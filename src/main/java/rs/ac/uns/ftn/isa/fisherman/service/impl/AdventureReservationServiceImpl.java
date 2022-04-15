package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.SearchAvailablePeriodsBoatAndAdventureDto;
import rs.ac.uns.ftn.isa.fisherman.mail.AdventureReservationSuccessfulInfo;
import rs.ac.uns.ftn.isa.fisherman.mail.MailService;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdditionalServiceMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdventureReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.FishingInstructorRepository;
import rs.ac.uns.ftn.isa.fisherman.service.*;
import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class AdventureReservationServiceImpl implements AdventureReservationService {
    private final Logger logger= LoggerFactory.getLogger(FirebaseServiceImpl.class);
    @Autowired
    private AdventureReservationRepository adventureReservationRepository;
    @Autowired
    private QuickReservationAdventureService quickReservationAdventureService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private MailService mailService;
    @Autowired
    private ReservationPaymentService reservationPaymentService;
    @Autowired
    private AvailableInstructorPeriodService availableInstructorPeriodService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    private FishingInstructorService fishingInstructorService;
    private AdventureReservationMapper adventureReservationMapper = new AdventureReservationMapper();
    private final AdditionalServiceMapper additionalServiceMapper = new AdditionalServiceMapper();
    @Override
    public boolean instructorCreates(AdventureReservation adventureReservation, String clientUsername) {
        Client client = clientService.findByUsername(clientUsername);
        if(!validateForReservation(adventureReservation,client)) return false;
        AdventureReservation successfullReservation=new AdventureReservation(adventureReservation.getId(),adventureReservation.getStartDate()
        ,adventureReservation.getEndDate(),client,adventureReservation.getPaymentInformation(),adventureReservation.isOwnerWroteAReport(),adventureReservation.getOwnersUsername(),
                adventureReservation.getAdventure(),adventureReservation.getFishingInstructor(),null);
        PaymentInformation paymentInformation = reservationPaymentService.setTotalPaymentAmount(successfullReservation,successfullReservation.getFishingInstructor());
        successfullReservation.setPaymentInformation(paymentInformation);
        reservationPaymentService.updateUserRankAfterReservation(client,successfullReservation.getFishingInstructor());
        adventureReservationRepository.save(successfullReservation);
        if(adventureReservation.getAddedAdditionalServices()!=null){
            successfullReservation.setAddedAdditionalServices(adventureReservation.getAddedAdditionalServices());
            adventureReservationRepository.save(successfullReservation);
        }

        sendMailNotification(successfullReservation,client.getUsername());
        return true;
    }

    @Override
    public Set<AdventureReservation> getPresentByInstructorId(String username) {
        return adventureReservationRepository.getPresentByInstructorId(username,LocalDateTime.now());
    }

    @Override
    public boolean reservationExists(String id, LocalDateTime startDate, LocalDateTime endDate) {
        return adventureReservationRepository.reservationExists(id,startDate,endDate);

    }

    @Override
    public boolean futureReservationsExist(LocalDateTime currentDate, Long id) {
        return  adventureReservationRepository.futureReservationsExist(currentDate,id);
    }

    @Override
    public Set<AdventureReservation> getPastReservations(String username) {
        return adventureReservationRepository.getPastReservations(username,LocalDateTime.now());
    }


    private boolean validateForReservation(AdventureReservation adventureReservation, Client client){
        LocalDateTime currentDate= LocalDateTime.now();
        if(client==null) return false;

        if(adventureReservationRepository.clientHasReservation(adventureReservation.getOwnersUsername()
                ,client.getId(),currentDate).size()==0) return false;

        if(!availableInstructorPeriodService.instructorIsAvailable(adventureReservation.getFishingInstructor()
                .getId(),adventureReservation.getStartDate(),adventureReservation.getEndDate())) return false;


        if(adventureReservationRepository.reservationExists(adventureReservation.getOwnersUsername()
                ,adventureReservation.getStartDate(),adventureReservation.getEndDate())) return false;

        if(quickReservationAdventureService.quickReservationExists(adventureReservation.getOwnersUsername(),
                adventureReservation.getStartDate(),adventureReservation.getEndDate())) return false;

        return true;
    }
    private void sendMailNotification(AdventureReservation adventureReservation,String email){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
            String message = adventureReservation.getAdventure().getName() + " is booked from " + adventureReservation.getStartDate().format(formatter) + " to " + adventureReservation.getEndDate().format(formatter) + " .";
            mailService.sendMail(email, message, new AdventureReservationSuccessfulInfo());
        } catch (MessagingException e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Integer countReservationsInPeriod(LocalDateTime start, LocalDateTime end, String username) {
        return adventureReservationRepository.countReservationsInPeriod(start,end,username);
    }
    @Override
    public double findReservationsAndSumProfit(String ownerUsername, LocalDateTime start, LocalDateTime end) {
        return sumProfitOfPricesCalucatedByHours(adventureReservationRepository.findReservationsInPeriodToSumProfit(ownerUsername,start,end),start,end);
    }
    @Override
    public double sumProfitOfPricesCalucatedByHours(List<AdventureReservation> reservations, LocalDateTime start, LocalDateTime end){
        double profit=0.0;
        double numOfHoursForReportReservation= 0.0;
        double reservationHours=0.0;
        for(AdventureReservation adventureReservation: reservations){
            numOfHoursForReportReservation= calculateOverlapingDates(start,end,adventureReservation.getStartDate(),adventureReservation.getEndDate());
            reservationHours= Duration.between(adventureReservation.getStartDate(),adventureReservation.getEndDate()).toMinutes()/60d;
            profit+=(numOfHoursForReportReservation* adventureReservation.getPaymentInformation().getOwnersPart())/reservationHours;
        }
        return profit;
    }

    private double calculateOverlapingDates(LocalDateTime startReport, LocalDateTime endReport, LocalDateTime startReservation, LocalDateTime endReservation){
        double numberOfOverlappingHours=0;
        LocalDateTime start = Collections.max(Arrays.asList(startReport, startReservation));
        LocalDateTime end = Collections.min(Arrays.asList(endReport, endReservation));
        numberOfOverlappingHours = ChronoUnit.MINUTES.between(start, end);
        return numberOfOverlappingHours/60d;
    }


    @Override
    public AdventureReservation findById(Long id) {
        return adventureReservationRepository.getById(id);
    }

    @Override
    public void save(AdventureReservation adventureReservation) {
        adventureReservationRepository.save(adventureReservation);
    }

    @Override
    public Set<Adventure> getAvailableAdventures(SearchAvailablePeriodsBoatAndAdventureDto searchAvailablePeriodsAdventureDto) {
        List<Long> availableInstructorsIds = getAvailableInstructors(searchAvailablePeriodsAdventureDto.getStartDate(), searchAvailablePeriodsAdventureDto.getEndDate());
        Set<Adventure> availableAdventures = new HashSet<>();
        for(Adventure adventure:adventureService.findAdventuresByInstructorIds(availableInstructorsIds)){
            if(searchAvailablePeriodsAdventureDto.getPrice()!=0){
                if(adventure.getPrice()>searchAvailablePeriodsAdventureDto.getPrice())
                    continue;
            }
            if(searchAvailablePeriodsAdventureDto.getMaxPeople()>adventure.getMaxPeople())
                continue;
            availableAdventures.add(adventure);
        }
        return availableAdventures;
    }

    private List<Long> getAvailableInstructors(LocalDateTime startDate, LocalDateTime endDate){
        //TODO: check if user has cancellation in that period with some instructor
        List<Long> availableInstructorsIds = new ArrayList<>();
        for(Long id:availableInstructorPeriodService.getAvailableFishingInstructorsIdsForPeriod(startDate, endDate)){
            if(!adventureReservationRepository.instructorHasReservationInPeriod(fishingInstructorRepository.findByID(id).getUsername(), startDate, endDate)){
                availableInstructorsIds.add(id);
            }
        }
        return availableInstructorsIds;
    }

    @Override
    public boolean makeReservation(AdventureReservationDto adventureReservationDto) {
        AdventureReservation adventureReservation = setUpAdventureReservationFromDto(adventureReservationDto);
        if(!clientHasCancellationWithInstructorInPeriod()){
            PaymentInformation paymentInformation = reservationPaymentService.setTotalPaymentAmount(adventureReservation, adventureReservation.getFishingInstructor());
            adventureReservation.setPaymentInformation(paymentInformation);
            reservationPaymentService.updateUserRankAfterReservation(adventureReservation.getClient(), adventureReservation.getFishingInstructor());
            adventureReservationRepository.save(adventureReservation);
            if(adventureReservationDto.getAddedAdditionalServices()!=null)
            {
                adventureReservation.setAddedAdditionalServices(additionalServiceMapper.additionalServicesDtoToAdditionalServices(adventureReservationDto.getAddedAdditionalServices()));
                adventureReservationRepository.save(adventureReservation);
            }
            SendReservationMailToClient(adventureReservationDto);
            return true;
        }
        return false;
    }

    private void SendReservationMailToClient(AdventureReservationDto adventureReservationDto) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
            String message = adventureReservationDto.getAdventureDto().getName() + " is booked from " + adventureReservationDto.getStartDate().format(formatter) + " to " + adventureReservationDto.getEndDate().format(formatter) + " .";
            mailService.sendMail(adventureReservationDto.getClientUsername(), message, new AdventureReservationSuccessfulInfo());
        } catch (MessagingException e) {
            logger.error(e.toString());
        }
    }

    private boolean clientHasCancellationWithInstructorInPeriod(){
        //TODO:
        return false;
    }

    private AdventureReservation setUpAdventureReservationFromDto(AdventureReservationDto adventureReservationDto) {
        FishingInstructor fishingInstructor = fishingInstructorService.findByUsername(adventureReservationDto.getAdventureDto().getFishingInstructorUsername());
        AdventureReservation adventureReservation = adventureReservationMapper.adventureReservationDtoToAdventureReservation(adventureReservationDto, fishingInstructor);
        adventureReservation.setClient(clientService.findByUsername(adventureReservationDto.getClientUsername()));
        return adventureReservation;
    }
}

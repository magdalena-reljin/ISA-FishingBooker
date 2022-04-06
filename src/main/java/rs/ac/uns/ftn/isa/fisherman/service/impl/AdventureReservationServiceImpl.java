package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.mail.AdventureReservationSuccessfullInfo;
import rs.ac.uns.ftn.isa.fisherman.mail.MailService;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.*;
import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

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
    @Override
    public boolean instructorCreates(AdventureReservation adventureReservation, String clientUsername) {
        Client client = clientService.findByUsername(clientUsername);
        if(!validateForReservation(adventureReservation,client)) return false;
        AdventureReservation successfullReservation=new AdventureReservation(adventureReservation.getId(),adventureReservation.getStartDate()
        ,adventureReservation.getEndDate(),client,adventureReservation.getPaymentInformation(),adventureReservation.isOwnerWroteAReport(),
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
    public Set<AdventureReservation> getPresentByInstructorId(Long id) {
        LocalDateTime currentDate= LocalDateTime.now();
        return adventureReservationRepository.getPresentByInstructorId(id,currentDate);
    }

    @Override
    public boolean reservationExists(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return adventureReservationRepository.reservationExists(id,startDate,endDate);

    }

    @Override
    public boolean futureReservationsExist(LocalDateTime currentDate, Long id) {
        return  adventureReservationRepository.futureReservationsExist(currentDate,id);
    }

    @Override
    public Set<AdventureReservation> getPastReservations(Long id) {
        LocalDateTime currentDate = LocalDateTime.now();
        return adventureReservationRepository.getPastReservations(id,currentDate);
    }

    @Override
    public void ownerCreatesReview(AdventureReservation reservation, boolean successfull) {
        AdventureReservation adventureReservation = adventureReservationRepository.getById(reservation.getId());
        adventureReservation.setSuccessfull(successfull);
       // adventureReservation.getOwnersReport().setComment(reservation.getOwnersReport().getComment());
      //  adventureReservation.getOwnersReport().setBadComment(reservation.getOwnersReport().isBadComment());
        adventureReservationRepository.save(adventureReservation);
    }

    private boolean validateForReservation(AdventureReservation adventureReservation, Client client){
        LocalDateTime currentDate= LocalDateTime.now();
        if(client==null) return false;
      /*  if(!adventureReservationRepository.clientHasReservation(adventureReservation.getFishingInstructor().
                getId(),client.getId(),currentDate)) return false;*/

        if(!availableInstructorPeriodService.instructorIsAvailable(adventureReservation.getFishingInstructor()
                .getId(),adventureReservation.getStartDate(),adventureReservation.getEndDate())) return false;

        if(adventureReservationRepository.reservationExists(adventureReservation.getFishingInstructor().getId()
                ,adventureReservation.getStartDate(),adventureReservation.getEndDate())) return false;

        if(quickReservationAdventureService.quickReservationExists(adventureReservation.getFishingInstructor().getId(),
                adventureReservation.getStartDate(),adventureReservation.getEndDate())) return false;

        return true;
    }
    private void sendMailNotification(AdventureReservation adventureReservation,String email){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
            String message = adventureReservation.getAdventure().getName() + " is booked from " + adventureReservation.getStartDate().format(formatter) + " to " + adventureReservation.getEndDate().format(formatter) + " .";
            mailService.sendMail(email, message, new AdventureReservationSuccessfullInfo());
        } catch (MessagingException e) {
            logger.error(e.toString());
        }
    }

    public Set<AdventureReservation>getAllReports(){
       return adventureReservationRepository.getAllReports();
    }

    @Override
    public Integer countReservationsInPeriod(LocalDateTime start, LocalDateTime end, Long ownerId) {
        return adventureReservationRepository.countReservationsInPeriod(start,end,ownerId);
    }

    @Override
    public Double sumProfit(Long ownerId, LocalDateTime start, LocalDateTime end) {
        return adventureReservationRepository.sumProfit(ownerId,start,end);
    }
}

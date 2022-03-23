package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.SearchAvailablePeriodsCabinDto;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.mail.AccountAcceptedInfo;
import rs.ac.uns.ftn.isa.fisherman.mail.CabinReservationSuccessfulInfo;
import rs.ac.uns.ftn.isa.fisherman.mail.MailService;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdditionalServiceMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableCabinPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableCabinPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationCabinService;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class ReservationCabinServiceImpl implements ReservationCabinService {

    @Autowired
    private ClientService clientService;
    @Autowired
    private MailService<String> mailService;
    private CabinReservationMapper cabinReservationMapper = new CabinReservationMapper();
    AdditionalServiceMapper additionalServiceMapper = new AdditionalServiceMapper();
    @Autowired
    private AvailableCabinPeriodService availableCabinPeriodService;
    @Autowired
    private CabinReservationRepository cabinReservationRepository;



    @Override
    public Set<Cabin> getAvailableCabins(SearchAvailablePeriodsCabinDto searchAvailablePeriodsCabinDto) {
        Set<Cabin> availableCabins = new HashSet<>();
        for(AvailableCabinPeriod cabinPeriod:availableCabinPeriodService.findAll()){
            if(searchAvailablePeriodsCabinDto.getStartDate().isBefore(cabinPeriod.getStartDate())
                    ||searchAvailablePeriodsCabinDto.getEndDate().isAfter(cabinPeriod.getEndDate())) {
                continue;
            }
            if(searchAvailablePeriodsCabinDto.getPrice()!=0){
                if(cabinPeriod.getCabin().getPrice()>searchAvailablePeriodsCabinDto.getPrice())
                    continue;
            }
            if(searchAvailablePeriodsCabinDto.getBedsPerRoom()>cabinPeriod.getCabin().getBedsPerRoom())
                continue;
            if(searchAvailablePeriodsCabinDto.getNumberOfRooms()>cabinPeriod.getCabin().getNumOfRooms())
                continue;
            if(cabinNotReservedInPeriod(cabinPeriod.getCabin().getId(), searchAvailablePeriodsCabinDto.getStartDate(), searchAvailablePeriodsCabinDto.getEndDate()))
                availableCabins.add(cabinPeriod.getCabin());
        }
        return availableCabins;
    }

    @Override
    public boolean makeReservation(CabinReservationDto cabinReservationDto) {
        CabinReservation cabinReservation = cabinReservationMapper.CabinReservationDtoToCabinReservation(cabinReservationDto);
        cabinReservation.setClient(clientService.findByUsername(cabinReservationDto.getClientUsername()));
        if(periodStillAvailable(cabinReservation)){
            cabinReservationRepository.save(cabinReservation);
            if(cabinReservationDto.getAddedAdditionalServices()!=null)
                cabinReservation.setAddedAdditionalServices(additionalServiceMapper.AdditionalServicesDtoToAdditionalServices(cabinReservationDto.getAddedAdditionalServices()));
                cabinReservationRepository.save(cabinReservation);
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
                String message = cabinReservationDto.getCabinDto().getName() + " is booked from " + cabinReservationDto.getStartDate().format(formatter) + " to " + cabinReservationDto.getEndDate().format(formatter) + " .";
                mailService.sendMail("aleksastojicns@gmail.com", message, new CabinReservationSuccessfulInfo());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    private boolean periodStillAvailable(CabinReservation cabinReservation) {
        for(AvailableCabinPeriod cabinPeriod:availableCabinPeriodService.findAll()){
            if(cabinPeriod.getCabin().getId().equals(cabinReservation.getCabin().getId())){
                if(cabinReservation.getStartDate().isAfter(cabinPeriod.getStartDate())
                        &&cabinReservation.getEndDate().isBefore(cabinPeriod.getEndDate())
                        &&cabinNotReservedInPeriod(cabinReservation.getCabin().getId(), cabinReservation.getStartDate(),
                        cabinReservation.getEndDate())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean cabinNotReservedInPeriod(Long id, LocalDateTime start, LocalDateTime end) {
        for(CabinReservation cabinReservation:cabinReservationRepository.findAll()){
            if(cabinReservation.getCabin().getId().equals(id)){
                if((cabinReservation.getStartDate().isBefore(end)||cabinReservation.getStartDate().isEqual(end))
                        &&(cabinReservation.getEndDate().isAfter(start)||cabinReservation.getEndDate().isEqual(start))) {
                    return false;
                }
            }
        }
        return true;
    }
}

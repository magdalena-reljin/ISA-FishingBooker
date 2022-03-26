package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.model.Client;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationCabin;
import rs.ac.uns.ftn.isa.fisherman.repository.QuickReservationCabinRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableCabinPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationCabinService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationCabinService;

import java.time.LocalDateTime;

@Service
public class QuickReservationCabinServiceImpl implements QuickReservationCabinService {

    @Autowired
    private AvailableCabinPeriodService availableCabinPeriodService;
    @Autowired
    private ReservationCabinService reservationCabinService;
    @Autowired
    private QuickReservationCabinRepository quickReservationCabinRepository;
    @Override
    public boolean ownerCreates(CabinReservation cabinReservation) {
        if(!validateForReservation(cabinReservation)) return false;

        QuickReservationCabin successfullQuickReservation=new QuickReservationCabin(cabinReservation.getId(),cabinReservation.getStartDate(),
                cabinReservation.getEndDate(),cabinReservation.getPrice(),cabinReservation.getCabin(),null);
        successfullQuickReservation.setClient(null);
        quickReservationCabinRepository.save(successfullQuickReservation);
        if(cabinReservation.getAddedAdditionalServices()!=null){
            successfullQuickReservation.setAddedAdditionalServices(cabinReservation.getAddedAdditionalServices());
            quickReservationCabinRepository.save(successfullQuickReservation);
        }
        //TO DO: poslati mejl onima koji su pretplaceni na akcije od tog cabina

        return true;
    }

    private boolean validateForReservation(CabinReservation cabinReservation){
        if(!availableCabinPeriodService.cabinIsAvailable(cabinReservation.getCabin()
                .getId(),cabinReservation.getStartDate(),cabinReservation.getEndDate())) return false;

        if(reservationCabinService.reservationExists(cabinReservation.getCabin()
                .getId(),cabinReservation.getStartDate(),cabinReservation.getEndDate())) return false;

        if(quickReservationCabinRepository.quickReservationExists(cabinReservation.getCabin()
                .getId(),cabinReservation.getStartDate(),cabinReservation.getEndDate())) return false;

        return true;
    }
}

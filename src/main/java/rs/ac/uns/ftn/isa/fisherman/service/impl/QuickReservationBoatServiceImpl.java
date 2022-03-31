package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.BoatReservation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationBoat;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationCabin;
import rs.ac.uns.ftn.isa.fisherman.repository.QuickReservationBoatRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.QuickReservationCabinRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableBoatPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatReservationService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationBoatService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationCabinService;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class QuickReservationBoatServiceImpl implements QuickReservationBoatService {
    @Autowired
    private AvailableBoatPeriodService availableBoatPeriodService;
    @Autowired
    private BoatReservationService boatReservationService;
    @Autowired
    private QuickReservationBoatRepository quickReservationBoatRepository;

    @Override
    public boolean ownerCreates(BoatReservation boatReservation) {
        if(!validateForReservation(boatReservation)) return false;

        QuickReservationBoat successfullQuickReservation=new QuickReservationBoat(boatReservation.getId(),boatReservation.getStartDate(),
                boatReservation.getEndDate(),boatReservation.getPrice(),boatReservation.getBoat(),null,boatReservation.getNeedsCaptainService());
        successfullQuickReservation.setClient(null);
        if(boatReservation.getAddedAdditionalServices()!=null){
            if(boatReservation.getNeedsCaptainService()) {
                if (ownerIsNotAvailable(successfullQuickReservation.getBoat().getBoatOwner().getId(),
                        successfullQuickReservation.getStartDate(), successfullQuickReservation.getEndDate())) return false;
            }
            quickReservationBoatRepository.save(successfullQuickReservation);
            successfullQuickReservation.setAddedAdditionalServices(boatReservation.getAddedAdditionalServices());
            quickReservationBoatRepository.save(successfullQuickReservation);
        }else{
            quickReservationBoatRepository.save(successfullQuickReservation);
        }
        //TO DO: poslati mejl onima koji su pretplaceni na akcije

        return true;
    }
    private boolean ownerIsNotAvailable(Long ownerId, LocalDateTime start, LocalDateTime end){
        if (quickReservationBoatRepository.ownerIsNotAvailable(ownerId, start, end)) return true;

        if(boatReservationService.ownerIsNotAvailableReservation(ownerId, start, end)) return true;
        return  false;
    }
    @Override
    public boolean ownerIsNotAvailableQuickResrvation(Long ownerId, LocalDateTime start, LocalDateTime end){
        return quickReservationBoatRepository.ownerIsNotAvailable(ownerId, start, end);
    }

    @Override
    public Set<QuickReservationBoat> findReservationsByOwnerId(Long id) {
        return quickReservationBoatRepository.findReservationsByOwnerId(id);
    }

    @Override
    public Set<QuickReservationBoat> getByBoatId(Long cabinId) {
        return quickReservationBoatRepository.getByBoatId(cabinId);
    }

    @Override
    public boolean quickReservationExists(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return quickReservationBoatRepository.quickReservationExists(id,startDate,endDate);
    }

    private boolean validateForReservation(BoatReservation boatReservation){
        if(!availableBoatPeriodService.boatIsAvailable(boatReservation.getBoat()
                .getId(),boatReservation.getStartDate(),boatReservation.getEndDate())) return false;

        if(boatReservationService.reservationExists(boatReservation.getBoat()
                .getId(),boatReservation.getStartDate(),boatReservation.getEndDate())) return false;

        if(quickReservationBoatRepository.quickReservationExists(boatReservation.getBoat()
                .getId(),boatReservation.getStartDate(),boatReservation.getEndDate())) return false;

        return true;
    }
    @Override
    public boolean futureQuickReservationsExist(LocalDateTime currentDate,Long boatId){
        return quickReservationBoatRepository.futureQuickReservationsExist(currentDate,boatId);
    }
}

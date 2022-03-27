package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableCabinPeriod;
import rs.ac.uns.ftn.isa.fisherman.repository.AvailableCabinPeriodRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableCabinPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.AvailablePeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationCabinService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationCabinService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Service
public class AvailableCabinPeriodServiceImpl implements AvailableCabinPeriodService {
    @Autowired
    private AvailableCabinPeriodRepository availableCabinPeriodRepository;

    @Autowired
    private ReservationCabinService reservationCabinService;

    @Autowired
    private QuickReservationCabinService quickReservationCabinService;

    @Autowired
    private AvailablePeriodService availablePeriodService;

    @Override
    public Set<AvailableCabinPeriod> getAvailablePeriod(Long id) {
        return  availableCabinPeriodRepository.findByCabinId(id);
    }

    @Override
    public boolean setAvailableCabinPeriod(AvailableCabinPeriod availableCabinPeriod) {
            if(availableCabinPeriodRepository.availablePeriodAlreadyExists(availableCabinPeriod.getCabin().getId(),
                    availableCabinPeriod.getStartDate(),availableCabinPeriod.getEndDate())) return false;
            availableCabinPeriodRepository.save(availableCabinPeriod);
            return true;
    }

    @Override
    public List<AvailableCabinPeriod> findAll() {
        return availableCabinPeriodRepository.findAll();
    }

    @Override
    public boolean cabinIsAvailable(Long cabinId, LocalDateTime start, LocalDateTime end) {
        return availableCabinPeriodRepository.cabinIsAvailable(cabinId,start,end);
    }

    @Override
    public boolean deleteAvailableCabinsPeriod(AvailableCabinPeriod availablePeriod) {
        System.out.println("----------------------------------------------------------------------------------------------------usao sam u delete");
        AvailableCabinPeriod availableCabinPeriodToDelete= availableCabinPeriodRepository.findId(availablePeriod.getCabin().getId(),
                availablePeriod.getStartDate(),availablePeriod.getEndDate());
        if(availableCabinPeriodToDelete == null){
            System.out.println("*************************************************************************************nisam nasao period");
            return false;
        }

        if(!reservationsDontExistInPeriod(availableCabinPeriodToDelete)) {
            System.out.println("*************************************************************************************iam neke rez");

            return false;
        }
        System.out.println("*************************************************************************************stigao sam do DELETE"+availableCabinPeriodToDelete.getId() );

        availablePeriodService.deleteAvailablePeriod(availableCabinPeriodToDelete.getId());
        return true;
    }

    private boolean reservationsDontExistInPeriod(AvailableCabinPeriod availablePeriod){
        if(reservationCabinService.reservationExists(availablePeriod.getCabin().getId()
                ,availablePeriod.getStartDate(),availablePeriod.getEndDate())){
            System.out.println("----------------------------------------------------------------ja sam nasla prva");
            return false;
            }

        if(quickReservationCabinService.quickReservationExists(availablePeriod.getCabin().getId(),
                availablePeriod.getStartDate(),availablePeriod.getEndDate())) {
            System.out.println("----------------------------------------------------------------ja sam nasla druga");

            return false;
        }
        return true;
    }
}

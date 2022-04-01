package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationAdventure;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationCabin;
import rs.ac.uns.ftn.isa.fisherman.repository.QuickReservationAdventureRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureReservationService;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableInstructorPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationAdventureService;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class QuickReservationAdventureImpl implements QuickReservationAdventureService {
    @Autowired
    private QuickReservationAdventureRepository quickReservationAdventureRepository;
    @Autowired
    private AvailableInstructorPeriodService availableInstructorPeriodService;
    @Autowired
    private AdventureReservationService adventureReservationService;
    @Override
    public boolean instructorCreates(AdventureReservation adventureReservation) {
        if(!validateForReservation(adventureReservation)) return false;

        QuickReservationAdventure successfullQuickReservation=new QuickReservationAdventure(adventureReservation.getId(),adventureReservation.getStartDate(),
                adventureReservation.getEndDate(),adventureReservation.getPrice(),adventureReservation.getAdventure(),adventureReservation.getFishingInstructor(),null);
        successfullQuickReservation.setClient(null);
        quickReservationAdventureRepository.save(successfullQuickReservation);
        if(adventureReservation.getAddedAdditionalServices()!=null){
            successfullQuickReservation.setAddedAdditionalServices(adventureReservation.getAddedAdditionalServices());
            quickReservationAdventureRepository.save(successfullQuickReservation);
        }
        //TO DO: poslati mejl onima koji su pretplaceni na akcije od tog cabina

        return true;
    }

    @Override
    public Set<QuickReservationAdventure> getByInstructorId(Long id) {
        return  quickReservationAdventureRepository.getByInstructorId(id);
    }

    @Override
    public boolean quickReservationExists(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return quickReservationAdventureRepository.quickReservationExists(id,startDate,endDate);
    }

    @Override
    public boolean futureQuickReservationsExist(LocalDateTime currentDate,Long id) {
        return quickReservationAdventureRepository.futureQuickReservationsExist(currentDate,id);
    }

    private boolean validateForReservation(AdventureReservation adventureReservation){
        if(!availableInstructorPeriodService.instructorIsAvailable(adventureReservation.getFishingInstructor()
                .getId(),adventureReservation.getStartDate(),adventureReservation.getEndDate())) return false;

        if(adventureReservationService.reservationExists(adventureReservation.getFishingInstructor()
                .getId(),adventureReservation.getStartDate(),adventureReservation.getEndDate())) return false;

        if(quickReservationAdventureRepository.quickReservationExists(adventureReservation.getFishingInstructor().getId()
                ,adventureReservation.getStartDate(),adventureReservation.getEndDate())) return false;

        return true;
    }
}

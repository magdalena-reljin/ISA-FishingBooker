package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.QuickReservationAdventureDto;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureReservationCancellationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.QuickReservationAdventureRepository;
import rs.ac.uns.ftn.isa.fisherman.service.*;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class AdventureReservationCancellationImpl implements AdventureReservationCancellationService {

    @Autowired
    private AdventureReservationCancellationRepository adventureReservationCancellationRepository;
    @Autowired
    private PenaltyService penaltyService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private FishingInstructorService fishingInstructorService;
    @Autowired
    private AdventureReservationRepository adventureReservationRepository;
    @Autowired
    private ReservationPaymentService reservationPaymentService;
    @Autowired
    private QuickReservationAdventureRepository quickReservationAdventureRepository;

    @Override
    public boolean addCancellation(AdventureReservationDto adventureReservationDto) {
        AdventureReservation adventureReservation = adventureReservationRepository.getById(adventureReservationDto.getId());
        AdventureReservationCancellation adventureReservationCancellation = new AdventureReservationCancellation(null, adventureReservation.getClient(), adventureReservation.getStartDate(), adventureReservation.getEndDate(), adventureReservation.getFishingInstructor());
        adventureReservation.setAddedAdditionalServices(new HashSet<>());
        reservationPaymentService.resetLoyaltyStatusAfterCancellation(adventureReservation.getClient(), adventureReservation.getFishingInstructor());
        adventureReservationRepository.save(adventureReservation);
        adventureReservationRepository.deleteByReservationId(adventureReservation.getId());
        adventureReservationCancellationRepository.save(adventureReservationCancellation);
        penaltyService.addPenalty(adventureReservation.getClient().getUsername());
        return true;
    }

    @Override
    public boolean clientHasCancellationWithInstructorInPeriod(String ownersUsername, String clientUsername, LocalDateTime startDate, LocalDateTime endDate) {
        return adventureReservationCancellationRepository.clientHasCancellationWithInstructorInPeriod(fishingInstructorService.findByUsername(ownersUsername).getId(), clientService.findByUsername(clientUsername).getId(), startDate, endDate);
    }

    @Override
    public boolean addCancellationQuickReservation(QuickReservationAdventureDto adventureReservationDto) {
        QuickReservationAdventure quickReservationAdventure = quickReservationAdventureRepository.getById(adventureReservationDto.getId());
        AdventureReservationCancellation adventureReservationCancellation = new AdventureReservationCancellation(null, quickReservationAdventure.getClient(), quickReservationAdventure.getStartDate(), quickReservationAdventure.getEndDate(), quickReservationAdventure.getFishingInstructor());
        reservationPaymentService.resetLoyaltyStatusAfterCancellation(quickReservationAdventure.getClient(), quickReservationAdventure.getFishingInstructor());
        penaltyService.addPenalty(quickReservationAdventure.getClient().getUsername());
        quickReservationAdventure.setClient(null);
        quickReservationAdventureRepository.save(quickReservationAdventure);
        adventureReservationCancellationRepository.save(adventureReservationCancellation);
        return true;
    }
}

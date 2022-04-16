package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.BoatReservationDto;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservation;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservationCancellation;
import rs.ac.uns.ftn.isa.fisherman.model.BoatReservation;
import rs.ac.uns.ftn.isa.fisherman.model.BoatReservationCancellation;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.BoatReservationCancellationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.BoatReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.*;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class BoatReservationCancellationServiceImpl implements BoatReservationCancellationService {

    @Autowired
    private BoatReservationCancellationRepository boatReservationCancellationRepository;
    @Autowired
    private BoatService boatService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BoatReservationRepository boatReservationRepository;
    @Autowired
    private ReservationPaymentService reservationPaymentService;
    @Autowired
    private PenaltyService penaltyService;

    @Override
    public boolean addCancellation(BoatReservationDto boatReservationDto) {
        if(boatReservationDto.getStartDate().minusDays(3).isBefore(LocalDateTime.now()))
            return false;
        BoatReservation boatReservation = boatReservationRepository.getById(boatReservationDto.getId());
        BoatReservationCancellation boatReservationCancellation = new BoatReservationCancellation(null, boatReservation.getClient(), boatReservationDto.getStartDate(), boatReservationDto.getEndDate(), boatReservation.getBoat());
        boatReservation.setAddedAdditionalServices(new HashSet<>());
        reservationPaymentService.resetLoyaltyStatusAfterCancellation(boatReservation.getClient(), boatReservation.getBoat().getBoatOwner());
        boatReservationRepository.save(boatReservation);
        boatReservationRepository.deleteByReservationId(boatReservation.getId());
        boatReservationCancellationRepository.save(boatReservationCancellation);
        penaltyService.addPenalty(boatReservation.getClient().getUsername());
        return true;
    }

    @Override
    public boolean clientHasCancellationForBoatInPeriod(BoatReservationDto boatReservationDto) {
        return boatReservationCancellationRepository.clientHasCancellationForBoatInPeriod(boatService.findById(boatReservationDto.getBoatDto().getId()).getId(), clientService.findByUsername(boatReservationDto.getClientUsername()).getId(), boatReservationDto.getStartDate(), boatReservationDto.getEndDate());
    }
}

package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservationCancellation;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationCancellationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinReservationCancellationService;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationPaymentService;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class CabinReservationCancellationServiceImpl implements CabinReservationCancellationService {

    @Autowired
    private CabinReservationCancellationRepository cabinReservationCancellationRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CabinReservationRepository cabinReservationRepository;
    @Autowired
    private CabinOwnerService cabinOwnerService;
    @Autowired
    private ReservationPaymentService reservationPaymentService;

    @Override
    public boolean addCancellation(CabinReservationDto cabinReservationDto) {
        CabinOwner cabinOwner = cabinOwnerService.findByUsername(cabinReservationDto.getCabinDto().getOwnerUsername());
        if(cabinReservationDto.getStartDate().minusDays(3).isBefore(LocalDateTime.now())||
                (cabinReservationCancellationRepository.findById(cabinReservationDto.getId()).isPresent()))
            return false;
        CabinReservationCancellation cabinReservationCancellation = new CabinReservationCancellation();
        CabinReservation cabinReservation = cabinReservationRepository.getById(cabinReservationDto.getId());
        cabinReservationCancellation.setCabin(cabinReservation.getCabin());
        cabinReservationCancellation.setClient(cabinReservation.getClient());
        cabinReservationCancellation.setStartDate(cabinReservation.getStartDate());
        cabinReservationCancellation.setEndDate(cabinReservation.getEndDate());
        cabinReservation.setAddedAdditionalServices(new HashSet<>());
        reservationPaymentService.resetLoyaltyStatusAfterCancellation(cabinReservation.getClient(),cabinOwner);
        cabinReservationRepository.save(cabinReservation);
        cabinReservationRepository.deleteByReservationId(cabinReservation.getId());
        cabinReservationCancellationRepository.save(cabinReservationCancellation);
        return true;
    }
}

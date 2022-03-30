package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservationCancellation;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationCancellationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinReservationCancellationService;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;

import java.time.LocalDateTime;

@Service
public class CabinReservationCancellationServiceImpl implements CabinReservationCancellationService {

    @Autowired
    private CabinReservationCancellationRepository cabinReservationCancellationRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CabinReservationRepository cabinReservationRepository;

    @Override
    public boolean addCancellation(CabinReservationDto cabinReservationDto) {
        if(cabinReservationDto.getStartDate().minusDays(3).isBefore(LocalDateTime.now()))
            return false;
        CabinReservationCancellation cabinReservationCancellation = new CabinReservationCancellation();
        CabinReservation cabinReservation = cabinReservationRepository.getById(cabinReservationDto.getId());
        cabinReservation.setClient(clientService.findByUsername(cabinReservationDto.getClientUsername()));
        cabinReservationCancellation.setCabinReservation(cabinReservation);
        cabinReservationCancellation.setClient(cabinReservation.getClient());
        cabinReservationCancellationRepository.save(cabinReservationCancellation);
        return true;
    }
}

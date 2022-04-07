package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinEvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.model.CabinEvaluation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinEvaluationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinReservationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinEvaluationService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationCabinService;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class CabinEvaluationServiceImpl implements CabinEvaluationService {

    @Autowired
    ClientService clientService;
    @Autowired
    CabinReservationRepository cabinReservationRepository;
    @Autowired
    CabinEvaluationRepository cabinEvaluationRepository;
    @Autowired
    CabinService cabinService;
    @Autowired
    ReservationCabinService cabinReservationService;

    @Override
    public boolean addEvaluation(CabinEvaluationDto cabinEvaluationDto) {
        CabinReservation cabinReservation = cabinReservationService.getById(cabinEvaluationDto.getCabinReservationDto().getId());
        if(cabinReservation.isEvaluated()){
            return false;
        }
        cabinEvaluationRepository.save(new CabinEvaluation(null, LocalDateTime.now(), cabinEvaluationDto.getComment(), cabinEvaluationDto.getGrade(), false, clientService.findByUsername(cabinEvaluationDto.getClientUsername()), cabinReservation.getCabin()));
        cabinReservationService.reservationIsEvaluated(cabinReservation.getId());
        return true;
    }

    @Override
    public boolean reservationHasEvaluation(Long id) {
        return cabinEvaluationRepository.reservationHasEvaluation(id);
    }

    @Override
    public Set<CabinEvaluation> findByCabinId(Long cabinId) {
        return null;
    }
}

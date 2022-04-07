package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.CabinEvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.model.CabinEvaluation;

import java.util.Set;

public interface CabinEvaluationService {

    boolean addEvaluation(CabinEvaluationDto cabinEvaluationDto);
    boolean reservationHasEvaluation(Long id);

    Set<CabinEvaluation> findByCabinId(Long cabinId);
}

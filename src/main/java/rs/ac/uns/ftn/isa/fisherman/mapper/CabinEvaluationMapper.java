package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.CabinEvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.model.CabinEvaluation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;

public class CabinEvaluationMapper {
    private CabinMapper cabinMapper=new CabinMapper();
    public CabinEvaluationDto cabinEvaluationToDto(CabinEvaluation cabinEvaluation){
        return new CabinEvaluationDto(cabinEvaluation.getId(),cabinEvaluation.getDate(),cabinEvaluation.getComment(),
                cabinEvaluation.getGrade(),cabinEvaluation.isApproved(),cabinEvaluation.getClient().getUsername(),
                cabinMapper.cabinToCabinDto(cabinEvaluation.getCabin()), null);
    }
}

package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.AddNewFishingInstructorEvaluationDto;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwnerEvaluation;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructorEvaluation;
import rs.ac.uns.ftn.isa.fisherman.repository.FishingInstructorEvaluationRepository;
import rs.ac.uns.ftn.isa.fisherman.repository.FishingInstructorRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureReservationService;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorEvaluationService;

import java.time.LocalDateTime;

@Service
public class FishingInstructorEvaluationServiceImpl implements FishingInstructorEvaluationService {

    @Autowired
    private AdventureReservationService adventureReservationService;
    @Autowired
    private FishingInstructorEvaluationRepository fishingInstructorEvaluationRepository;
    @Override
    public void addEvaluation(AddNewFishingInstructorEvaluationDto addNewFishingInstructorEvaluationDto) {
        AdventureReservation adventureReservation = adventureReservationService.findById(addNewFishingInstructorEvaluationDto.getReservationId());
        fishingInstructorEvaluationRepository.save(new FishingInstructorEvaluation(null, LocalDateTime.now(), addNewFishingInstructorEvaluationDto.getCommentForTheFishingInstructor(), addNewFishingInstructorEvaluationDto.getGradeForTheFishingInstructor(), false, adventureReservation.getClient(), adventureReservation.getFishingInstructor().getUsername()));
    }
}

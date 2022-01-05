package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.repository.FishingInstructorRepository;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;

import java.util.List;
@Service
public class FishingInstructorServiceImpl implements FishingInstructorService {
    private FishingInstructorRepository fishingInstructorRepository;
    @Autowired
    public FishingInstructorServiceImpl(FishingInstructorRepository fishingInstructorRepository) {
        this.fishingInstructorRepository = fishingInstructorRepository;
    }

    @Override
    public List<FishingInstructor> getNewFishingInstructors() {return fishingInstructorRepository.getNewFishingInstructor();}
}
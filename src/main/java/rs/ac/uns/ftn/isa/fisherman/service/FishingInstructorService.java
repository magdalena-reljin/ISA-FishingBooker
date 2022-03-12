package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;

import java.util.List;

public interface FishingInstructorService {
    List<FishingInstructor> getNewFishingInstructors();
    List<FishingInstructor> getNewActiveInstructors();

    FishingInstructor findByUsername(String fishingInstructorUsername);


    FishingInstructor findByID(Long id);

    void save(FishingInstructor fishingInstructor);
}

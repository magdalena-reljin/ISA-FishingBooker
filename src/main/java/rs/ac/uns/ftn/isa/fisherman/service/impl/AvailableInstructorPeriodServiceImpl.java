package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableInstructorPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.repository.AvailableInstructorPeriodRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableInstructorPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;

import java.util.Set;

@Service
public class AvailableInstructorPeriodServiceImpl implements AvailableInstructorPeriodService {
    @Autowired
    AvailableInstructorPeriodRepository availableInstructorPeriodRepository;

    @Autowired
    FishingInstructorService fishingInstructorService;


    @Override
    public Set<AvailableInstructorPeriod> getAvailablePeriod(String username) {
        Long instructorId= fishingInstructorService.findByUsername(username).getId();
        return  availableInstructorPeriodRepository.findByInstructorId(instructorId);
    }
}

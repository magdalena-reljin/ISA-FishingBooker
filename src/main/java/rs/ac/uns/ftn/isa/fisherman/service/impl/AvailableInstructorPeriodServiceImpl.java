package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableInstructorPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.repository.AvailableInstructorPeriodRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableInstructorPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;

import java.time.LocalDateTime;
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

    @Override
    public boolean setAvailableInstructorPeriod(AvailableInstructorPeriod availableInstructorPeriod) {

        if(availableInstructorPeriodRepository.availablePeriodAlreadyExists(availableInstructorPeriod.getFishingInstructor().getId(),
                availableInstructorPeriod.getStartDate(),availableInstructorPeriod.getEndDate())) return false;
        availableInstructorPeriodRepository.save(availableInstructorPeriod);
        return true;
    }

    @Override
    public boolean instructorIsAvailable(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return availableInstructorPeriodRepository.instructorIsAvailable(id,startDate,endDate);
    }

}

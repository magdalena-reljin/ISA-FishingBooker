package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AvailableInstructorPeriodDto;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableInstructorPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;

import java.util.HashSet;
import java.util.Set;

public class AvailableInstructorPeriodMapper {

    public AvailableInstructorPeriodDto availablePeriodToAvailablePeriodDto(AvailableInstructorPeriod availableInstructorPeriod){
        return new AvailableInstructorPeriodDto(availableInstructorPeriod.getId(),availableInstructorPeriod.getStartDate(),
                availableInstructorPeriod.getEndDate(),availableInstructorPeriod.getFishingInstructor().getUsername());
    }

    public Set<AvailableInstructorPeriodDto> availableInstructorPeriodsDtos(Set<AvailableInstructorPeriod> availableInstructorPeriods){
        Set<AvailableInstructorPeriodDto> periods = new HashSet<>();
        for(AvailableInstructorPeriod availableInstructorPeriod: availableInstructorPeriods){
            periods.add(availablePeriodToAvailablePeriodDto(availableInstructorPeriod));
        }
        return periods;
    }

    public  AvailableInstructorPeriod availableInstructorPeriodDtoToAvailableInstructorPeriod(AvailableInstructorPeriodDto availableInstructorPeriodDto, FishingInstructor fishingInstructor){
        return  new AvailableInstructorPeriod(availableInstructorPeriodDto.getId(),availableInstructorPeriodDto.getStartDate(),availableInstructorPeriodDto.getEndDate(),fishingInstructor);
    }

    public Set<AvailableInstructorPeriod> availableInstructorDtosToInstructorPeriods(Set<AvailableInstructorPeriodDto> availableInstructorPeriodDtoSet,FishingInstructor fishingInstructor){
        Set<AvailableInstructorPeriod> availableInstructorPeriods = new HashSet<>();
        for(AvailableInstructorPeriodDto availableInstructorPeriodDto: availableInstructorPeriodDtoSet){
          //  System.out.println("AAAAAAAAAAAAAAAAAA" +availableInstructorPeriodDto.getStartDate());
            availableInstructorPeriods.add(availableInstructorPeriodDtoToAvailableInstructorPeriod(availableInstructorPeriodDto,fishingInstructor));
        }
        return availableInstructorPeriods;
    }
}

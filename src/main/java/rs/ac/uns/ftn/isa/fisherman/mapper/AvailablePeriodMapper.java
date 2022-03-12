package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AvailablePeriodDto;
import rs.ac.uns.ftn.isa.fisherman.model.*;

import java.util.HashSet;
import java.util.Set;

public class AvailablePeriodMapper {

    public AvailablePeriodDto availableInstructorPeriodToAvailablePeriodDto(AvailableInstructorPeriod availableInstructorPeriod){
        return new AvailablePeriodDto(availableInstructorPeriod.getId(),availableInstructorPeriod.getStartDate(),
                availableInstructorPeriod.getEndDate(),availableInstructorPeriod.getFishingInstructor().getUsername());
    }
    public AvailablePeriodDto availableBoatPeriodToAvailablePeriodDto(AvailableBoatPeriod availableBoatPeriod){
        return new AvailablePeriodDto(availableBoatPeriod.getId(),availableBoatPeriod.getStartDate(),
                availableBoatPeriod.getEndDate(),availableBoatPeriod.getBoatOwner().getUsername(),availableBoatPeriod.getBoat().getId());
    }
    public AvailablePeriodDto availableCabinPeriodToAvailablePeriodDto(AvailableCabinPeriod availableCabinPeriod){
        return new AvailablePeriodDto(availableCabinPeriod.getId(),availableCabinPeriod.getStartDate(),
                availableCabinPeriod.getEndDate(),availableCabinPeriod.getCabinOwner().getUsername(),availableCabinPeriod.getCabin().getId());
    }

    public Set<AvailablePeriodDto> availableInstructorPeriodsToDtos(Set<AvailableInstructorPeriod> availableInstructorPeriods){
        Set<AvailablePeriodDto> periods = new HashSet<>();
        for(AvailableInstructorPeriod availableInstructorPeriod: availableInstructorPeriods){
            periods.add(availableInstructorPeriodToAvailablePeriodDto(availableInstructorPeriod));
        }
        return periods;
    }
    public Set<AvailablePeriodDto> availableBoatPeriodsToDtos(Set<AvailableBoatPeriod> availableBoatPeriods){
        Set<AvailablePeriodDto> periods = new HashSet<>();
        for(AvailableBoatPeriod availableBoatPeriod: availableBoatPeriods){
            periods.add(availableBoatPeriodToAvailablePeriodDto(availableBoatPeriod));
        }
        return periods;
    }
    public Set<AvailablePeriodDto> availableCabinPeriodsToDtos(Set<AvailableCabinPeriod> availableCabinPeriods){
        Set<AvailablePeriodDto> periods = new HashSet<>();
        for(AvailableCabinPeriod availableCabinPeriod: availableCabinPeriods){
            periods.add(availableCabinPeriodToAvailablePeriodDto(availableCabinPeriod));
        }
        return periods;
    }

    public  AvailableInstructorPeriod availablePeriodDtoToAvailableInstructorPeriod(AvailablePeriodDto availablePeriodDto, FishingInstructor fishingInstructor){
        return  new AvailableInstructorPeriod(availablePeriodDto.getId(), availablePeriodDto.getStartDate(), availablePeriodDto.getEndDate(),fishingInstructor);
    }
    public  AvailableBoatPeriod availablePeriodDtoToAvailableBoatPeriod(AvailablePeriodDto availablePeriodDto, BoatOwner boatOwner, Boat boat){
        return  new AvailableBoatPeriod(availablePeriodDto.getId(), availablePeriodDto.getStartDate(), availablePeriodDto.getEndDate(),boatOwner,boat);
    }
    public  AvailableCabinPeriod availablePeriodDtoToAvailableCabinPeriod(AvailablePeriodDto availablePeriodDto, CabinOwner cabinOwner, Cabin cabin){
        return  new AvailableCabinPeriod(availablePeriodDto.getId(), availablePeriodDto.getStartDate(), availablePeriodDto.getEndDate(),cabinOwner,cabin);
    }

    public Set<AvailableInstructorPeriod> availableDtosToAvailableInstructorPeriods(Set<AvailablePeriodDto> availablePeriodDtoSet, FishingInstructor fishingInstructor){
        Set<AvailableInstructorPeriod> availableInstructorPeriods = new HashSet<>();
        for(AvailablePeriodDto availablePeriodDto : availablePeriodDtoSet){
            availableInstructorPeriods.add(availablePeriodDtoToAvailableInstructorPeriod(availablePeriodDto,fishingInstructor));
        }
        return availableInstructorPeriods;
    }
    public Set<AvailableBoatPeriod> availableDtosToAvailableBoatPeriods(Set<AvailablePeriodDto> availablePeriodDtos, BoatOwner boatOwner,Boat boat){
        Set<AvailableBoatPeriod> availableBoatPeriods = new HashSet<>();
        for(AvailablePeriodDto availablePeriodDto : availablePeriodDtos){
            availableBoatPeriods.add(availablePeriodDtoToAvailableBoatPeriod(availablePeriodDto,boatOwner,boat));
        }
        return availableBoatPeriods;
    }
    public Set<AvailableCabinPeriod> availableDtosToAvailableCabinPeriods(Set<AvailablePeriodDto> availablePeriodDtos, CabinOwner cabinOwner,Cabin cabin){
        Set<AvailableCabinPeriod> availableCabinPeriods = new HashSet<>();
        for(AvailablePeriodDto availablePeriodDto : availablePeriodDtos){
            availableCabinPeriods.add(availablePeriodDtoToAvailableCabinPeriod(availablePeriodDto,cabinOwner,cabin));
        }
        return availableCabinPeriods;
    }

}

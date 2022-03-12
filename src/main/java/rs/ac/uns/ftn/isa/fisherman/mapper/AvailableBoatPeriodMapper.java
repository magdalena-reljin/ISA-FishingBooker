package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AvailablePeriodDto;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableBoatPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;
import java.util.HashSet;
import java.util.Set;

public class AvailableBoatPeriodMapper {
    public AvailablePeriodDto availableBoatPeriodToAvailablePeriodDto(AvailableBoatPeriod availableBoatPeriod){
        return new AvailablePeriodDto(availableBoatPeriod.getId(),availableBoatPeriod.getStartDate(),
                availableBoatPeriod.getEndDate(),availableBoatPeriod.getBoatOwner().getUsername(),availableBoatPeriod.getBoat().getId());
    }
    public Set<AvailablePeriodDto> availableBoatPeriodsToDtos(Set<AvailableBoatPeriod> availableBoatPeriods){
        Set<AvailablePeriodDto> periods = new HashSet<>();
        for(AvailableBoatPeriod availableBoatPeriod: availableBoatPeriods){
            periods.add(availableBoatPeriodToAvailablePeriodDto(availableBoatPeriod));
        }
        return periods;
    }
    public  AvailableBoatPeriod availablePeriodDtoToAvailableBoatPeriod(AvailablePeriodDto availablePeriodDto, BoatOwner boatOwner, Boat boat){
        return  new AvailableBoatPeriod(availablePeriodDto.getId(), availablePeriodDto.getStartDate(), availablePeriodDto.getEndDate(),boatOwner,boat);
    }
    public Set<AvailableBoatPeriod> availableDtosToAvailableBoatPeriods(Set<AvailablePeriodDto> availablePeriodDtos, BoatOwner boatOwner,Boat boat){
        Set<AvailableBoatPeriod> availableBoatPeriods = new HashSet<>();
        for(AvailablePeriodDto availablePeriodDto : availablePeriodDtos){
            availableBoatPeriods.add(availablePeriodDtoToAvailableBoatPeriod(availablePeriodDto,boatOwner,boat));
        }
        return availableBoatPeriods;
    }

}

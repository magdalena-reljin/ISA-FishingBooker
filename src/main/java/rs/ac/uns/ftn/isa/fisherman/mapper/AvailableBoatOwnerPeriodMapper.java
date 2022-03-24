package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AvailablePeriodDto;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableBoatOwnerPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;

import java.util.HashSet;
import java.util.Set;

public class AvailableBoatOwnerPeriodMapper {
    public Set<AvailableBoatOwnerPeriod> availableDtoSToAvailableBoatOwnerPeriods(HashSet<AvailablePeriodDto> availablePeriodDtoS, BoatOwner boatOwner) {
        Set<AvailableBoatOwnerPeriod> availableBoatOwnerPeriods = new HashSet<>();

        for(AvailablePeriodDto availablePeriodDto : availablePeriodDtoS){
            availableBoatOwnerPeriods.add(availablePeriodDtoToAvailableBoatOwnerPeriod(availablePeriodDto,boatOwner));
        }
        return availableBoatOwnerPeriods;
    }
    public  AvailableBoatOwnerPeriod availablePeriodDtoToAvailableBoatOwnerPeriod(AvailablePeriodDto availablePeriodDto, BoatOwner boatOwner){
        return  new AvailableBoatOwnerPeriod(availablePeriodDto.getId(), availablePeriodDto.getStartDate(), availablePeriodDto.getEndDate(),boatOwner);
    }

    public AvailablePeriodDto availableBoatOwnerPeriodToAvailablePeriodDto(AvailableBoatOwnerPeriod availableBoatOwnerPeriod){
        return new AvailablePeriodDto(availableBoatOwnerPeriod.getId(),availableBoatOwnerPeriod.getStartDate(),
                availableBoatOwnerPeriod.getEndDate(),availableBoatOwnerPeriod.getBoatOwner().getUsername());
    }

    public Set<AvailablePeriodDto> availableBoatOwnerPeriodsToDtoS(Set<AvailableBoatOwnerPeriod> availableBoatOwnerPeriods){
        Set<AvailablePeriodDto> periods = new HashSet<>();
        for(AvailableBoatOwnerPeriod availableBoatOwnerPeriod: availableBoatOwnerPeriods){
            periods.add(availableBoatOwnerPeriodToAvailablePeriodDto(availableBoatOwnerPeriod));
        }
        return periods;
    }
}

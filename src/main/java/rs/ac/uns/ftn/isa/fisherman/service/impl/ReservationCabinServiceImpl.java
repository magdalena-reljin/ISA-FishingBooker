package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.SearchAvailablePeriodsCabinDto;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableCabinPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableCabinPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationCabinService;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReservationCabinServiceImpl implements ReservationCabinService {

    @Autowired
    private AvailableCabinPeriodService availableCabinPeriodService;

    @Override
    public Set<Cabin> getAvailableCabins(SearchAvailablePeriodsCabinDto searchAvailablePeriodsCabinDto) {
        Set<Cabin> availableCabins = new HashSet<>();
        System.out.println("USAO U SERVIS");
        for(AvailableCabinPeriod cabinPeriod:availableCabinPeriodService.findAll()){
            System.out.println("USAO U petlju");
            System.out.println(cabinPeriod.getCabin().getId());
            if(searchAvailablePeriodsCabinDto.getStartDate().isBefore(cabinPeriod.getStartDate())
                    ||searchAvailablePeriodsCabinDto.getEndDate().isAfter(cabinPeriod.getEndDate())) {
                continue;
            }
            if(searchAvailablePeriodsCabinDto.getPrice()!=0){
                if(cabinPeriod.getCabin().getPrice()>searchAvailablePeriodsCabinDto.getPrice())
                    continue;
            }
            if(searchAvailablePeriodsCabinDto.getBedsPerRoom()>cabinPeriod.getCabin().getBedsPerRoom())
                continue;
            if(searchAvailablePeriodsCabinDto.getNumberOfRooms()>cabinPeriod.getCabin().getNumOfRooms())
                continue;
            availableCabins.add(cabinPeriod.getCabin());
        }
        return availableCabins;
    }
}

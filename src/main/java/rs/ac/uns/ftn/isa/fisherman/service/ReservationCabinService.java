package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.SearchAvailablePeriodsCabinDto;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;

import java.util.Set;

public interface ReservationCabinService {

    Set<Cabin> getAvailableCabins(SearchAvailablePeriodsCabinDto searchAvailablePeriodsCabinDto);
    boolean makeReservation(CabinReservationDto cabinReservationDto);

    boolean ownerCreates(CabinReservation cabinReservation, String clientUsername);
}

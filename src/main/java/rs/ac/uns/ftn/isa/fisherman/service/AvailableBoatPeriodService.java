package rs.ac.uns.ftn.isa.fisherman.service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableBoatOwnerPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableBoatPeriod;

import java.util.Set;

public interface AvailableBoatPeriodService {

    Set<AvailableBoatPeriod> getAvailablePeriod(Long id);

    void setAvailableBoatPeriod(Set<AvailableBoatPeriod> availableBoatPeriods);


}

package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.AvailableBoatOwnerPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;

import java.util.Set;

public interface AvailableBoatOwnersPeriodService {
    Set<AvailableBoatOwnerPeriod> getAvailablePeriodByOwnersUsername(String username);

    void setAvailableBoatOwnersPeriod(Set<AvailableBoatOwnerPeriod> availableBoatOwnerPeriod, BoatOwner boatOwner);
}

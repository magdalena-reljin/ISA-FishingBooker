package rs.ac.uns.ftn.isa.fisherman.service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableCabinPeriod;

import java.util.List;
import java.util.Set;

public interface AvailableCabinPeriodService {
    Set<AvailableCabinPeriod> getAvailablePeriod(Long id);

    void setAvailableCabinPeriod(Set<AvailableCabinPeriod> availableCabinPeriod);

    List<AvailableCabinPeriod> findAll();
}

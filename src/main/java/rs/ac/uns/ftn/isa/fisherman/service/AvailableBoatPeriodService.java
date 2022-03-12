package rs.ac.uns.ftn.isa.fisherman.service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableBoatPeriod;
import java.util.Set;

public interface AvailableBoatPeriodService {

    Set<AvailableBoatPeriod> getAvailablePeriod(Long id);

    void setAvailableBoatPeriod(Long id, Set<AvailableBoatPeriod> availableBoatPeriod);
}

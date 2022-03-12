package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableBoatPeriod;
import rs.ac.uns.ftn.isa.fisherman.repository.AvailableBoatPeriodRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableBoatPeriodService;

import java.util.Set;

@Service
public class AvailableBoatPeriodServiceImpl implements AvailableBoatPeriodService {
    @Autowired
    private AvailableBoatPeriodRepository availableBoatPeriodRepository;
    @Override
    public Set<AvailableBoatPeriod> getAvailablePeriod(Long id) {
        return  availableBoatPeriodRepository.findByBoatId(id);
    }

    @Override
    public void setAvailableBoatPeriod(Set<AvailableBoatPeriod> availableBoatPeriods) {
            for(AvailableBoatPeriod availablePeriod: availableBoatPeriods)
                availableBoatPeriodRepository.save(availablePeriod);
    }
}

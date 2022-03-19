package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableCabinPeriod;
import rs.ac.uns.ftn.isa.fisherman.repository.AvailableCabinPeriodRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableCabinPeriodService;

import java.util.List;
import java.util.Set;
@Service
public class AvailableCabinPeriodServiceImpl implements AvailableCabinPeriodService {
    @Autowired
    private AvailableCabinPeriodRepository availableCabinPeriodRepository;

    @Override
    public Set<AvailableCabinPeriod> getAvailablePeriod(Long id) {
        return  availableCabinPeriodRepository.findByCabinId(id);
    }

    @Override
    public void setAvailableCabinPeriod(Set<AvailableCabinPeriod> availableCabinPeriod) {
        for(AvailableCabinPeriod availablePeriods:availableCabinPeriod ) {
            availableCabinPeriodRepository.save(availablePeriods);
        }

    }

    @Override
    public List<AvailableCabinPeriod> findAll() {
        return availableCabinPeriodRepository.findAll();
    }
}

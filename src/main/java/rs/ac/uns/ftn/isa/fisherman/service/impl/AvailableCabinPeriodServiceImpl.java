package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableCabinPeriod;
import rs.ac.uns.ftn.isa.fisherman.repository.AvailableCabinPeriodRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableCabinPeriodService;

import java.util.Set;
@Service
public class AvailableCabinPeriodServiceImpl implements AvailableCabinPeriodService {
    @Autowired
    private AvailableCabinPeriodRepository availableCabinPeriodRepository;
    @Override
    public Set<AvailableCabinPeriod> getAvailablePeriod(Long id) {
        return null;
    }

    @Override
    public void setAvailableCabinPeriod(Long id, Set<AvailableCabinPeriod> availableCabinPeriod) {

    }
}
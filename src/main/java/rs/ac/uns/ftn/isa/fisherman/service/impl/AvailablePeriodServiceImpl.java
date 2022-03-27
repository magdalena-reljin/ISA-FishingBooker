package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailablePeriod;
import rs.ac.uns.ftn.isa.fisherman.repository.AvailablePeriodRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailablePeriodService;

@Service
public class AvailablePeriodServiceImpl implements AvailablePeriodService {
    @Autowired
    private AvailablePeriodRepository availablePeriodRepository;
    @Override
    public void deleteAvailablePeriod(Long id) {
        AvailablePeriod availablePeriodToDelete=availablePeriodRepository.findByPeriodId(id);
        System.out.println("``````````````````````````````````````````````````````````` nasao sam ovo"
        +availablePeriodToDelete.getId());
        availablePeriodRepository.deleteById(id);
    }
}

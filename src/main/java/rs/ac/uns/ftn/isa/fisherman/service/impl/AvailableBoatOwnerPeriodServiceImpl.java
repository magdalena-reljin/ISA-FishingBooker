package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableBoatOwnerPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;
import rs.ac.uns.ftn.isa.fisherman.repository.AvailableBoatOwnerPeriodRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableBoatOwnersPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnerService;

import java.util.Set;
@Service
public class AvailableBoatOwnerPeriodServiceImpl implements AvailableBoatOwnersPeriodService {
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private AvailableBoatOwnerPeriodRepository availableBoatOwnerPeriodRepository;
    @Override
    public Set<AvailableBoatOwnerPeriod> getAvailablePeriodByOwnersUsername(String username) {
        Long ownerId= boatOwnerService.findByUsername(username).getId();
        System.out.println("******************************************************************************************* usao u get"+ownerId);
        for(AvailableBoatOwnerPeriod av : availableBoatOwnerPeriodRepository.findByOwnersId(ownerId) )
            System.out.println("usao u for    "+av.getClass());
        return  availableBoatOwnerPeriodRepository.findByOwnersId(ownerId);
    }

    @Override
    public void setAvailableBoatOwnersPeriod(Set<AvailableBoatOwnerPeriod> availableBoatOwnerPeriod, BoatOwner boatOwner) {
        BoatOwner boatOwner1 = boatOwnerService.findByUsername(boatOwner.getUsername());
        boatOwner1.setAvailableBoatOwnerPeriods(availableBoatOwnerPeriod);
        boatOwnerService.save(boatOwner1);
    }
}

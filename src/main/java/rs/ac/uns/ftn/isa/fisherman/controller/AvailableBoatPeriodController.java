package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.*;
import rs.ac.uns.ftn.isa.fisherman.mapper.AvailableBoatPeriodMapper;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableBoatPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/boatsPeriod", produces = MediaType.APPLICATION_JSON_VALUE)
public class AvailableBoatPeriodController {
    @Autowired
    private AvailableBoatPeriodService availableBoatPeriodService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    @Autowired
    private BoatService boatService;
    private final AvailableBoatPeriodMapper availableBoatPeriodMapper= new AvailableBoatPeriodMapper();

    @PostMapping("/getAvailablePeriod")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<Set<AvailablePeriodDto>> getAvailablePeriod (@RequestBody BoatDto boatDto) {
        Set<AvailablePeriodDto> availableBoatPeriods= new HashSet<>();
        for(AvailableBoatPeriod availableBoatPeriod:availableBoatPeriodService.getAvailablePeriod(boatDto.getId())){
           availableBoatPeriods.add(availableBoatPeriodMapper.availableBoatPeriodToAvailablePeriodDto(availableBoatPeriod));
        }
        return new ResponseEntity<>(availableBoatPeriods, HttpStatus.OK);
    }

    @PostMapping("/setAvailableBoatsPeriod")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<String> setAvailableBoatsPeriod (@RequestBody List<AvailablePeriodDto> availablePeriodDto) {
        BoatOwner boatOwner= boatOwnerService.findByUsername(availablePeriodDto.get(0).getUsername());
        Boat boat = boatService.findById(availablePeriodDto.get(0).getPropertyId());
        Set<AvailableBoatPeriod> availableBoatPeriods = availableBoatPeriodMapper
                .availableDtoSToAvailableBoatPeriods(new HashSet<>(availablePeriodDto),boatOwner,boat);
        availableBoatPeriodService.setAvailableBoatPeriod(availableBoatPeriods);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

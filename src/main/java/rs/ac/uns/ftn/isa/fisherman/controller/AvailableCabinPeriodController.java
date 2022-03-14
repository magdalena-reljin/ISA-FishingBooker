package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.AvailablePeriodDto;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinDto;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.mapper.AvailableCabinPeriodMapper;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableCabinPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableInstructorPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableCabinPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/cabinsPeriod", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AvailableCabinPeriodController {

    @Autowired
   private AvailableCabinPeriodService availableCabinPeriodService;
    @Autowired
    private CabinOwnerService cabinOwnerService;
    @Autowired
    private CabinService cabinService;

    private AvailableCabinPeriodMapper availableCabinPeriodMapper= new AvailableCabinPeriodMapper();



    @PostMapping("/getAvailablePeriod")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<Set<AvailablePeriodDto>> getAvailablePeriod (@RequestBody CabinDto cabinDto) {
        Set<AvailablePeriodDto> availableCabinPeriods= new HashSet<>();
        for(AvailableCabinPeriod availableCabinPeriod:availableCabinPeriodService.getAvailablePeriod(cabinDto.getId())){
           availableCabinPeriods.add(availableCabinPeriodMapper.availableCabinPeriodToAvailablePeriodDto(availableCabinPeriod));
        }
        return new ResponseEntity<>(availableCabinPeriods, HttpStatus.OK);
    }

    @PostMapping("/setAvailableCabinsPeriod")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<String> setAvailableCabinsPeriod (@RequestBody List<AvailablePeriodDto> availablePeriodDto) {
        CabinOwner cabinOwner= cabinOwnerService.findByUsername(availablePeriodDto.get(0).getUsername());
        Cabin cabin = cabinService.findById(availablePeriodDto.get(0).getPropertyId());
        Set<AvailableCabinPeriod> availableCabinPeriods = availableCabinPeriodMapper
                .availableDtosToAvailableCabinPeriods(new HashSet<>(availablePeriodDto),cabinOwner,cabin);
        availableCabinPeriodService.setAvailableCabinPeriod(availableCabinPeriods);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}

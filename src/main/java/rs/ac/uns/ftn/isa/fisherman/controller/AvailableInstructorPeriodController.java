package rs.ac.uns.ftn.isa.fisherman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureDto;
import rs.ac.uns.ftn.isa.fisherman.dto.AvailablePeriodDto;
import rs.ac.uns.ftn.isa.fisherman.dto.FishingInstructorDto;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.mapper.AvailableInstructorPeriodMapper;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableInstructorPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableInstructorPeriodService;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/instructorsPeriod", produces = MediaType.APPLICATION_JSON_VALUE)
public class AvailableInstructorPeriodController {
    @Autowired
    private AvailableInstructorPeriodService availableInstructorPeriodService;
    @Autowired
    private FishingInstructorService fishingInstructorService;

    private final AvailableInstructorPeriodMapper availableInstructorPeriodMapper = new AvailableInstructorPeriodMapper();

    @GetMapping("/getAvailablePeriod/{username:.+}/")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<Set<AvailablePeriodDto>> getAvailablePeriod (@PathVariable ("username") String username) {
        Set<AvailablePeriodDto> periods= new HashSet<>();
        for(AvailableInstructorPeriod availableInstructorPeriod: availableInstructorPeriodService.getAvailablePeriod(username))
            periods.add(availableInstructorPeriodMapper.availableInstructorPeriodToAvailablePeriodDto(availableInstructorPeriod));
        return new ResponseEntity<>(periods, HttpStatus.OK);
    }


    @PostMapping("/setAvailableInstructorPeriod")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<String> setAvailableInstructorPeriod(@RequestBody AvailablePeriodDto availablePeriodDto){
        FishingInstructor fishingInstructor= fishingInstructorService.findByUsername(availablePeriodDto.getUsername());
        AvailableInstructorPeriod availableInstructorPeriod= availableInstructorPeriodMapper
                .availablePeriodDtoToAvailableInstructorPeriod(availablePeriodDto,fishingInstructor);
        if(availableInstructorPeriodService.setAvailableInstructorPeriod(availableInstructorPeriod))
            return new ResponseEntity<>("Success", HttpStatus.OK);
        else
            return new ResponseEntity<>("Already exists", HttpStatus.BAD_REQUEST);
    }




}

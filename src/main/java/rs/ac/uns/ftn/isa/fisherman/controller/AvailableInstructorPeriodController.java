package rs.ac.uns.ftn.isa.fisherman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.AvailableInstructorPeriodDto;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.mapper.AvailableInstructorPeriodMapper;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableInstructorPeriod;
import rs.ac.uns.ftn.isa.fisherman.service.AvailableInstructorPeriodService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/instructorsPeriod", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AvailableInstructorPeriodController {
    @Autowired
    private AvailableInstructorPeriodService availableInstructorPeriodService;

   private  AvailableInstructorPeriodMapper availableInstructorPeriodMapper= new AvailableInstructorPeriodMapper();

    @PostMapping("/getAvailablePeriod")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<Set<AvailableInstructorPeriodDto>> getAvailablePeriod (@RequestBody UserRequestDTO userRequestDto) {
        Set<AvailableInstructorPeriodDto> periods= new HashSet<>();
        for(AvailableInstructorPeriod availableInstructorPeriod: availableInstructorPeriodService.getAvailablePeriod(userRequestDto.getUsername()))
                periods.add(availableInstructorPeriodMapper.availablePeriodToAvailablePeriodDto(availableInstructorPeriod));
        return new ResponseEntity<>(periods, HttpStatus.OK);
    }
}

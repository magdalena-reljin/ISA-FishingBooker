package rs.ac.uns.ftn.isa.fisherman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureDto;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinDto;
import rs.ac.uns.ftn.isa.fisherman.dto.FishingInstructorDto;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdventureMapper;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureService;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/instructors", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class FishingInstructorController {

    @Autowired
    private FishingInstructorService fishingInstructorService;


    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/findInstructorRatingByUsername")
    public ResponseEntity<Double> findInstructorRatingByUsername(@RequestBody FishingInstructorDto instructor){
       Double instructorRating= fishingInstructorService.findByUsername(instructor.getUsername()).getRating();
        return new ResponseEntity<>(instructorRating, HttpStatus.OK);
    }



}

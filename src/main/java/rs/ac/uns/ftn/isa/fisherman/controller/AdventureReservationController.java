package rs.ac.uns.ftn.isa.fisherman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.BoatReservationDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdventureMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdventureReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureReservationService;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/reservationAdventure", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureReservationController {
 @Autowired
 private AdventureReservationService adventureReservationService;
 @Autowired
 private FishingInstructorService fishingInstructorService;
 private AdventureReservationMapper adventureReservationMapper= new AdventureReservationMapper();
 private AdventureMapper adventureMapper = new AdventureMapper();

    @PostMapping("/instructorCreates")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<String> instructorCreates (@RequestBody AdventureReservationDto adventureReservationDto) {
        Adventure adventure = adventureMapper.adventureDtoToAdventure(adventureReservationDto.getAdventureDto());
        FishingInstructor fishingInstructor= fishingInstructorService.findByUsername(adventureReservationDto.getAdventureDto().getFishingInstructorUsername());
        AdventureReservation adventureReservation = adventureReservationMapper.adventureReservationDtoToAdventureReservation(adventureReservationDto);
        adventureReservation.setAdventure(adventure);
        adventureReservation.setFishingInstructor(fishingInstructor);
        if(adventureReservationService.instructorCreates(adventureReservation,adventureReservationDto.getClientUsername())) {

            return new ResponseEntity<>("Success.", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Unsuccessfull reservation.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value= "/getByInstructorId/{username:.+}/")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<Set<AdventureReservationDto>> getPresentByInstructorId(@PathVariable ("username")String username) {
        Long instructorId= fishingInstructorService.findByUsername(username).getId();
        Set<AdventureReservationDto> adventureReservations= new HashSet<>();
        for(AdventureReservation adventureReservation: adventureReservationService.getPresentByInstructorId(instructorId))
        adventureReservations.add(adventureReservationMapper.adventureReservationToAdventureReservationDto(adventureReservation));
        return new ResponseEntity<>(adventureReservations,HttpStatus.OK);
    }
}

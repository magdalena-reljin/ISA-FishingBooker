package rs.ac.uns.ftn.isa.fisherman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdventureMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdventureReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationAdventureService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/quickReservationAdventure", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuickReservationAdventureController {
    @Autowired
    private QuickReservationAdventureService quickReservationAdventureService;
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
        if(quickReservationAdventureService.instructorCreates(adventureReservation)) {

            return new ResponseEntity<>("Success.", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Unsuccessfull reservation.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value= "/getByInstructorId/{username:.+}/")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<Set<AdventureReservationDto>> getPresentByCabinId(@PathVariable ("username")String username) {
        Long instructorId= fishingInstructorService.findByUsername(username).getId();
        Set<AdventureReservationDto> reservationDtos= new HashSet<>();
        for(QuickReservationAdventure quickReservationAdventure: quickReservationAdventureService.getByInstructorId(instructorId))
            reservationDtos.add(adventureReservationMapper.quickAdventureReservationToQuickAdventureReservationDto(quickReservationAdventure));
        return new ResponseEntity<>(reservationDtos,HttpStatus.OK);
    }

}

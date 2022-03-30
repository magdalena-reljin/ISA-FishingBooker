package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.BoatReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.BoatReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationBoatService;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationCabinService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/quickReservationBoat", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuickReservationBoatController {
    @Autowired
    private QuickReservationBoatService quickReservationBoatService;
    @Autowired
    private BoatOwnerService boatOwnerService;
    private final BoatReservationMapper boatReservationMapper= new BoatReservationMapper();

    @PostMapping("/ownerCreates/{username:.+}/")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<String> ownerCreates (@PathVariable("username") String username,@RequestBody BoatReservationDto boatReservationDto) {
        BoatReservation boatReservation= boatReservationMapper.boatReservationOwnerDtoToBoatReservation(boatReservationDto);
        BoatOwner boatOwner= boatOwnerService.findByUsername(username);
        boatReservation.getBoat().setBoatOwner(boatOwner);
        if(quickReservationBoatService.ownerCreates(boatReservation)){
            return new ResponseEntity<>("Success.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Unsuccessfull reservation.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value= "/getByBoatId/{boatId}")
    @PreAuthorize("hasRole('BOATOWNER')")
    public ResponseEntity<Set<BoatReservationDto>> getPresentByBoatId(@PathVariable ("boatId") Long boatId) {
        Set<BoatReservationDto> boatReservationDtos= new HashSet<>();
        for(QuickReservationBoat quickReservationBoat: quickReservationBoatService.getByBoatId(boatId))
            boatReservationDtos.add(boatReservationMapper.quickBoatReservationToBoatReservationDto(quickReservationBoat));
        return new ResponseEntity<>(boatReservationDtos,HttpStatus.OK);
    }
}

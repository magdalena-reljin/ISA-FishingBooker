package rs.ac.uns.ftn.isa.fisherman.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.BoatDto;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinDto;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdditionalServiceMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.BoatMapper;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/boats", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class BoatController {
    @Autowired
    private BoatService boatService;
    @Autowired
    private BoatOwnerService boatOwnerService;

    private BoatMapper boatMapper=new BoatMapper();
    private AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();

    private String success="Success";
    @PreAuthorize("hasRole('BOATOWNER')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody BoatDto boatDto){
        Boolean services=false;
        Boat boat=boatMapper.boatDtoToBoat(boatDto);
        boat.setBoatOwner(boatOwnerService.findByUsername(boatDto.getOwnersUsername()));
        boatService.save(boat);
        if(boatDto.getAdditionalServices()!=null) {
            boat.setAdditionalServices(additionalServiceMapper.AdditionalServicesDtoToAdditionalServices(boatDto.getAdditionalServices()));
            services=true;
        }
        if(services)
            boatService.save(boat);
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('BOATOWNER')")
    @PostMapping("/findBoatsByOwnersUsername")
    public ResponseEntity<Set<BoatDto>> getByOwnerId(@RequestBody UserRequestDTO owner){
        Set<BoatDto> boats=new HashSet<>();
        for(Boat boat: boatService.findByOwnersId(boatOwnerService.findByUsername(owner.getUsername()).getId()))
            boats.add(boatMapper.boatToBoatDto(boat));
        return new ResponseEntity<>(boats,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('BOATOWNER')")
    @PostMapping("/findByName")
    public ResponseEntity<BoatDto> findByName(@RequestBody BoatDto boatDto){
        Long boatOwner= boatOwnerService.findByUsername(boatDto.getOwnersUsername()).getId();
        String boatName= boatDto.getName();
        Boat boat= boatService.findByNameAndOwner(boatName,boatOwner);
        return new ResponseEntity<>(boatMapper.boatToBoatDto(boat), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('BOATOWNER')")
    @PostMapping("/edit")
    public ResponseEntity<String> edit(@RequestBody BoatDto boatDto){
        BoatOwner owner=boatOwnerService.findByUsername(boatDto.getOwnersUsername());
        Boat boat=boatMapper.boatDtoToBoatEdit(boatDto);
        boat.setBoatOwner(owner);
        Boolean deleteOldImages=false;
        if(boatDto.getImages()==null)
            deleteOldImages=true;
        boatService.edit(boat,deleteOldImages);
        return new ResponseEntity<>(success,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('BOATOWNER')")
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody BoatDto boatDto){
        Boat boat=boatMapper.boatDtoToBoat(boatDto);
        boatService.delete(boat.getId());
        return new ResponseEntity<>(success,HttpStatus.OK);
    }
}

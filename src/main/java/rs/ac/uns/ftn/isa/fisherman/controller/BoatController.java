package rs.ac.uns.ftn.isa.fisherman.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.BoatDto;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdditionalServiceMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.BoatMapper;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.BoatService;
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
        Boat boat=boatMapper.BoatDtoToBoat(boatDto);
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
}

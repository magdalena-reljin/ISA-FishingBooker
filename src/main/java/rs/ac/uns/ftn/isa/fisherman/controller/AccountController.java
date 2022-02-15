package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.mapper.BoatOwnerMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinOwnerMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.FishingInstructorMapper;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.model.User;
import rs.ac.uns.ftn.isa.fisherman.service.BoatOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.FishingInstructorService;
import rs.ac.uns.ftn.isa.fisherman.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin

public class AccountController {


    @Autowired
    private UserService userService;

    @Autowired
    private CabinOwnerService cabinOwnerService;

    @Autowired
    private FishingInstructorService fishingInstructorService;

    @Autowired
    private BoatOwnerService boatOwnerService;

    public  AccountController(){}
    private CabinOwnerMapper cabinOwnerMapper = new CabinOwnerMapper();
    private BoatOwnerMapper boatOwnerMapper = new BoatOwnerMapper();
    private FishingInstructorMapper fishingInstructorMapper = new FishingInstructorMapper();



    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<Iterable<UserRequestDTO>>getAllUsers(){
        List<UserRequestDTO> allUsers=new ArrayList<UserRequestDTO>();
        for(CabinOwner cabinOwner: cabinOwnerService.getActiveCabinOwners()) {
            if(cabinOwner.isEnabled())
             allUsers.add(cabinOwnerMapper.CabinOwnerToUserRequestDto(cabinOwner));
        }
        for(BoatOwner boatOwner: boatOwnerService.getActiveBoatOwners()) {
            if(boatOwner.isEnabled())
            allUsers.add(boatOwnerMapper.boatOwnerToUserRequestDto(boatOwner));
        }
        for(FishingInstructor fishingInstructor: fishingInstructorService.getNewActiveInstructors()) {
            if(fishingInstructor.isEnabled())
            allUsers.add(fishingInstructorMapper.fishingInstructorToUserRequestDto(fishingInstructor));
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }
}

package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.dto.VerificationDTO;
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

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)


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
    private String success= "Success!";
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

    @PostMapping("/acceptAccount")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> acceptAccount(HttpServletRequest httpServletRequest, @RequestBody UserRequestDTO userRequest) throws MessagingException {
        userService.acceptAccount(userService.findByUsername(userRequest.getUsername()));

        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }
    @PostMapping("/denyAccount/{reason}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> denyAccount(@PathVariable ("reason") String reason, HttpServletRequest httpServletRequest, @RequestBody UserRequestDTO userRequest) throws MessagingException {
        userService.denyAccount(userService.findByUsername(userRequest.getUsername()),reason);
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }

    @GetMapping("/getNewUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserRequestDTO> getNewUsers() throws MessagingException {
        List<UserRequestDTO> newUsers=new ArrayList<UserRequestDTO>();
        for(CabinOwner cabinOwner: cabinOwnerService.getNewCabinOwners()) {
            newUsers.add(cabinOwnerMapper.CabinOwnerToUserRequestDto(cabinOwner));
        }
        for(BoatOwner boatOwner: boatOwnerService.getNewBoatOwners()) {
            newUsers.add(boatOwnerMapper.boatOwnerToUserRequestDto(boatOwner));
        }
        for(FishingInstructor fishingInstructor: fishingInstructorService.getNewFishingInstructors()) {
            newUsers.add(fishingInstructorMapper.fishingInstructorToUserRequestDto(fishingInstructor));
        }
        return newUsers;
    }

    @PostMapping("/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> activate(@RequestBody VerificationDTO verificationDTO) {
        String email = verificationDTO.getEmail();
        String code = verificationDTO.getActivationCode();

        if(this.userService.activateAccount(email, code) != null){
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
    }
}

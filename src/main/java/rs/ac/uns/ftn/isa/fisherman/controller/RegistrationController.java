package rs.ac.uns.ftn.isa.fisherman.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.RegistrationDTO;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinOwnerMapper;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.service.RegistrationService;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/registration",  produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    private CabinOwnerMapper cabinOwnerMapper;

    @PostMapping(value = "/register")
    public ResponseEntity<RegistrationDTO> register(@RequestBody RegistrationDTO registrationDTO) {
        System.out.println("POGODIO SAM GAAAAAAAAAAAAAAAAAAAAAAAA");
        CabinOwner cabinOwner = cabinOwnerMapper.RegistrationDTOToCabinOwner(registrationDTO);
        registrationService.RegisterCabinOwner(cabinOwner);
        return new ResponseEntity<>(registrationDTO, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<RegistrationDTO>> getCourses() {
        System.out.println("uspeoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        List<RegistrationDTO> lista=new ArrayList<RegistrationDTO>();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}

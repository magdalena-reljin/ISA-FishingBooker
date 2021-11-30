package rs.ac.uns.ftn.isa.fisherman.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinOwnerDTO;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinOwnerMapper;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.service.RegistrationService;
@RestController
@RequestMapping(value = "api/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    private CabinOwnerMapper cabinOwnerMapper;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CabinOwnerDTO> saveCourse(@RequestBody CabinOwnerDTO cabinOwnerDTO) {

        CabinOwner cabinOwner = cabinOwnerMapper.DTOToModel(cabinOwnerDTO);
        registrationService.RegisterCabinOwner(cabinOwner);
        return new ResponseEntity<>(cabinOwnerDTO, HttpStatus.CREATED);
    }
}

package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinDto;
import rs.ac.uns.ftn.isa.fisherman.dto.ImageDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.AdditionalServiceMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.ImageMapper;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerService;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;
import rs.ac.uns.ftn.isa.fisherman.service.FirebaseService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/cabins", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CabinController {
    @Autowired
    private CabinService cabinService;
    @Autowired
    private CabinOwnerService cabinOwnerService;
    @Autowired
    private FirebaseService firebaseService;
    private CabinMapper cabinMapper=new CabinMapper();
    private AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();
    private ImageMapper imageMapper=new ImageMapper();
    private String success="Success";

    @PreAuthorize("hasRole('CABINOWNER')")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody CabinDto cabinDto){
         System.out.println("pogodio sam save u cabinnnnnnnnn"+ cabinDto.getDescription());
        Boolean services=false;
        Cabin cabin=cabinMapper.CabinDtoToCabin(cabinDto);
        cabin.setCabinOwner(cabinOwnerService.findByUsername(cabinDto.getOwnerUsername()));
        cabinService.save(cabin);

        if(cabinDto.getAdditionalServices()!=null) {
            cabin.setAdditionalServices(additionalServiceMapper.AdditionalServicesDtoToAdditionalServices(cabinDto.getAdditionalServices()));
            services=true;
        }
        if(services)
        cabinService.save(cabin);
        return new ResponseEntity<>(success,HttpStatus.CREATED);
    }
}

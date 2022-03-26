package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.service.QuickReservationCabinService;

@RestController
@RequestMapping(value = "/quickReservationCabin", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuickReservationCabinController {
    @Autowired
    private QuickReservationCabinService quickReservationCabinService;
    private final CabinReservationMapper cabinReservationMapper= new CabinReservationMapper();
    @PostMapping("/ownerCreates")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<String> ownerCreates (@RequestBody CabinReservationDto cabinReservationDto) {
        CabinReservation cabinReservation= cabinReservationMapper.cabinOwnerReservationDtoToCabinReservation(cabinReservationDto);
        if(quickReservationCabinService.ownerCreates(cabinReservation)){
            return new ResponseEntity<>("Success.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Unsuccessfull reservation.", HttpStatus.BAD_REQUEST);
        }
    }

}

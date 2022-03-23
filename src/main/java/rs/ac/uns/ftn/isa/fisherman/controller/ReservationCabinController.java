package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinDto;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.SearchAvailablePeriodsCabinDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinReservationMapper;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinMapper;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.service.ClientService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationCabinService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/reservationCabin", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ReservationCabinController {

    @Autowired
    private ReservationCabinService reservationCabinService;
    private CabinMapper cabinMapper = new CabinMapper();

    @PostMapping("/getAvailableCabins")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Set<CabinDto>> getAvailableCabins (@RequestBody SearchAvailablePeriodsCabinDto searchAvailablePeriodsCabinDto) {
        Set<CabinDto> cabinsDto= new HashSet<CabinDto>();
        for(Cabin cabin:reservationCabinService.getAvailableCabins(searchAvailablePeriodsCabinDto)){
            cabinsDto.add(cabinMapper.CabinToCabinDto(cabin));
        }
        return new ResponseEntity<>(cabinsDto, HttpStatus.OK);
    }

    @PostMapping("/makeReservation")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<String> makeReservation (@RequestBody CabinReservationDto cabinReservationDto) {
        if(reservationCabinService.makeReservation(cabinReservationDto))
            return new ResponseEntity<>("Success.", HttpStatus.OK);
        else
            return new ResponseEntity<>("Unsuccessfull reservatioon.", HttpStatus.BAD_REQUEST);
    }
}

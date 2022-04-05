package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.StatisticsReportDto;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;

@RestController
@RequestMapping(value = "/statisticsReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticsReportController {
    @GetMapping("/findCabinStatistics/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<StatisticsReportDto> findCabinStatistics (@PathVariable("username") String username) {
        StatisticsReportDto cabinStats=new StatisticsReportDto();
        return new ResponseEntity<>(cabinStats, HttpStatus.OK);
    }
}

package rs.ac.uns.ftn.isa.fisherman.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.ReservationsPointsDto;
import rs.ac.uns.ftn.isa.fisherman.dto.StatisticsReportDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.ReservationPointsMapper;
import rs.ac.uns.ftn.isa.fisherman.model.ReservationPoints;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationPointsService;
import rs.ac.uns.ftn.isa.fisherman.service.UserService;

@RestController
@RequestMapping(value = "/statisticsReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticsReportController {
    @Autowired
    private ReservationPointsService reservationPointsService;
    @Autowired
    private UserService userService;
    @Autowired
    private CabinService cabinService;
    private final ReservationPointsMapper reservationPointsMapper=new ReservationPointsMapper();

    @GetMapping("/findCabinStatistics/{username:.+}/")
    @PreAuthorize("hasRole('CABINOWNER')")
    public ResponseEntity<StatisticsReportDto> findCabinStatistics (@PathVariable("username") String username) {
        StatisticsReportDto cabinStats=new StatisticsReportDto();
        ReservationPoints reservationPoints=reservationPointsService.get();
        cabinStats.setReservationsPointsDto(reservationPointsMapper.reservationsPointsToDto(reservationPoints));
        cabinStats.setOwnerRating(userService.findRatingByUsername(username));
        Long ownerId=  userService.findByUsername(username).getId();
        cabinStats.setAvgRating(cabinService.findAvgCabinRatingByOwnerId(ownerId));
        return new ResponseEntity<>(cabinStats, HttpStatus.OK);
    }
}

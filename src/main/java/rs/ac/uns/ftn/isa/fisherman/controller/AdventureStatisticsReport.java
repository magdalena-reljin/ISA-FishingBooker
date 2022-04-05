package rs.ac.uns.ftn.isa.fisherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.isa.fisherman.dto.StatisticsReportDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.ReservationPointsMapper;
import rs.ac.uns.ftn.isa.fisherman.model.ReservationPoints;
import rs.ac.uns.ftn.isa.fisherman.service.*;

@RestController
@RequestMapping(value = "/adventureStatisticsReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdventureStatisticsReport {
    @Autowired
    private ReservationPointsService reservationPointsService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdventureService adventureService;
    @Autowired
    private AdventureReservationService adventureReservationService;
    @Autowired
    private QuickReservationAdventureService quickReservationAdventureService;
    @Autowired
    private DateService dateService;
    private final ReservationPointsMapper reservationPointsMapper=new ReservationPointsMapper();

    @GetMapping("/findAdventureStatistics/{username:.+}/")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public ResponseEntity<StatisticsReportDto> findAdventureStatistics (@PathVariable("username") String username) {
        StatisticsReportDto adventureStats=new StatisticsReportDto();
        ReservationPoints reservationPoints=reservationPointsService.get();
        adventureStats.setReservationsPointsDto(reservationPointsMapper.reservationsPointsToDto(reservationPoints));
        adventureStats.setOwnerRating(userService.findRatingByUsername(username));
        Long ownerId=  userService.findByUsername(username).getId();
        //adventureStats.setAvgRating(adventureService.findAvgCabinRatingByOwnerId(ownerId));
        return new ResponseEntity<>(adventureStats, HttpStatus.OK);
    }
}

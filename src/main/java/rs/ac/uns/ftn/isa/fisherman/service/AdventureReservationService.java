package rs.ac.uns.ftn.isa.fisherman.service;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.BoatReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.SearchAvailablePeriodsBoatAndAdventureDto;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservation;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;
import rs.ac.uns.ftn.isa.fisherman.model.BoatReservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface AdventureReservationService {
    boolean instructorCreates(AdventureReservation adventureReservation, String clientUsername);
    Set<AdventureReservation> getPresentByInstructorId(String username);
    boolean reservationExists(String username, LocalDateTime startDate, LocalDateTime endDate);
    boolean futureReservationsExist(LocalDateTime currentDate, Long id);
    Set<AdventureReservation>  getPastReservations(String username);
    Integer countReservationsInPeriod(LocalDateTime start, LocalDateTime end, String username);
    double findReservationsAndSumProfit(String ownerUsername, LocalDateTime start, LocalDateTime end);
    double sumProfitOfPricesCalucatedByHours(List<AdventureReservation> reservations, LocalDateTime start, LocalDateTime end);
    AdventureReservation findById(Long id);
    void save (AdventureReservation adventureReservation);
    Set<Adventure> getAvailableAdventures(SearchAvailablePeriodsBoatAndAdventureDto searchAvailablePeriodsAdventureDto);
    boolean makeReservation(AdventureReservationDto adventureReservationDto);
    Set<AdventureReservation> getUpcomingClientReservationsByUsername(String username);
    Set<AdventureReservation> getClientReservationHistoryByUsername(String username);
    boolean reservationExists(Long id);
    boolean checkIfReservationIsEvaluated(Long reservationId);
    void markThatReservationIsEvaluated(Long reservationId);
    boolean fishingInstructorNotFree(String instructorUsername, LocalDateTime startDate, LocalDateTime endDate);
}

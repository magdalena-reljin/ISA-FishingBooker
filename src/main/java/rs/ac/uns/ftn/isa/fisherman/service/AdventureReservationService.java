package rs.ac.uns.ftn.isa.fisherman.service;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservation;

import java.time.LocalDateTime;
import java.util.Set;

public interface AdventureReservationService {
    boolean instructorCreates(AdventureReservation adventureReservation, String clientUsername);
    Set<AdventureReservation> getPresentByInstructorId(String username);
    boolean reservationExists(String username, LocalDateTime startDate, LocalDateTime endDate);
    boolean futureReservationsExist(LocalDateTime currentDate, Long id);
    Set<AdventureReservation>  getPastReservations(String username);
    Integer countReservationsInPeriod(LocalDateTime start, LocalDateTime end, String username);
    Double sumProfit(String username, LocalDateTime start, LocalDateTime end);
    AdventureReservation findById(Long id);
    void save (AdventureReservation adventureReservation);
}

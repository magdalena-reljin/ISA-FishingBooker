package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.QuickReservationBoatDto;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationBoat;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationCabin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface QuickReservationBoatService {
    boolean ownerCreates(QuickReservationBoat quickReservationBoat);
    Set<QuickReservationBoat> getByBoatId(Long cabinId);
    boolean quickReservationExists(Long id, LocalDateTime startDate, LocalDateTime endDate);
    boolean ownerIsNotAvailableQuickResrvation(String ownerUsername, LocalDateTime start, LocalDateTime end);
    Set<QuickReservationBoat> findReservationsByOwnerUsername(String username);
    boolean futureQuickReservationsExist(LocalDateTime currentDate,Long boatId);
    Set<QuickReservationBoat> getPastReservations(String username);
    Integer countReservationsInPeriod(LocalDateTime start, LocalDateTime end, String username);
    double findReservationsAndSumProfit(String username, LocalDateTime start, LocalDateTime end);
    double sumProfitOfPricesCalculatedByHours(List<QuickReservationBoat> reservations, LocalDateTime start, LocalDateTime end);
    QuickReservationBoat getById(Long reservationId);
    void save(QuickReservationBoat reservation);
    Set<QuickReservationBoat> getAvailableReservations();
    boolean makeQuickReservation(QuickReservationBoatDto quickReservationBoatDto);
    boolean boatHasQuickReservationInPeriod(Long boatId, LocalDateTime startDate, LocalDateTime endDate);
    Set<QuickReservationBoat> getUpcomingClientQuickReservations(String clientUsername);
    Set<QuickReservationBoat> getClientQuickReservationsHistory(String clientUsername);
    List<QuickReservationBoat> findAllQucikReservationsForAdminProfit(LocalDateTime start, LocalDateTime end);
    double sumProfitOfPricesCalculatedByHoursForAdmin(List<QuickReservationBoat> reservations, LocalDateTime start, LocalDateTime end);

}

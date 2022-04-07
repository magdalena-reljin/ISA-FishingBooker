package rs.ac.uns.ftn.isa.fisherman.service;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationCabin;

import java.time.LocalDateTime;
import java.util.Set;

public interface QuickReservationCabinService {
    boolean ownerCreates(QuickReservationCabin quickReservationCabin);


    Set<QuickReservationCabin> getByCabinId(Long cabinId);

    boolean quickReservationExists(Long id, LocalDateTime startDate, LocalDateTime endDate);

    boolean futureQuickReservationsExist(LocalDateTime currentDate, Long id);

    Set<QuickReservationCabin> findReservationsByOwnerId(String  ownerUsername);
    QuickReservationCabin findReservationById(Long id);
    Set<QuickReservationCabin> getPastReservations(String ownerUsername);

    void ownerCreatesReview(QuickReservationCabin reservation, boolean successfull);
    Set<QuickReservationCabin> getAllReports();
    Integer countReservationsInPeriod(LocalDateTime startWeek, LocalDateTime endWeek, String ownerUsername);

    Double sumProfit(String ownerUsername, LocalDateTime start, LocalDateTime end);

    void save(QuickReservationCabin reservation);
}

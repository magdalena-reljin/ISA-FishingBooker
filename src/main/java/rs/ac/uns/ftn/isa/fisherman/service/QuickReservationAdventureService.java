package rs.ac.uns.ftn.isa.fisherman.service;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationAdventure;
import java.time.LocalDateTime;
import java.util.Set;

public interface QuickReservationAdventureService {

    boolean instructorCreates(QuickReservationAdventure adventureReservation);


   Set<QuickReservationAdventure> getByInstructorId(Long id) ;

    boolean quickReservationExists(Long id, LocalDateTime startDate, LocalDateTime endDate);
    boolean futureQuickReservationsExist(LocalDateTime currentDate,Long id);

    Set<QuickReservationAdventure> getPastReservations(Long instructorId);

    void ownerCreatesReview(QuickReservationAdventure reservation, boolean successfull);
    Set<QuickReservationAdventure> getAllReports();

    Integer countReservationsInPeriod(LocalDateTime start, LocalDateTime end, Long ownerId);

    Double sumProfit(Long ownerId, LocalDateTime start, LocalDateTime end);
}

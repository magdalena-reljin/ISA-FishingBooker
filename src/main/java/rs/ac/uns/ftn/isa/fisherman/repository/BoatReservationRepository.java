package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.BoatReservation;

import java.time.LocalDateTime;
import java.util.Set;

public interface BoatReservationRepository extends JpaRepository<BoatReservation,Long> {
    @Query(value="SELECT CASE WHEN  COUNT(c) > 0 THEN true ELSE false END FROM boat_reservation c where boat_id=:boat_id and users_id=:users_id and ((:currentDate between start_date and end_date))",nativeQuery = true)
    boolean clientHasReservation(@Param("boat_id")Long boatId, @Param("users_id")Long usersId, @Param("currentDate") LocalDateTime currentDate);

    @Query(value="SELECT CASE WHEN  COUNT(c) > 0 THEN true ELSE false END FROM boat_reservation c where boat_id=:boat_id and ((:startDate between start_date and end_date) or (:endDate  between start_date and end_date) or (start_date  between :startDate and :endDate) or (end_date  between :startDate and :endDate)) ",nativeQuery = true)
    boolean reservationExists(@Param("boat_id")Long boatId,@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value="SELECT * FROM boat_reservation where boat_id=:boat_id and (:currentDate <= end_date) ",nativeQuery = true)
    Set<BoatReservation> getPresentByBoatId(@Param("boat_id")Long boatId, @Param("currentDate")LocalDateTime currentDate);

    @Query(value="SELECT CASE WHEN  COUNT(res) > 0 THEN true ELSE false END FROM boat_reservation res join boat b on res.boat_id=b.id where b.users_id=:users_id and needs_captain_service=true and ((:startDate between start_date and end_date) or (:endDate  between start_date and end_date) or (start_date  between :startDate and :endDate) or (end_date  between :startDate and :endDate)) ",nativeQuery = true)
    boolean ownerIsNotAvailable(@Param("users_id")Long ownerId, @Param("startDate") LocalDateTime start, @Param("endDate") LocalDateTime end);

    @Query(value="SELECT * FROM boat_reservation res join boat b on res.boat_id=b.id where b.users_id=:users_id ",nativeQuery = true)
    Set<BoatReservation> findReservationsByOwnerId(@Param("users_id")Long id);

    @Query(value="SELECT CASE WHEN  COUNT(c) > 0 THEN true ELSE false END FROM boat_reservation c where boat_id=:boat_id and (:currentDate <= end_date) ",nativeQuery = true)
    boolean futureReservationsExist(@Param("currentDate")LocalDateTime currentDate,@Param("boat_id") Long boatId);
}
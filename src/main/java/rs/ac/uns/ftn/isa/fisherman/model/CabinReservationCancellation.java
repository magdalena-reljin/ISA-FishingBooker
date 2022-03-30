package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CabinReservationCancellation extends ReservationCancellation{

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name="cabin_reservation_id")
    protected CabinReservation cabinReservation;

}

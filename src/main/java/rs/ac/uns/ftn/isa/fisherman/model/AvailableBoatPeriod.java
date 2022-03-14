package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class AvailableBoatPeriod extends AvailablePeriod {
    @ManyToOne
    @JoinColumn(name="users_id")
    protected BoatOwner boatOwner;

    @ManyToOne
    @JoinColumn(name="boat_id")
    protected Boat boat;

    public AvailableBoatPeriod(){}

    public AvailableBoatPeriod(Long id, LocalDateTime startDate, LocalDateTime endDate, BoatOwner boatOwner, Boat boat) {
        super(id, startDate, endDate);
        this.boatOwner = boatOwner;
        this.boat = boat;
    }

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}

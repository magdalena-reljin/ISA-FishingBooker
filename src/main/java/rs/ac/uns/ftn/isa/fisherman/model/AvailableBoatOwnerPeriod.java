package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
@Entity
public class AvailableBoatOwnerPeriod extends AvailablePeriod {
    @ManyToOne
    @JoinColumn(name="users_id")
    protected BoatOwner boatOwner;

    public AvailableBoatOwnerPeriod(){}

    public AvailableBoatOwnerPeriod(Long id, LocalDateTime startDate, LocalDateTime endDate, BoatOwner boatOwner) {
        super(id, startDate, endDate);
        this.boatOwner = boatOwner;
    }

    public AvailableBoatOwnerPeriod(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }
}

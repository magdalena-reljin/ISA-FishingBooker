package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("BOAT")
public class BoatComplaint extends Complaint{

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name="boat_id")
    private Boat boat;

    public BoatComplaint(Long id, String text, LocalDateTime date, boolean responded, Client client, Boat boat) {
        super(id, text, date, responded, client);
        this.boat = boat;
    }

    public BoatComplaint() {}

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}

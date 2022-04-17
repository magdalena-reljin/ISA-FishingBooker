package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("BOATOWNER")
public class BoatOwnerComplaint extends Complaint {

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name="boat_owner_id")
    private BoatOwner boatOwner;

    public BoatOwnerComplaint(Long id, String text, LocalDateTime date, boolean responded, Client client, BoatOwner boatOwner) {
        super(id, text, date, responded, client);
        this.boatOwner = boatOwner;
    }

    public BoatOwnerComplaint() {}

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }
}

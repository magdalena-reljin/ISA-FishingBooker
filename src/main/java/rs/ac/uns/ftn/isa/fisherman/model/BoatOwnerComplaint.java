package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("BOATOWNER")
public class BoatOwnerComplaint extends Complaint {


    private static final String COMPLAINT_TYPE = "BOAT_OWNER_COMPLAINT";
    @ManyToOne
    @JoinColumn(name="boat_owner_id")
    private BoatOwner boatOwner;

    public BoatOwnerComplaint(Long id, String text, LocalDateTime date, boolean responded, Client client,String ownersUsername, BoatOwner boatOwner) {
        super(id, text, date, responded, client,ownersUsername);
        this.boatOwner = boatOwner;
    }

    public BoatOwnerComplaint() {}

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }

    public  String getComplaintType() {
        return COMPLAINT_TYPE;
    }
}

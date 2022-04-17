package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("BOATOWNEREVALUATION")
public class BoatOwnerEvaluation extends Evaluation {

    private static final String TYPE = "BOAT OWNER EVALUATION";
    @ManyToOne
    @JoinColumn(name= "boat_owner_id")
    private BoatOwner boatOwner;

    public BoatOwnerEvaluation(Long id, LocalDateTime date, String comment, Double grade, boolean approved, Client client, String ownersUsername, BoatOwner boatOwner) {
        super(id, date, comment, grade, approved, client, ownersUsername);
        this.boatOwner = boatOwner;
    }

    public BoatOwnerEvaluation() {}

    public BoatOwner getBoatOwner() {
        return boatOwner;
    }

    public void setBoatOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }

    public String getType() {
        return TYPE;
    }
}

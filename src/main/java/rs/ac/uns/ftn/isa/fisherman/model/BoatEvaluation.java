package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("BOATEVALUATION")
public class BoatEvaluation extends Evaluation {
    @ManyToOne
    @JoinColumn(name= "boat_id")
    private Boat boat;

    public BoatEvaluation(Long id, LocalDateTime date, String comment, Double grade, boolean approved, Client client,String ownersUsername, Boat boat) {
        super(id, date, comment, grade, approved, client,ownersUsername);
        this.boat = boat;
    }

    public BoatEvaluation() {}

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}

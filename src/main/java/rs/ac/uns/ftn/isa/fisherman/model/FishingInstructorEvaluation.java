package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("FISHINGINSTRUCTOREVALUATION")
public class FishingInstructorEvaluation extends Evaluation {

    private static final String TYPE = "FISHING INSTRUCTOR EVALUATION";
    @ManyToOne
    @JoinColumn(name= "fishing_instructor_id")
    private FishingInstructor fishingInstructor;

    public FishingInstructorEvaluation(Long id, LocalDateTime date, String comment, Double grade, boolean approved, Client client, String ownersUsername) {
        super(id, date, comment, grade, approved, client, ownersUsername);
    }

    public FishingInstructorEvaluation() {}

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public void setFishingInstructor(FishingInstructor fishingInstructor) {
        this.fishingInstructor = fishingInstructor;
    }

    public String getType() {
        return TYPE;
    }
}

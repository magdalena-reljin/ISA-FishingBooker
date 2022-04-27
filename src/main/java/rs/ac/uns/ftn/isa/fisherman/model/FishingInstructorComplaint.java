package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("FISHINGINSTRUCTOR")
public class FishingInstructorComplaint extends Complaint {


    private static final String COMPLAINT_TYPE = "FISHING_INSTRUCTOR_COMPLAINT";

    @ManyToOne
    @JoinColumn(name= "fishing_instructor_id")
    private FishingInstructor fishingInstructor;

    public FishingInstructorComplaint(Long id, String text, LocalDateTime date, boolean responded, Client client,String ownersUsername, FishingInstructor fishingInstructor) {
        super(id, text, date, responded, client,ownersUsername);
        this.fishingInstructor = fishingInstructor;
    }

    public FishingInstructorComplaint() {}

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public void setFishingInstructor(FishingInstructor fishingInstructor) {
        this.fishingInstructor = fishingInstructor;
    }

    public  String getComplaintType() {
        return COMPLAINT_TYPE;
    }
}

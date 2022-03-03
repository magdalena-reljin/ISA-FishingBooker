package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="availableInstructorPeriod")
public class AvailableInstructorPeriod extends AvailablePeriod {

    @ManyToOne
    @JoinColumn(name="users_id")
    protected FishingInstructor fishingInstructor;


    public AvailableInstructorPeriod() {

    }

    public AvailableInstructorPeriod(Long id, LocalDateTime startDate, LocalDateTime endDate, FishingInstructor fishingInstructor) {
        super(id, startDate, endDate);
        this.fishingInstructor = fishingInstructor;
    }

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public void setFishingInstructor(FishingInstructor fishingInstructor) {
        this.fishingInstructor = fishingInstructor;
    }
}

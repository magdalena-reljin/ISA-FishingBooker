package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CABINOWNEREVALUATION")
public class CabinOwnerEvaluation extends Evaluation{

    @ManyToOne
    @JoinColumn(name= "cabin_owner_id")
    private CabinOwner cabinOwner;

    public CabinOwnerEvaluation(Long id, LocalDateTime date, String comment, Double grade, boolean approved, Client client, CabinOwner cabinOwner) {
        super(id, date, comment, grade, approved, client);
        this.cabinOwner = cabinOwner;
    }

    public CabinOwnerEvaluation(){}

    public CabinOwner getCabinOwner() {
        return cabinOwner;
    }

    public void setCabinOwner(CabinOwner cabinOwner) {
        this.cabinOwner = cabinOwner;
    }
}

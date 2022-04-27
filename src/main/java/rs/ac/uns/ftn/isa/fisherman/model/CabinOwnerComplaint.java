package rs.ac.uns.ftn.isa.fisherman.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CABINOWNER")
public class CabinOwnerComplaint extends Complaint {

    private static final String COMPLAINT_TYPE = "CABIN_OWNER_COMPLAINT";
    @ManyToOne
    @JoinColumn(name="cabin_owner_id")
    private CabinOwner cabinOwner;

    public CabinOwnerComplaint(Long id, String text, LocalDateTime date, boolean responded, Client client,String ownersUsername, CabinOwner cabinOwner) {
        super(id, text, date, responded, client,ownersUsername);
        this.cabinOwner = cabinOwner;
    }

    public CabinOwnerComplaint(){}

    public CabinOwner getCabinOwner() {
        return cabinOwner;
    }

    public void setCabinOwner(CabinOwner cabinOwner) {
        this.cabinOwner = cabinOwner;
    }

    @Override
    public  String getComplaintType() {
        return COMPLAINT_TYPE;
    }
}

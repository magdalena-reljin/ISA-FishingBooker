package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class BoatQuickReservationReport extends OwnersReport{
    @ManyToOne
    @JoinColumn(name= "boat_quick_reservation_id")
    QuickReservationBoat quickReservationBoat;

    public BoatQuickReservationReport(Long id, boolean badComment, String comment, String ownersUsername, String clientUsername, boolean approved, QuickReservationBoat quickReservationBoat) {
        super(id,badComment, comment,ownersUsername,clientUsername, approved);
        this.quickReservationBoat = quickReservationBoat;
    }

    public BoatQuickReservationReport() {
    }

    public QuickReservationBoat getQuickReservationBoat() {
        return quickReservationBoat;
    }

    public void setQuickReservationBoat(QuickReservationBoat quickReservationBoat) {
        this.quickReservationBoat = quickReservationBoat;
    }
}

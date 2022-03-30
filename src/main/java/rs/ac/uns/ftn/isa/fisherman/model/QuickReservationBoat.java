package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
@Entity
public class QuickReservationBoat extends Reservation{
    @ManyToOne
    @JoinColumn(name="boat_id")
    protected Boat boat;

    @ManyToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
    @JoinTable(name = "boat_quickr_services",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    protected Set<AdditionalServices> addedAdditionalServices;

    protected boolean needsCaptainServices;
    public QuickReservationBoat(){}

    public QuickReservationBoat(Long id, LocalDateTime startDate, LocalDateTime endDate, Double price, Boat boat, Set<AdditionalServices> addedAdditionalServices,boolean needsCaptainServices) {
        super(id, startDate, endDate, price);
        this.boat = boat;
        this.addedAdditionalServices = addedAdditionalServices;
        this.needsCaptainServices=needsCaptainServices;
    }

    public boolean getNeedsCaptainServices() {
        return needsCaptainServices;
    }

    public void setNeedsCaptainServices(boolean needsCaptainServices) {
        this.needsCaptainServices = needsCaptainServices;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public Set<AdditionalServices> getAddedAdditionalServices() {
        return addedAdditionalServices;
    }

    public void setAddedAdditionalServices(Set<AdditionalServices> addedAdditionalServices) {
        this.addedAdditionalServices = addedAdditionalServices;
    }
}

package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class AdventureReservation extends Reservation{
    @ManyToOne
    @JoinColumn(name="adventure_id")
    protected Adventure adventure;

    @ManyToOne
    @JoinColumn(name="instructors_id")
    protected FishingInstructor fishingInstructor;

    @ManyToMany(fetch = FetchType.EAGER,cascade={CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "adventure_reservation_services",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    protected Set<AdditionalServices> addedAdditionalServices;

    public AdventureReservation(Long id, LocalDateTime startDate, LocalDateTime endDate, Client client, PaymentInformation paymentInformation, Adventure adventure, FishingInstructor fishingInstructor, Set<AdditionalServices> addedAdditionalServices) {
        super(id, startDate, endDate, client, paymentInformation);
        this.adventure = adventure;
        this.fishingInstructor = fishingInstructor;
        this.addedAdditionalServices = addedAdditionalServices;
    }

    public AdventureReservation() {}

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public void setFishingInstructor(FishingInstructor fishingInstructor) {
        this.fishingInstructor = fishingInstructor;
    }

    public Set<AdditionalServices> getAddedAdditionalServices() {
        return addedAdditionalServices;
    }

    public void setAddedAdditionalServices(Set<AdditionalServices> addedAdditionalServices) {
        this.addedAdditionalServices = addedAdditionalServices;
    }
}

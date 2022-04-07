package rs.ac.uns.ftn.isa.fisherman.model;

import rs.ac.uns.ftn.isa.fisherman.dto.OwnersReportDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
@Entity
public class QuickReservationAdventure extends  Reservation {
    @ManyToOne
    @JoinColumn(name="adventure_id")
    protected Adventure adventure;

    @ManyToOne
    @JoinColumn(name="instructors_id")
    protected FishingInstructor fishingInstructor;

    @Column(name="discount")
    protected Integer discount;


    @ManyToMany(fetch = FetchType.EAGER,cascade={CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "adventure_quickr_services",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    protected Set<AdditionalServices> addedAdditionalServices;

    public QuickReservationAdventure() {}

    public QuickReservationAdventure(Long id, LocalDateTime startDate, LocalDateTime endDate, Client client, PaymentInformation paymentInformation, boolean ownerWroteAReport,String ownersUsername,Adventure adventure, FishingInstructor fishingInstructor, Integer discount, Set<AdditionalServices> addedAdditionalServices) {
        super(id, startDate, endDate, client, paymentInformation,ownerWroteAReport,ownersUsername);
        this.adventure = adventure;
        this.fishingInstructor = fishingInstructor;
        this.discount = discount;
        this.addedAdditionalServices = addedAdditionalServices;
    }

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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}

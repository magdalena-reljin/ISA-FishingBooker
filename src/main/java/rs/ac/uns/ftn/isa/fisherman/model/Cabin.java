package rs.ac.uns.ftn.isa.fisherman.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Cabin")
public class Cabin {
    @Id
    @SequenceGenerator(name = "cabin_sequence_generator", sequenceName = "cabin_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cabin_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;

    @Column(name="name", nullable = false)
    protected String name;

    @Column(name="description", nullable = false)
    protected String description;

    @Column(name="numOfRooms")
    protected Integer numOfRooms;

    @Column(name="bedsPerRoom")
    protected Integer bedsPerRoom;


    @Column(name="rules")
    protected String rules;

    @Column(name="price", nullable = false)
    protected double price=0.0;

    @Embedded
    private  Address address;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cabin_services",
            joinColumns = @JoinColumn(name = "cabin_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private Set<AdditionalServices> additionalServices;

    @Column(name = "rating")
    private Double rating = 0.0;

    @OneToMany(mappedBy = "cabin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Image> images;



    @ManyToOne
    @JoinColumn(name = "users_id")
    private CabinOwner cabinOwner;

    public Cabin() {

    }

    public CabinOwner getCabinOwner() {
        return cabinOwner;
    }

    public void setCabinOwner(CabinOwner cabinOwner) {
        this.cabinOwner = cabinOwner;
    }



    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double prices) {
        this.price = prices;
    }

    public Set<AdditionalServices> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalServices> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(Integer numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public Integer getBedsPerRoom() {
        return bedsPerRoom;
    }

    public void setBedsPerRoom(Integer bedsPerRoom) {
        this.bedsPerRoom = bedsPerRoom;
    }

    public Cabin(Long id, String name, String description, Integer numOfRooms, Integer bedsPerRoom, String rules, double price, Address address, Set<AdditionalServices> additionalServices, Double rating, Set<Image> images, CabinOwner cabinOwner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numOfRooms = numOfRooms;
        this.bedsPerRoom = bedsPerRoom;
        this.rules = rules;
        this.price = price;
        this.address = address;
        this.additionalServices = additionalServices;
        this.rating = rating;
        this.images = images;
        this.cabinOwner = cabinOwner;
    }
}

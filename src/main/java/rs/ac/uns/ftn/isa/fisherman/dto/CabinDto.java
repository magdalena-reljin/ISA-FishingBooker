package rs.ac.uns.ftn.isa.fisherman.dto;

import java.util.Set;

public class CabinDto {
    protected Long id;
    protected String name; //
    protected String description; //
    protected int numOfRooms; //
    protected int bedsPerRoom; //
    protected String rules; //
    protected double price=0.0; ///
    private AddressDTO addressDto; //
    private Set<AdditionalServicesDto> additionalServices;
    private Double rating = 0.0;
    private Set<ImageDto> images;
    private String ownerUsername; ///

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

    public void setPrice(double price) {
        this.price = price;
    }

    public AddressDTO getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDTO addressDto) {
        this.addressDto = addressDto;
    }

    public Set<AdditionalServicesDto> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalServicesDto> additionalServices) {
        this.additionalServices = additionalServices;
    }



    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setImages(Set<ImageDto> images) {
        this.images = images;
    }

    public Double getRating() {
        return rating;
    }

    public Set<ImageDto> getImages() {
        return images;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
    public CabinDto(){}

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public int getBedsPerRoom() {
        return bedsPerRoom;
    }

    public void setBedsPerRoom(int bedsPerRoom) {
        this.bedsPerRoom = bedsPerRoom;
    }

    public CabinDto(Long id, String name, String description, int numOfRooms, int bedsPerRoom, String rules, double price, AddressDTO address, Set<AdditionalServicesDto> additionalServices, Double rating, Set<ImageDto> images, String ownerUsername) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numOfRooms = numOfRooms;
        this.bedsPerRoom = bedsPerRoom;
        this.rules = rules;
        this.price = price;
        this.addressDto = address;
        this.additionalServices = additionalServices;
        this.rating = rating;
        this.images = images;
        this.ownerUsername = ownerUsername;
    }
}
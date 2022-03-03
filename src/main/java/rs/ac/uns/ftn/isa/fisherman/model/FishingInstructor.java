package rs.ac.uns.ftn.isa.fisherman.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("FISHING INSTRUCTOR")
public class FishingInstructor extends  User {
    private static String roleApp = "ROLE_FISHING_INSTRUCTOR";
    private String registrationReason;
    private double rating;
    public FishingInstructor() {}
    public FishingInstructor(Long id, String name, String lastName, String username, String password, String phoneNum, Address address,String registrationReason) {
        super(id, name, lastName, username, password, phoneNum, address);
        this.registrationReason=registrationReason;
        this.rating=0;
        this.availableInstructorPeriods= new HashSet<>();
    }

    public FishingInstructor(Long id, String name, String lastName, String username, String password, String phoneNum, Address address, String registrationReason, double rating, Set<Adventure> adventures, Set<AvailableInstructorPeriod> availableInstructorPeriods) {
        super(id, name, lastName, username, password, phoneNum, address);
        this.registrationReason = registrationReason;
        this.rating = rating;
        this.adventures = adventures;
        this.availableInstructorPeriods = availableInstructorPeriods;
    }

    @OneToMany(mappedBy = "fishingInstructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Adventure> adventures;

    @OneToMany(mappedBy = "fishingInstructor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AvailableInstructorPeriod> availableInstructorPeriods;

    public String getRegistrationReason() {
        return registrationReason;
    }
    public void setRegistrationReason(String registrationReason) {
        this.registrationReason = registrationReason;
    }

    @Override
    public String getRoleApp() {
        return roleApp;
    }

    public Set<Adventure> getAdventures() {
        return adventures;
    }

    public void setAdventures(Set<Adventure> adventures) {
        this.adventures = adventures;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Set<AvailableInstructorPeriod> getAvailableInstructorPeriods() {
        return availableInstructorPeriods;
    }

    public void setAvailableInstructorPeriods(Set<AvailableInstructorPeriod> availableInstructorPeriods) {
        this.availableInstructorPeriods = availableInstructorPeriods;
    }

}

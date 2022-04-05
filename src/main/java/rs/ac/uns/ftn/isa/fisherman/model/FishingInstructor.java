package rs.ac.uns.ftn.isa.fisherman.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("FISHINGINSTRUCTOR")
public class FishingInstructor extends  User {
    private static String roleApp = "ROLE_FISHING_INSTRUCTOR";
    private double rating;
    public FishingInstructor() {}
    public FishingInstructor(Long id, String name, String lastName, String username, String password, String phoneNum, Address address, double rating, Set<Adventure> adventures, Set<AvailableInstructorPeriod> availableInstructorPeriods,String registrationReason) {
        super(id, name, lastName, username, password, phoneNum, address,registrationReason);
        this.rating = rating;
        this.adventures = adventures;
        this.availableInstructorPeriods = availableInstructorPeriods;
    }

    @OneToMany(mappedBy = "fishingInstructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Adventure> adventures;

    @OneToMany(mappedBy = "fishingInstructor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AvailableInstructorPeriod> availableInstructorPeriods;

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

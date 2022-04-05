package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("BOATOWNER")
public class BoatOwner extends User {
    private static final String ROLE_APP = "ROLE_BOATOWNER";
    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Boat> boats;


    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AvailableBoatOwnerPeriod> availableBoatOwnerPeriods;

    public Set<AvailableBoatOwnerPeriod> getAvailableBoatOwnerPeriods() {
        return availableBoatOwnerPeriods;
    }

    public void setAvailableBoatOwnerPeriods(Set<AvailableBoatOwnerPeriod> availableBoatOwnerPeriods) {
        this.availableBoatOwnerPeriods = availableBoatOwnerPeriods;
    }

    public BoatOwner() {}

    public BoatOwner(Long id, String name, String lastName, String username, String password, String phoneNum, Address address,String registrationReason) {
        super(id, name, lastName, username, password, phoneNum, address,registrationReason);
        this.availableBoatOwnerPeriods=new HashSet<>();
    }

    public Set<Boat> getBoats() {
        return boats;
    }

    public void setBoats(Set<Boat> boats) {
        this.boats = boats;
    }

    @Override
    public String getRoleApp() {
        return ROLE_APP;
    }




}

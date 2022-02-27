package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("BOAT OWNER")
public class BoatOwner extends User {
    private static String roleApp = "ROLE_BOATOWNER";
    private String registrationReason;
    @OneToMany(mappedBy = "boatOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Boat> boats;

    public BoatOwner() {}

    public BoatOwner(Long id, String name, String lastName, String username, String password, String phoneNum, Address address,String registrationReason) {
        super(id, name, lastName, username, password, phoneNum, address);
        this.registrationReason=registrationReason;
    }

    public Set<Boat> getBoats() {
        return boats;
    }

    public void setBoats(Set<Boat> boats) {
        this.boats = boats;
    }

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

}

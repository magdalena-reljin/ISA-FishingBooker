package rs.ac.uns.ftn.isa.fisherman.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("CABIN OWNER")
public class CabinOwner extends User{
    private String registrationReason;
    private  static String roleApp = "ROLE_CABINOWNER";

    @OneToMany(mappedBy = "cabinOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Cabin> cabins;


    public CabinOwner(){}
    public CabinOwner(Long id, String name, String lastName, String username, String password, String phoneNum, Address address, String registrationReason) {
        super(id, name, lastName, username, password, phoneNum, address);
        this.registrationReason = registrationReason;
    }
    public CabinOwner(String registrationReason) {
        this.registrationReason = registrationReason;
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

    public Set<Cabin> getCabins() {
        return cabins;
    }

    public void setCabins(Set<Cabin> cabins) {
        this.cabins = cabins;
    }
}

package rs.ac.uns.ftn.isa.fisherman.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("CABIN OWNER")
public class CabinOwner extends User{
    private String registrationReason;
    private static final String ROLE_APP = "ROLE_CABINOWNER";

    @OneToMany(mappedBy = "cabinOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Cabin> cabins;

    @OneToMany(mappedBy = "cabinOwner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AvailableCabinPeriod> availableCabinPeriods;

    public CabinOwner(){}
    public CabinOwner(Long id, String name, String lastName, String username, String password, String phoneNum, Address address, String registrationReason) {
        super(id, name, lastName, username, password, phoneNum, address);
        this.registrationReason = registrationReason;
        this.availableCabinPeriods= new HashSet<>();
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
        return ROLE_APP;
    }

    public Set<Cabin> getCabins() {
        return cabins;
    }

    public void setCabins(Set<Cabin> cabins) {
        this.cabins = cabins;
    }
}

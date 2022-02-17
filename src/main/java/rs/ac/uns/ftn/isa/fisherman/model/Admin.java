package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    private static String roleApp = "ROLE_ADMIN";
    private Boolean isPredefined;
    private Boolean changedPassword;

    public Admin(){}
    public Admin(Long id, String name, String lastName, String username, String password, String phoneNum, Address address, Boolean isPredefined) {
        super(id, name, lastName, username, password, phoneNum, address);
        this.isPredefined = isPredefined;
        this.changedPassword=false;

    }

    public Boolean getChangedPassword() {
        return changedPassword;
    }

    public void setChangedPassword(Boolean changedPassword) {
        this.changedPassword = changedPassword;
    }

    @Override
    public String getRoleApp() {
        return roleApp;
    }


    public Boolean getPredefined() {
        return isPredefined;
    }

    public void setPredefined(Boolean predefined) {
        isPredefined = predefined;
    }


}

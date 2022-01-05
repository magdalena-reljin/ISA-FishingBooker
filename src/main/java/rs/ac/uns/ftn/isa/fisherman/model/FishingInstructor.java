package rs.ac.uns.ftn.isa.fisherman.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FISHING INSTRUCTOR")
public class FishingInstructor extends  User {

    private static String roleApp = "ROLE_FISHING_INSTRUCTOR";
    private String registrationReason;

    private  boolean accepted;


    public FishingInstructor() {
    }

    public FishingInstructor(Long id, String name, String lastName, String email, String password, String phoneNum, Address address,String registrationReason) {
        super(id, name, lastName, email, password, phoneNum, address);
        this.registrationReason=registrationReason;

        this.accepted=false;

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


    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String getUsername() {
        return null;
    }
}

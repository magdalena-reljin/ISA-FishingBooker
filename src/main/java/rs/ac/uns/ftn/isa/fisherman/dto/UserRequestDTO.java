package rs.ac.uns.ftn.isa.fisherman.dto;

public class UserRequestDTO {

    private Long id;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String phoneNum;
    private AddressDTO address;
    private String registrationReason;

    public UserRequestDTO(Long id, String email, String password, String firstname, String lastname, String phoneNum, AddressDTO address, String registrationReason) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNum = phoneNum;
        this.address = address;
        this.registrationReason = registrationReason;
    }

    public UserRequestDTO(){}
    public String getPhoneNum() {
        return phoneNum;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public String getRegistrationReason() {
        return registrationReason;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public void setRegistrationReason(String registrationReason) {
        this.registrationReason = registrationReason;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

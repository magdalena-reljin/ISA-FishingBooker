package rs.ac.uns.ftn.isa.fisherman.dto;

public class UserRequestDTO {

    private Long id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String phoneNum;
    private AddressDTO address;
    private String registrationReason;
    private String role;

    public UserRequestDTO(Long id, String username, String password, String firstname, String lastname, String phoneNum, AddressDTO address, String registrationReason, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNum = phoneNum;
        this.address = address;
        this.registrationReason = registrationReason;
        this.role=role;
    }

    public UserRequestDTO(String username, String firstname, String lastname, String role, String registrationReason) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.registrationReason=registrationReason;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

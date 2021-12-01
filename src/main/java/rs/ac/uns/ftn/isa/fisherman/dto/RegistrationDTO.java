package rs.ac.uns.ftn.isa.fisherman.dto;

import rs.ac.uns.ftn.isa.fisherman.model.Address;

public class RegistrationDTO {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phoneNum;
    private AddressDTO address;
    private String registrationReason;

    public RegistrationDTO() {}

    public RegistrationDTO(String name, String lastName, String email, String password, String phoneNum, AddressDTO address, String registrationReason) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.address = address;
        this.registrationReason = registrationReason;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getRegistrationReason() {
        return registrationReason;
    }

    public void setRegistrationReason(String registrationReason) {
        this.registrationReason = registrationReason;
    }
}

package com.example.bank.app.dto;

public class BankClientDto extends AbstractEntityDto {
    private String lastName;
    private String firstName;
    private String address;
    private Long passportId;

    public BankClientDto() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    public Long getPassportId() {
        return passportId;
    }
}

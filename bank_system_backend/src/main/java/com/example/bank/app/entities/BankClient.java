package com.example.bank.app.entities;

import com.example.bank.utils.xml.adapters.BankClientAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlJavaTypeAdapter(BankClientAdapter.class)
public class BankClient extends AbstractEntity {
    @XmlElement
    private String lastName;

    @XmlElement
    private String firstName;

    @XmlElement
    private String address;

    @XmlElement
    private Long passportId;

    public BankClient(String id, String firstName, String lastName, String address, Long passportId) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.passportId = passportId;
    }

    private BankClient() {
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public Long getPassportId() {
        return passportId;
    }

    public void updateFromDto(String newFirstName, String newLastName, String newAddress, Long newPassportId) {
        firstName = newFirstName;
        lastName = newLastName;
        address = newAddress;
        passportId = newPassportId;
    }

    public boolean getHasLimitedOperations() {
        return address == null || passportId == null;
    }
}

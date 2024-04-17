package model;

import java.io.Serializable;

public class PassengerModel extends Person implements Serializable {
    private String contactPerson;
    private String contactPersonLastName;
    private int contactPersonPhoneNumber;

    public PassengerModel(String name, String lastName, int phoneNumber, int dni, String contactPerson, String contactPersonLastName, int contactPersonPhoneNumber) {
        super(name, lastName, phoneNumber, dni);
        this.contactPerson = contactPerson;
        this.contactPersonLastName = contactPersonLastName;
        this.contactPersonPhoneNumber = contactPersonPhoneNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPersonLastName() {
        return contactPersonLastName;
    }

    public void setContactPersonLastName(String contactPersonLastName) {
        this.contactPersonLastName = contactPersonLastName;
    }

    public int getContactPersonPhoneNumber() {
        return contactPersonPhoneNumber;
    }

    public void setContactPersonPhoneNumber(int contactPersonPhoneNumber) {
        this.contactPersonPhoneNumber = contactPersonPhoneNumber;
    }
}
package model;

public class Person {
    public String name;
    public String lastName;
    public int phoneNumber;
    public int dni; 

    public Person(String name, String lastName, int phoneNumber, int dni) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dni = dni;
    }

    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public int getDni() {
        return dni;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
}

package model;

public class Person {
    // Variables de instancia para el nombre, apellido, número de teléfono y DNI
    public String name;
    public String lastName;
    public int phoneNumber;
    public int dni; 

    // Constructor con parámetros para inicializar un objeto Person con nombre, apellido, número de teléfono y DNI
    public Person(String name, String lastName, int phoneNumber, int dni) {
        this.name = name; // Asigna el nombre proporcionado
        this.lastName = lastName; // Asigna el apellido proporcionado
        this.phoneNumber = phoneNumber; // Asigna el número de teléfono proporcionado
        this.dni = dni; // Asigna el DNI proporcionado
    }

    // Método para obtener el nombre
    public String getName() {
        return name; // Retorna el nombre
    }
    
    // Método para obtener el apellido
    public String getLastName() {
        return lastName; // Retorna el apellido
    }
    
    // Método para obtener el número de teléfono
    public int getPhoneNumber() {
        return phoneNumber; // Retorna el número de teléfono
    }
    
    // Método para obtener el DNI
    public int getDni() {
        return dni; // Retorna el DNI
    }
    
    // Método para establecer el nombre
    public void setName(String name) {
        this.name = name; // Asigna el nombre
    }
    
    // Método para establecer el apellido
    public void setLastName(String lastName) {
        this.lastName = lastName; // Asigna el apellido
    }
    
    // Método para establecer el número de teléfono
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber; // Asigna el número de teléfono
    }
    
    // Método para establecer el DNI
    public void setDni(int dni) {
        this.dni = dni; // Asigna el DNI
    }
}

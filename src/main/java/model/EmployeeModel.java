package model;

import java.io.Serializable;

public class EmployeeModel extends Person implements Serializable {
    
    public EmployeeModel(String name, String lastName, int phoneNumber, int dni) {
        super(name, lastName, phoneNumber, dni);
    }

    public String user;
    public String password;
    
    public EmployeeModel(String name, String lastName, int phoneNumber, int dni, String user, String password) {
        super(name, lastName, phoneNumber, dni);
        this.user = user;
        this.password = password;
    }
    public EmployeeModel() {
        super("", "", 0, 0); // Llama al constructor de la superclase con valores por defecto
        this.user = "";
        this.password = "";
    }

    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String generateUsername() {
        // Obtener los dos últimos dígitos del ID
        String idDigits = String.valueOf(this.getDni());
        if (idDigits.length() > 2) {
            idDigits = idDigits.substring(idDigits.length() - 2);
        }
        // Generar el usuario concatenando el nombre, apellido y los dos últimos dígitos del ID
        return this.getName().toLowerCase() + "." + this.getLastName().toLowerCase() + "." + idDigits;
    }

    public String generatePassword() {
        // Obtener el primer carácter del nombre y del apellido
        char firstCharName = this.getName().charAt(0);
        char firstCharLastName = this.getLastName().charAt(0);
        // Generar la contraseña concatenando los primeros caracteres del nombre, apellido y DNI
        return String.valueOf(firstCharName) + String.valueOf(firstCharLastName) + this.getDni();
    }
}


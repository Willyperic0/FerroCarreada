package model;

import java.io.Serializable;

public class EmployeeModel extends Person implements Serializable {
    // Declara la clase EmployeeModel que extiende de la clase Person e implementa Serializable
    
    // Constructor con parámetros para inicializar un objeto EmployeeModel con nombre, apellido, número de teléfono y DNI
    public EmployeeModel(String name, String lastName, int phoneNumber, int dni) {
        super(name, lastName, phoneNumber, dni); // Llama al constructor de la superclase Person con los parámetros proporcionados
    }

    // Variables de instancia para el nombre de usuario y la contraseña
    public String user;
    public String password;
    public boolean isAdmin; // Variable para indicar si el empleado es administrador o no
    
    // Constructor con parámetros para inicializar un objeto EmployeeModel con nombre, apellido, número de teléfono, DNI, nombre de usuario y contraseña
    public EmployeeModel(String name, String lastName, int phoneNumber, int dni, String user, String password, boolean isAdmin) {
        super(name, lastName, phoneNumber, dni); // Llama al constructor de la superclase Person con los parámetros proporcionados
        this.user = user; // Asigna el nombre de usuario
        this.password = password; // Asigna la contraseña
        this.isAdmin = isAdmin; // Asigna el estado de administrador
    }

    // Constructor sin parámetros que llama al constructor de la superclase Person con valores por defecto y establece el nombre de usuario, contraseña y estado de administrador como valores predeterminados
    public EmployeeModel() {
        super("", "", 0, 0); // Llama al constructor de la superclase Person con valores por defecto
        this.user = ""; // Inicializa el nombre de usuario como una cadena vacía
        this.password = ""; // Inicializa la contraseña como una cadena vacía
        this.isAdmin = false; // Por defecto, no es administrador
    }

    // Método para obtener el nombre de usuario
    public String getUser() {
        return user; // Retorna el nombre de usuario
    }
    
    // Método para establecer el nombre de usuario
    public void setUser(String user) {
        this.user = user; // Asigna el nombre de usuario
    }
    
    // Método para obtener la contraseña
    public String getPassword() {
        return password; // Retorna la contraseña
    }
    
    // Método para establecer la contraseña
    public void setPassword(String password) {
        this.password = password; // Asigna la contraseña
    }

    // Método para obtener el estado de administrador
    public boolean getisAdmin() {
        return isAdmin; // Retorna el estado de administrador
    }

    // Método para establecer el estado de administrador
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin; // Asigna el estado de administrador
    }


    // Método para generar un nombre de usuario basado en el nombre, apellido y los dos últimos dígitos del DNI
    public String generateUsername() {
        // Obtiene los dos últimos dígitos del DNI
        String idDigits = String.valueOf(this.getDni());
        if (idDigits.length() > 2) {
            idDigits = idDigits.substring(idDigits.length() - 2);
        }
        // Genera el nombre de usuario concatenando el nombre, apellido y los dos últimos dígitos del DNI, todo en minúsculas
        return this.getName().toLowerCase() + "." + this.getLastName().toLowerCase() + "." + idDigits;
    }

    // Método para generar una contraseña basada en el primer carácter del nombre, apellido y el DNI
    public String generatePassword() {
        // Obtiene el primer carácter del nombre y del apellido
        char firstCharName = this.getName().charAt(0);
        char firstCharLastName = this.getLastName().charAt(0);
        // Genera la contraseña concatenando los primeros caracteres del nombre, apellido y el DNI
        return String.valueOf(firstCharName) + String.valueOf(firstCharLastName) + this.getDni();
    }
}

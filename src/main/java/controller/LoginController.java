package controller;

import model.EmployeeModel;
import view.LoginView;
import willy.linkedlist.doubly.LinkedList;

public class LoginController {
    static LoginView loginView; // Declara una vista estática de inicio de sesión

    // Método para autenticar un usuario
    public boolean authenticate(String username, String password, LinkedList<EmployeeModel> employees) {
        int size = employees.size(); // Obtiene el tamaño de la lista de empleados
        for (int i = 0; i < size; i++) { // Bucle para recorrer la lista de empleados
            EmployeeModel employee = employees.get(i); // Obtiene el empleado en la posición actual del bucle
            // Comprueba si el nombre de usuario y la contraseña coinciden con las credenciales del empleado
            if (employee.getUser().equals(username) && employee.getPassword().equals(password)) {
                return true; // Devuelve verdadero si las credenciales coinciden
            }
        }
        return false; // Devuelve falso si no se encontraron coincidencias de credenciales
    }

    // Método para obtener el DNI del usuario conectado
    public int getLoggedUserDNI(String username, LinkedList<EmployeeModel> employees) {
        int size = employees.size(); // Obtiene el tamaño de la lista de empleados
        for (int i = 0; i < size; i++) { // Bucle para recorrer la lista de empleados
            EmployeeModel employee = employees.get(i); // Obtiene el empleado en la posición actual del bucle
            // Comprueba si el nombre de usuario coincide con el usuario actual del empleado
            if (employee.getUser().equals(username)) {
                return employee.getDni(); // Devuelve el DNI del empleado si se encuentra una coincidencia
            }
        }
        return -1; // Devuelve un valor negativo si el usuario no se encuentra o si hay un problema
    }

    // Método estático para obtener el DNI del usuario conectado
    public static int loggedDNI() {
        int cc =  loginView.getLoggedUserDNI(); // Obtiene el DNI del usuario conectado desde la vista
        return cc; // Devuelve el DNI del usuario conectado
    }
}
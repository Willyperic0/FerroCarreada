package controller;

import model.EmployeeModel;
import view.LoginView;
import willy.linkedlist.doubly.LinkedList;
import java.io.File;
import java.io.IOException;

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
    public boolean authenticateRole(String username, String password, LinkedList<EmployeeModel> employees) {
        boolean result = false;
        int size = employees.size(); // Obtiene el tamaño de la lista de empleados
        for (int i = 0; i < size; i++) { // Bucle para recorrer la lista de empleados
            EmployeeModel employee = employees.get(i); // Obtiene el empleado en la posición actual del bucle
            // Comprueba si el nombre de usuario y la contraseña coinciden con las credenciales del empleado
            if (employee.getUser().equals(username) && employee.getPassword().equals(password)) {
                result = employee.getisAdmin();
                return result; // Devuelve verdadero si las credenciales coinciden
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
     // Método para crear las carpetas necesarias y los archivos JSON
     public static void crearCarpetasYArchivos() {
        // Definir las rutas de las carpetas
        String folderPath1 = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database";
        // Puedes agregar más carpetas si es necesario

        // Crear la primera carpeta
        File folder1 = new File(folderPath1);
        if (!folder1.exists()) {
            if (folder1.mkdirs()) {
                System.out.println("Carpeta creada: " + folderPath1);
            } else {
                System.out.println("No se pudo crear la carpeta: " + folderPath1);
            }
        } else {
            System.out.println("La carpeta ya existe: " + folderPath1);
        }

        // Definir las rutas de los archivos JSON
        String jsonPath1 = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "routes.json";
        String jsonPath2 = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "trains.json";
        String jsonPath3 = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "employees.json";
        String jsonPath4 = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "tickets.json";

        // Crear los archivos JSON
        crearArchivoSiNoExiste(jsonPath1);
        crearArchivoSiNoExiste(jsonPath2);
        crearArchivoSiNoExiste(jsonPath3);
        crearArchivoSiNoExiste(jsonPath4);
    }

    // Método para crear un archivo si no existe
    private static void crearArchivoSiNoExiste(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Archivo JSON creado: " + filePath);
                } else {
                    System.out.println("No se pudo crear el archivo JSON: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo JSON: " + filePath);
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo JSON ya existe: " + filePath);
        }
    }
}

package controller;


import willy.linkedlist.doubly.LinkedList;

import java.io.File;

import javax.swing.JOptionPane;

import model.EmployeeModel;


public class EmployeeController {
    private LinkedList<EmployeeModel> employees; // Lista enlazada para almacenar los empleados
    private EmployeeModel model; // Modelo de empleado
    

    static LoginController loginController;
    public EmployeeController(EmployeeModel model) {
        this.model = model;
        this.employees = new LinkedList<>();
        loadEmployeesFromJson();
    }
        public void loadEmployeesFromJson() {
            String employeeFilePath = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "employees.json";
            // Crear una instancia de FileJsonAdapter para trenes
            FileJsonAdapter<EmployeeModel> employeeJsonAdapter = FileJsonAdapter.getInstance();
        
            // Leer los datos del archivo JSON y establecer la lista de trenes en el controlador de trenes
            LinkedList<EmployeeModel> employeeList = employeeJsonAdapter.getObjects(employeeFilePath, EmployeeModel[].class);
        
            // Verificar si se leyeron correctamente los datos
            if (employeeList != null) {
                // Actualizar la lista de trenes en el controlador de trenes
                this.employees = employeeList;
            } else {
                System.out.println("Error al leer los datos de empleados desde el archivo JSON.");
            }
        }
        public void saveEmployeesToJson() {
            String employeeFilePath = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "employees.json";
            // Crear una instancia de FileJsonAdapter para trenes
            FileJsonAdapter<EmployeeModel> employeeJsonAdapter = FileJsonAdapter.getInstance();
    
            // Guardar los datos de trenes en un archivo JSON
            boolean success = employeeJsonAdapter.writeObjects(employeeFilePath, employees);
    
            // Mostrar mensaje de éxito o error para trenes
            if (success) {
                System.out.println("Datos de Empleados guardados correctamente en el archivo JSON.");
            } else {
                System.out.println("Error al guardar los datos de Empleados en el archivo JSON.");
            }
        }

// Método para agregar un nuevo empleado al sistema
public void addEmployee(String name, String lastName, int phoneNumber, int dni, String user, String password, boolean role) {
    // Verificar si ya existe un empleado con el mismo DNI
    if (isEmployeeExists(dni)) {
        JOptionPane.showMessageDialog(null,"Error: Ya existe un empleado con el mismo DNI en el sistema.");
        return; // Salir del método si ya existe un empleado con el mismo DNI
    }
    
    // Si no hay un empleado con el mismo DNI, proceder con la adición del nuevo empleado
    EmployeeModel newEmployee = new EmployeeModel(name, lastName, phoneNumber, dni, user, password, role); // Crea un nuevo objeto EmployeeModel
    employees.add(newEmployee); // Agrega el nuevo empleado a la lista de empleados
    saveEmployeesToJson();
    JOptionPane.showMessageDialog(null,"Empleado agregado correctamente al sistema."); // Imprime un mensaje de éxito
}
public void updateEmployeeData(int dni, String newName, String newLastName, int newPhoneNumber, String newUser, String newPassword, String Dni) {
    // Convertir el DNI proporcionado como cadena
    String providedDni = String.valueOf(dni);
    
    // Verificar si el usuario ha proporcionado un nuevo DNI y si es diferente al DNI anterior
    if (!Dni.isEmpty() && !Dni.equals(providedDni)) {
        // Verificar si ya existe un empleado con el mismo DNI
        if (isEmployeeExists(dni)) {
            System.out.println("Error: Ya existe un empleado con el mismo DNI en el sistema. No se pueden actualizar los datos.");
            return; // Salir del método si ya existe un empleado con el mismo DNI
        }
    }

    // Buscar al empleado por su DNI
    EmployeeModel employeeToUpdate = null;
    for (int i = 0; i < employees.size(); i++) {
        if (employees.get(i).getDni() == dni) {
            employeeToUpdate = employees.get(i);
            break;
        }
    }
    
    // Verificar si se encontró al empleado
    if (employeeToUpdate != null) {
        // Actualizar los datos del empleado encontrado
        employeeToUpdate.setName(newName);
        employeeToUpdate.setLastName(newLastName);
        employeeToUpdate.setPhoneNumber(newPhoneNumber);
        employeeToUpdate.setUser(newUser);
        employeeToUpdate.setPassword(newPassword);
        
        // Guardar los cambios en el archivo JSON
        saveEmployeesToJson();
        
        System.out.println("Datos del empleado actualizados correctamente.");
    } else {
        System.out.println("No se encontró ningún empleado con el DNI proporcionado.");
    }
}



// Método para verificar si ya existe un empleado con el mismo DNI
private boolean isEmployeeExists(int dni) {
    // Obtener el tamaño de la lista de empleados
    int size = employees.size();
    
    // Inicializar el índice del bucle
    int index = 0;
    
    // Bucle mientras el índice sea menor que el tamaño de la lista
    while (index < size) {
        // Obtener el empleado en la posición del índice
        EmployeeModel employee = employees.get(index);
        
        // Verificar si el DNI del empleado coincide con el DNI proporcionado
        if (employee.getDni() == dni) {
            return true; // Devolver true si se encuentra un empleado con el mismo DNI
        }
        
        // Incrementar el índice para pasar al siguiente elemento
        index++;
    }
    
    // Si no se encuentra el empleado, devolver false
    return false;
}




    // Método para obtener la lista de empleados
    public LinkedList<EmployeeModel> getEmployeeList() {
        loadEmployeesFromJson();
        return employees; // Retorna la lista de empleados
    }

    // Método para establecer la lista de empleados
    public void setEmployeeList(LinkedList<EmployeeModel> employeeList) {
        this.employees = employeeList; // Asigna la lista de empleados recibida como parámetro
    }

    public void deleteAllEmployees() {
        employees.clear(); // Borra toda la lista de empleados
    }    

    public EmployeeModel findEmployeeByDNI(int dni) {
        // Recorrer la lista de empleados para encontrar el empleado con el DNI dado
        for (int i = 0; i < employees.size(); i++) {
            EmployeeModel employee = employees.get(i);
            if (employee.getDni() == dni) {
                return employee; // Devolver el empleado si se encuentra
            }
        }
        return null; // Devolver null si no se encuentra ningún empleado con el DNI dado
    }

    public void deleteEmployee(int employeeDNI) {
        // Crear una LinkedList temporal para almacenar los datos
        LinkedList<EmployeeModel> tempEmployeeList = new LinkedList<>();
        if (employeeDNI == loginController.loggedDNI()) {
            JOptionPane.showMessageDialog(null, "ERROR No puedes eliminarte a ti mismo!");
        } else {
        // Recorrer los elementos originales y agregar aquellos cuyo DNI no coincida con el DNI a eliminar
        for (int i = 0; i < employees.size(); i++) {
            EmployeeModel employee = employees.get(i);
            if (employee.getDni() != employeeDNI) {
                tempEmployeeList.add(employee); // Agregar el empleado a la lista temporal
            }
        }
        
        // Actualizar la lista de empleados con la lista temporal
        employees = tempEmployeeList;
        saveEmployeesToJson();
    }
    }

    public String getEmployeeFullName(String username) {
        // Obtener el tamaño de la lista de empleados
        int size = employees.size();
        
        // Inicializar el índice del bucle
        int index = 0;
        
        // Bucle mientras el índice sea menor que el tamaño de la lista
        while (index < size) {
            // Obtener el empleado en la posición del índice
            EmployeeModel employee = employees.get(index);
            
            // Verificar si el nombre de usuario coincide
            if (employee.getUser().equals(username)) {
                // Si se encuentra el empleado, devolver su nombre completo
                return employee.getName() + " " + employee.getLastName();
            }
            
            // Incrementar el índice para pasar al siguiente elemento
            index++;
        }
        
        // Si no se encuentra el empleado, devolver una cadena vacía
        return "";
    }
}
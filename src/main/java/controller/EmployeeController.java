package controller;


import willy.linkedlist.doubly.LinkedList;

import javax.swing.JOptionPane;

import model.EmployeeModel;
import view.EmployeeView;

public class EmployeeController {
    private LinkedList<EmployeeModel> employees; // Lista enlazada para almacenar los empleados
    private EmployeeModel model; // Modelo de empleado
    private EmployeeView view;
    static LoginController loginController;
    public EmployeeController(EmployeeModel model,EmployeeView view) {
        this.model = model;
        this.view = view;
        this.employees = new LinkedList<>();
    }


    // Método para agregar un nuevo empleado al sistema
// Método para agregar un nuevo empleado al sistema
public void addEmployee(String name, String lastName, int phoneNumber, int dni, String user, String password) {
    // Verificar si ya existe un empleado con el mismo DNI
    if (isEmployeeExists(dni)) {
        JOptionPane.showMessageDialog(null,"Error: Ya existe un empleado con el mismo DNI en el sistema.");
        return; // Salir del método si ya existe un empleado con el mismo DNI
    }
    
    // Si no hay un empleado con el mismo DNI, proceder con la adición del nuevo empleado
    EmployeeModel newEmployee = new EmployeeModel(name, lastName, phoneNumber, dni, user, password); // Crea un nuevo objeto EmployeeModel
    employees.add(newEmployee); // Agrega el nuevo empleado a la lista de empleados
    JOptionPane.showMessageDialog(null,"Empleado agregado correctamente al sistema."); // Imprime un mensaje de éxito
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



    // Método para obtener una representación de texto de la lista de empleados en el sistema
    public String getEmployees() {
        StringBuilder message = new StringBuilder(); // Crea un StringBuilder para construir el mensaje

        // Verifica si la lista de empleados no está vacía
        if (employees != null && !employees.isEmpty()) {
            message.append("Lista de empleados:\n");
            // Itera sobre la lista de empleados y agrega los detalles de cada empleado al mensaje
            for (int i = 0; i < employees.size(); i++) {
                EmployeeModel employee = employees.get(i);
                message.append("Nombre: ").append(employee.getName()).append("\n")
                        .append("Apellido: ").append(employee.getLastName()).append("\n")
                        .append("Número de teléfono: ").append(employee.getPhoneNumber()).append("\n")
                        .append("DNI: ").append(employee.getDni()).append("\n");
            }
        } else { // Si la lista de empleados está vacía, agrega un mensaje indicando que no hay empleados en el sistema
            message.append("No hay empleados en el sistema.");
        }

        return message.toString(); // Retorna el mensaje como una cadena de texto
    }

    // Método para obtener la lista de empleados
    public LinkedList<EmployeeModel> getEmployeeList() {
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
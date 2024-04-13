package controller;


import willy.linkedlist.doubly.LinkedList;
import model.EmployeeModel;
import view.EmployeeView;

public class EmployeeController {
    private LinkedList<EmployeeModel> employees; // Lista enlazada para almacenar los empleados
    private EmployeeModel model; // Modelo de empleado
    private EmployeeView view;

    public EmployeeController(EmployeeModel model,EmployeeView view) {
        this.model = model;
        this.view = view;
        this.employees = new LinkedList<>();
    }


    // Método para agregar un nuevo empleado al sistema
    public void addEmployee(String name, String lastName, int phoneNumber, int dni, String user, String password) {
        EmployeeModel newEmployee = new EmployeeModel(name, lastName, phoneNumber, dni, user, password); // Crea un nuevo objeto EmployeeModel
        employees.add(newEmployee); // Agrega el nuevo empleado a la lista de empleados
        System.out.println("Empleado agregado correctamente al sistema."); // Imprime un mensaje de éxito
        System.out.println("Lista de empleados después de agregar un nuevo empleado: " + employees); // Imprime la lista de empleados (depuración)
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
                        .append("DNI: ").append(employee.getDni()).append("\n")
                        .append("Usuario: ").append(employee.getUser()).append("\n")
                        .append("Contraseña: ").append(employee.getPassword()).append("\n\n");
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


package view;
import java.io.File;

import javax.swing.JOptionPane;

import controller.EmployeeController;
import controller.FileJsonAdapter;
import controller.LoginController;
import controller.TrainController;
import model.EmployeeModel;
import model.TrainModel;
import view.EmployeeView;
import view.TrainView;
import willy.linkedlist.doubly.LinkedList;
public class MainTemp {
    public static void main(String[] args) {
        new MainTemp().startApplication();
    }
    public void startApplication() {
        // Crear instancias del modelo, la vista y el controlador
        TrainModel trainModel = new TrainModel();
        TrainView trainView = new TrainView();
        TrainController trainController = new TrainController(trainModel, trainView);

        // Crear instancias del modelo, la vista y el controlador
        EmployeeModel employeeModel = new EmployeeModel();
        EmployeeView employeeView = new EmployeeView();
        EmployeeController employeeController = new EmployeeController(employeeModel, employeeView);
        LoginController loginController = new LoginController();

        // Crear una instancia de FileJsonAdapter para trenes
        FileJsonAdapter<TrainModel> trainJsonAdapter = FileJsonAdapter.getInstance();
        // Ruta completa del archivo JSON para trenes
        String trainFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "trains.json";
        
        // Crear una instancia de FileJsonAdapter para empleados
        FileJsonAdapter<EmployeeModel> employeeJsonAdapter = FileJsonAdapter.getInstance();
        // Ruta completa del archivo JSON para empleados
        String employeeFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "employees.json";

        // Leer los datos del archivo JSON y establecer la lista de trenes en el controlador de trenes
        LinkedList<TrainModel> trainList = trainJsonAdapter.getObjects(trainFilePath, TrainModel[].class);
        trainController.setTrainList(trainList);

        // Leer los datos del archivo JSON y establecer la lista de empleados en el controlador de empleados
        LinkedList<EmployeeModel> employeeList = employeeJsonAdapter.getObjects(employeeFilePath, EmployeeModel[].class);
        employeeController.setEmployeeList(employeeList);
        
        // Solicitar información del tren y agregarlo al modelo
        int tCapacityLoad; // capacidad del tren o vagones
        int tMileage; // kilometraje del tren
        String tName; // nombre del tren
        String tIdentifier; // identificador del tren

        // Solicitar información del empleado y agregarlo al modelo
        String eName;
        String eLastName;
        int ePhoneNumber;
        int eDNI;   
        
        // Definir variable para los switches
        int answer; 

        do {
            answer = Integer.parseInt(JOptionPane.showInputDialog("Que desea hacer?\n1. Gestionar Trenes\n2. Gestionar Rutas\n3. Gestionar Empleados"));
            switch (answer) {
                case 1:
            answer = Integer.parseInt(JOptionPane.showInputDialog("Que desea hacer?\n1. ver lista de trenes\n2. agregar trenes\n3. eliminar un tren\n4. eliminar TODOS los trenes\n5. buscar trenes"));
            switch (answer) {
                case 1:
                    // Mostrar los trenes en el modelo
                    JOptionPane.showMessageDialog(null, trainController.getTrains());
                    break;
                    case 2:
                    // Solicitar al usuario el tipo de tren
                    int typeAnswer = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese el tipo de tren:\n1. Mercedes-Benz\n2. Arnold"));
                    switch (typeAnswer) {
                        case 1:
                            tName = "Mercedes-Benz";
                            do {
                                // Solicitar la capacidad del tren
                                tCapacityLoad = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese la cantidad de vagones"));
                                if (tCapacityLoad <= 28) {
                                    // Solicitar el kilometraje del tren
                                    tMileage = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese el kilometraje del tren"));
                                    // Solicitar el identificador del tren
                                    tIdentifier = JOptionPane.showInputDialog("Por favor, ingrese el serial del tren");
                                    // Agregar el tren al modelo
                                    trainController.addTrain(tName, tIdentifier, tCapacityLoad, tMileage);
                                    int cargo = tCapacityLoad / 3;
                                    int passenger = tCapacityLoad - cargo;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad válida");
                                }
                            } while (tCapacityLoad > 28);
                            // Mostrar los trenes en el modelo
                            JOptionPane.showMessageDialog(null, trainController.getTrains());
                            break;
                        case 2:
                            tName = "Arnold";
                            do {
                                // Solicitar la capacidad del tren
                                tCapacityLoad = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese la cantidad de vagones de pasajeros"));
                                if (tCapacityLoad <= 32) {
                                    // Solicitar el kilometraje del tren
                                    tMileage = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese el kilometraje del tren"));
                                    // Solicitar el identificador del tren
                                    tIdentifier = JOptionPane.showInputDialog("Por favor, ingrese el serial del tren");
                                    // Agregar el tren al modelo
                                    trainController.addTrain(tName, tIdentifier, tCapacityLoad, tMileage);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad válida");
                                }
                            } while (tCapacityLoad > 32);
                            // Mostrar los trenes en el modelo
                            JOptionPane.showMessageDialog(null, trainController.getTrains());
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese el tipo de tren");
                            break;
                    }
                    break;
                    case 3:
                    // Solicitar al usuario el ID del tren a eliminar
                    String trainIdToDelete = JOptionPane.showInputDialog("Ingrese el identificador del tren que desea eliminar:");
                    // Llamar al método deleteAndReorganize para eliminar el tren y reorganizar la lista
                    trainController.deleteAndReorganize(trainIdToDelete);
                    // Mostrar un mensaje indicando que el tren ha sido eliminado y la lista ha sido reorganizada
                    JOptionPane.showMessageDialog(null, "El tren con identificador " + trainIdToDelete + " ha sido eliminado correctamente.");
                    // Mostrar los trenes en la lista actualizada
                    JOptionPane.showMessageDialog(null, trainController.getTrains());
                    break;
                
                    case 4:
                    // Llamar al método deleteAllTrains para eliminar toda la lista de trenes
                    trainController.deleteAllTrains();
                    // Mostrar un mensaje indicando que todos los trenes han sido eliminados
                    JOptionPane.showMessageDialog(null, "Todos los trenes han sido eliminados correctamente.");
                    break;
                    case 5:
                    // Solicitar al usuario el identificador del tren a buscar
                    String searchIdentifier = JOptionPane.showInputDialog("Ingrese el identificador del tren que desea buscar:");
                    // Buscar el tren por su identificador utilizando el método findTrainByIdentifier
                    TrainModel foundTrain = trainController.findTrainByIdentifier(searchIdentifier);
                    // Verificar si se encontró el tren
                    if (foundTrain != null) {
                        // Mostrar los detalles del tren encontrado
                        JOptionPane.showMessageDialog(null, "Se encontró el tren:\n\n" + 
                                                            "Nombre: " + foundTrain.getName() + "\n" +
                                                            "Identificador: " + foundTrain.getIdentifier() + "\n" +
                                                            "Capacidad de carga: " + foundTrain.getCapacityLoad() + "\n" +
                                                            "Millas: " + foundTrain.getMileage());
                    } else {
                        // Mostrar un mensaje indicando que el tren no fue encontrado
                        JOptionPane.showMessageDialog(null, "No se encontró ningún tren con el identificador proporcionado.");
                    }
                    break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una opción válida");
                    break;
            }
            break;
            case 2:
            //logica de gestion de rutas
            break;
        case 3:
            //Logica de gestion de empleados
            answer = Integer.parseInt(JOptionPane.showInputDialog("Que desea hacer?\n1. Ver lista de Empleados\n2. Agregar Empleado\n3. Buscar empleados\n4. Eliminar empleados"));
            switch (answer) {
                case 1:
                // Mostrar lista de empleados
                JOptionPane.showMessageDialog(null, employeeController.getEmployees());
                break;
                case 2:
                // Agregar empleado
                String name = JOptionPane.showInputDialog("Ingrese el nombre del empleado:");
                String lastName = JOptionPane.showInputDialog("Ingrese el apellido del empleado:");
                int phoneNumber = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de teléfono del empleado:"));
                int dni = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el DNI del empleado:"));
            
                // Crear instancia de EmployeeModel
                EmployeeModel newEmployee = new EmployeeModel(name, lastName, phoneNumber, dni);
            
                // Generar usuario y contraseña automáticamente
                String eUser = newEmployee.generateUsername();
                String ePassword = newEmployee.generatePassword();
            
                // Agregar la instancia al controlador
                employeeController.addEmployee(name, lastName, phoneNumber, dni, eUser, ePassword);
                break;
            
            case 3:
                // Buscar empleado
                int searchDNI = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el DNI del empleado que desea buscar:"));
                EmployeeModel foundEmployee = employeeController.findEmployeeByDNI(searchDNI);
                if (foundEmployee != null) {
                    // Mostrar detalles del empleado encontrado
                    JOptionPane.showMessageDialog(null, "Empleado encontrado:\n\n" + 
                                                        "Nombre: " + foundEmployee.getName() + "\n" +
                                                        "Apellido: " + foundEmployee.getLastName() + "\n" +
                                                        "Teléfono: " + foundEmployee.getPhoneNumber() + "\n" +
                                                        "DNI: " + foundEmployee.getDni());
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún empleado con el DNI proporcionado.");
                }
                break;
            case 4:
                // Eliminar empleado
                int employeeDNI = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el DNI del empleado que desea eliminar:"));
                employeeController.deleteEmployee(employeeDNI);
                break;
            }
            break;
        default:
            JOptionPane.showMessageDialog(null, "Por favor, ingrese una opción válida");
            break;
    }
            // Solicitar al usuario que continúe
            answer = Integer.parseInt(JOptionPane.showInputDialog("¿Desea continuar?\n1. Sí\n2. No"));
        } while (answer == 1);        

        // Escribir los datos en los archivos JSON
        boolean trainSuccess = trainJsonAdapter.writeObjects(trainFilePath, trainController.getTrainList());
        boolean employeeSuccess = employeeJsonAdapter.writeObjects(employeeFilePath, employeeController.getEmployeeList());

        // Mostrar mensaje de éxito o error para trenes
        if (trainSuccess) {
            JOptionPane.showMessageDialog(null, "Datos de trenes guardados correctamente en el archivo JSON.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos de trenes en el archivo JSON.");
        }

        // Mostrar mensaje de éxito o error para empleados
        if (employeeSuccess) {
            JOptionPane.showMessageDialog(null, "Datos de empleados guardados correctamente en el archivo JSON.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos de empleados en el archivo JSON.");
        }
    }
}

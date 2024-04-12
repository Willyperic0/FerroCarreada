import javax.swing.JOptionPane;

import controller.TrainController;
import model.TrainModel;
import view.TrainView;

public class App {
    // RECORDAR QUE ESTO SE HACE PARA PROBAR LA LOGICA, SE DEBE USAR JFRAME
public static void main(String[] args) {
    // crea instancias del modelo, la vista y el controlador
    TrainModel trainModel = new TrainModel();
    TrainView trainView = new TrainView();
    TrainController trainController = new TrainController(trainModel, trainView);

    // variables temporales
    int tId = 0; // identificador del tren
    int tCapacityLoad; // capacidad del tren
    int tMileage; // kilometraje del tren
    int answer; // respuesta del usuario
    String tName; // nombre del tren
    String tIdentifier; // identificador del tren
    int cargo; // número de vagones de carga
    int passengers; // número de vagones de pasajeros

    do {
        // solicita al usuario la información del tren
        tId++; // incrementa el identificador del tren
        answer = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese el tipo de tren:\n1. Mercedes-Benz\n2. Arnold"));
        switch (answer) {
            case 1:
                tName = "Mercedes-Benz";
                do {
                    // solicita la capacidad del tren
                    tCapacityLoad = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese la cantidad de vagones de pasajeros"));
                    if (tCapacityLoad <= 28) {
                        // calcula el número de vagones de carga
                        cargo = tCapacityLoad / 3;
                        // calcula el número de vagones de pasajeros
                        passengers = tCapacityLoad - cargo;
                        // solicita el kilometraje del tren
                        tMileage = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese el kilometraje del tren"));
                        // solicita el identificador del tren
                        tIdentifier = JOptionPane.showInputDialog("Por favor, ingrese el serial del tren");
                        // agrega el tren al modelo
                        trainController.addTrain(tId, tName, tIdentifier, tCapacityLoad, tMileage);
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad válida");
                    }
                } while (tCapacityLoad > 28);
                break;
            case 2:
                tName = "Arnold";
                do {
                    // solicita la capacidad del tren
                    tCapacityLoad = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese la cantidad de vagones de pasajeros"));
                    if (tCapacityLoad <= 32) {
                        // calcula el número de vagones de carga
                        cargo = tCapacityLoad / 3;
                        // calcula el número de vagones de pasajeros
                        passengers = tCapacityLoad - cargo;
                        // solicita el kilometraje del tren
                        tMileage = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese el kilometraje del tren"));
                        // solicita el identificador del tren
                        tIdentifier = JOptionPane.showInputDialog("Por favor, ingrese el serial del tren");
                        // agrega el tren al modelo
                        trainController.addTrain(tId, tName, tIdentifier, tCapacityLoad, tMileage);
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese una cantidad válida");
                    }
                } while (tCapacityLoad > 32);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Por favor, ingrese el tipo de tren");
                break;
        }
        // muestra los trenes en el modelo
        JOptionPane.showMessageDialog(null, trainController.getTrains());
        // solicita al usuario que continúe
        answer = Integer.parseInt(JOptionPane.showInputDialog("¿Desea continuar?\n1. Sí\n2. No"));
    } while (answer == 1);
    // muestra un mensaje de agradecimiento
    JOptionPane.showMessageDialog(null, "Gracias por usar este programa");
}
    //FALTA LA CREACION DE LOS METODOS PARA LA BASE DE DATOS Y UNA INTERFAZ PARA MANEJO DE TRENES
}
 
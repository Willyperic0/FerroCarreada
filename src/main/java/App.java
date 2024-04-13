import java.io.File;

import javax.swing.JOptionPane;

import controller.FileJsonAdapter;
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
            int tCapacityLoad; // capacidad del tren o vagones
            int tMileage; // kilometraje del tren
            int answer; // respuesta del usuario
            String tName; // nombre del tren
            String tIdentifier; // identificador del tren
    
            // Crear una instancia de FileJsonAdapter
            FileJsonAdapter<TrainModel> jsonAdapter = FileJsonAdapter.getInstance();
            // Carpeta donde se guardará el archivo JSON
            String folderPath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database";
            File folder = new File(folderPath);
        // Crear la carpeta si no existe
        if (!folder.exists()) {
            folder.mkdirs();
        }
            do {
                // solicita al usuario la información del tren
                tId++; // incrementa el identificador del tren
                answer = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese el tipo de tren:\n1. Mercedes-Benz\n2. Arnold"));
                switch (answer) {
                    case 1:
                        tName = "Mercedes-Benz";
                        do {
                            // solicita la capacidad del tren
                            tCapacityLoad = Integer.parseInt(JOptionPane.showInputDialog("Por favor, ingrese la cantidad de vagones"));
                            if (tCapacityLoad <= 28) {
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
    
    // Ruta completa del archivo JSON
    String filePath = folderPath + File.separator + "trains.json";


        boolean success = jsonAdapter.writeObjects(filePath,trainController.getTrainList());

            
    
            if (success) {
                JOptionPane.showMessageDialog(null, "Datos guardados correctamente en el archivo JSON.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar los datos en el archivo JSON.");
            }
        }
    //FALTA LA CREACION DE LOS METODOS PARA LA BASE DE DATOS Y UNA INTERFAZ PARA MANEJO DE TRENES
}
 
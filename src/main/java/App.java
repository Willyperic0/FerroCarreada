import java.io.File;

import javax.swing.JOptionPane;

import controller.FileJsonAdapter;
import controller.TrainController;
import model.TrainModel;
import view.TrainView;
import willy.linkedlist.doubly.LinkedList;

public class App {
    public static void main(String[] args) {
        // Crear instancias del modelo, la vista y el controlador
        TrainModel trainModel = new TrainModel();
        TrainView trainView = new TrainView();
        TrainController trainController = new TrainController(trainModel, trainView);

        // Crear una instancia de FileJsonAdapter
        FileJsonAdapter<TrainModel> jsonAdapter = FileJsonAdapter.getInstance();
        
        // Carpeta donde se guardará el archivo JSON
        String folderPath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database";
        File folder = new File(folderPath);

        // Crear la carpeta si no existe
        if (!folder.exists()) {
            folder.mkdirs();
        }
        
        // Ruta completa del archivo JSON
        String filePath = folderPath + File.separator + "trains.json";
        
        // Leer los datos del archivo JSON y establecer la lista de trenes en el controlador
        LinkedList<TrainModel> trainList = jsonAdapter.getObjects(filePath, TrainModel[].class);
        
        // Obtener el último ID de tren en la lista de trenes leídos del archivo JSON
        int lastTrainId = 0;
        for (int i = 0; i < trainList.size(); i++) {
            TrainModel train = trainList.get(i);
            lastTrainId = Math.max(lastTrainId, train.getTrainId());
        }
        
        // Incrementar el último ID de tren para usarlo como ID base para los nuevos trenes
        int tId = lastTrainId + 1;
        
        // Establecer el ID del tren en el modelo para comenzar desde el próximo ID disponible
        trainModel.setTrainId(tId);
        
        // Establecer la lista de trenes en el controlador
        trainController.setTrainList(trainList);
        
        // Solicitar información del tren y agregarlo al modelo
        int tCapacityLoad; // capacidad del tren o vagones
        int tMileage; // kilometraje del tren
        int answer; // respuesta del usuario
        String tName; // nombre del tren
        String tIdentifier; // identificador del tren
        
        do {
            answer = Integer.parseInt(JOptionPane.showInputDialog("Que desea hacer?\n1. ver lista de trenes\n2. agregar trenes\n3. eliminar un tren\n4. eliminar TODOS los trenes"));
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
                                    trainController.addTrain(tId, tName, tIdentifier, tCapacityLoad, tMileage);
                                    // Incrementar el ID del tren para el próximo tren
                                    tId++;
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
                                    trainController.addTrain(tId, tName, tIdentifier, tCapacityLoad, tMileage);
                                    // Incrementar el ID del tren para el próximo tren
                                    tId++;
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
                    // Implementar la lógica para eliminar un tren
                    JOptionPane.showMessageDialog(null, "MODULO NO DISPONIBLE POR EL MOMENTO");
                    break;
                case 4:
                    // Implementar la lógica para eliminar todos los trenes
                    JOptionPane.showMessageDialog(null, "MODULO NO DISPONIBLE POR EL MOMENTO");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una opción válida");
                    break;
            }
            // Solicitar al usuario que continúe
            answer = Integer.parseInt(JOptionPane.showInputDialog("¿Desea continuar?\n1. Sí\n2. No"));
        } while (answer == 1);        

        // Escribir los datos en el archivo JSON
        boolean success = jsonAdapter.writeObjects(filePath, trainController.getTrainList());
        
        // Mostrar mensaje de éxito o error
        if (success) {
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente en el archivo JSON.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos en el archivo JSON.");
        }
    }
}
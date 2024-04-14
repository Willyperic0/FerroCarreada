package controller;

import model.TrainModel;
import view.TrainView;
import willy.linkedlist.doubly.LinkedList;

public class TrainController {
    private LinkedList<TrainModel> trains; // Lista enlazada para almacenar los trenes
    private TrainModel model; // Modelo de tren
    private TrainView view; // Vista de tren

    // Constructor de la clase TrainController
    public TrainController(TrainModel model, TrainView view) { // Este metodo se esta usando en App
        this.model = model; // Asigna el modelo de tren
        this.view = view; // Asigna la vista de tren
        this.trains = new LinkedList<>(); // Inicializa la lista de trenes
    }

    // Método para agregar un nuevo tren al sistema
    public void addTrain(String name, String identifier, int capacityLoad, int mileage) { // Este metodo se esta usando en App
        TrainModel nuevoTren = new TrainModel(name, identifier, capacityLoad, mileage); // Crea un nuevo objeto TrainModel
        trains.add(nuevoTren); // Agrega el nuevo tren a la lista de trenes
        System.out.println("Tren agregado correctamente al sistema."); // Imprime un mensaje de éxito
        System.out.println("Lista de trenes después de agregar un nuevo tren: " + trains); // Imprime la lista de trenes (depuración)
    }

    // Método para obtener una representación de texto de la lista de trenes en el sistema
    public String getTrains() { // Este metodo se esta usando en App
        LinkedList<TrainModel> trains = this.trains; // Obtiene la lista de trenes del controlador
        StringBuilder message = new StringBuilder(); // Crea un StringBuilder para construir el mensaje

        // Verifica si la lista de trenes no está vacía
        if (trains != null && !trains.isEmpty()) {
            message.append("Lista de trenes:\n");
            // Itera sobre la lista de trenes y agrega los detalles de cada tren al mensaje
            for (int i = 0; i < trains.size(); i++) {
                TrainModel train = trains.get(i);
                message.append("Nombre: ").append(train.getName()).append("\n")
                        .append("Identificador: ").append(train.getIdentifier()).append("\n")
                        .append("Capacidad de carga: ").append(train.getCapacityLoad()).append("\n")
                        .append("Millas: ").append(train.getMileage()).append("\n\n");
            }
        } else { // Si la lista de trenes está vacía, agrega un mensaje indicando que no hay trenes en el sistema
            message.append("No hay trenes en el sistema.");
        }

        return message.toString(); // Retorna el mensaje como una cadena de texto
    }

    // Método para obtener la lista de trenes
    public LinkedList<TrainModel> getTrainList() {
        return trains; // Retorna la lista de trenes
    }

    // Método para establecer la lista de trenes
    public void setTrainList(LinkedList<TrainModel> trainList) {
        this.trains = trainList; // Asigna la lista de trenes recibida como parámetro
    }

    public void deleteAndReorganize(String trainIdToDelete) {
     // Crear una LinkedList temporal para almacenar los datos
     LinkedList<TrainModel> tempTrainList = new LinkedList<>();
    
     // Recorrer los elementos originales y agregar aquellos cuyo identificador no coincida con el identificador a eliminar
     for (int i = 0; i < trains.size(); i++) {
         TrainModel train = trains.get(i);
         if (!train.getIdentifier().equals(trainIdToDelete)) {
             tempTrainList.add(train); // Agregar el tren a la lista temporal
         }
     }
     
     // Actualizar la lista de trenes con la lista temporal
     trains = tempTrainList;
    }
    
    public void deleteAllTrains() {
        trains.clear(); // Borra toda la lista de trenes
    }    

    public TrainModel findTrainByIdentifier(String identifier) {
        // Recorrer la lista de trenes para encontrar el tren con el identificador dado
        for (int i = 0; i < trains.size(); i++) {
            TrainModel train = trains.get(i);
            if (train.getIdentifier().equals(identifier)) {
                return train; // Devolver el tren si se encuentra
            }
        }
        return null; // Devolver null si no se encuentra ningún tren con el identificador dado
    }
    
}

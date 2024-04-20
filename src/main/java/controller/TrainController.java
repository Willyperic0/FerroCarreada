package controller;

import java.io.File;

import javax.swing.JOptionPane;

import model.TrainModel;

import willy.linkedlist.doubly.LinkedList;

public class TrainController {
    private LinkedList<TrainModel> trains; // Lista enlazada para almacenar los trenes
    private TrainModel model; // Modelo de tren


    // Constructor de la clase TrainController
    public TrainController(TrainModel model) { // Este metodo se esta usando en App
        this.model = model; // Asigna el modelo de tre
        this.trains = new LinkedList<>(); // Inicializa la lista de trenes
    }

    public void addTrain(String name, String identifier, int capacityLoad, int mileage) {
        System.out.println("Add button clicked");
        // Verificar si ya existe un tren con el mismo identificador
        if (isTrainExists(identifier)) {
            JOptionPane.showMessageDialog(null,"Error: Ya existe un tren con el mismo identificador en el sistema.");
            return; // Salir del método si ya existe un tren con el mismo identificador
        }
        
        // Si no hay un tren con el mismo identificador, proceder con la adición del nuevo tren
        TrainModel newTrain = new TrainModel(name, identifier, capacityLoad, mileage); // Crea un nuevo objeto TrainModel
        
        // Calcular la cantidad de carga y pasajeros
        int cargo = newTrain.calculateCargo(capacityLoad);
        int passenger = newTrain.calculatePassenger(capacityLoad, cargo);
        
        // Establecer la cantidad de carga y pasajeros en el objeto TrainModel
        newTrain.setCargo(cargo);
        newTrain.setPassenger(passenger);
        
        // Calcula el número de vagones VIP, ejecutivos y estándar
        int executiveVagons = newTrain.calculateExecutive(passenger);
        int standardVagons = newTrain.calculateStandard(passenger);
        int vipVagons = newTrain.calculateVIP(passenger);

        newTrain.setExecutiveVagons(executiveVagons);
        newTrain.setStandardVagons(standardVagons);
        newTrain.setvipVagons(vipVagons);
        
        trains.add(newTrain); // Agrega el nuevo tren a la lista de trenes
        
        // Guardar la lista actualizada de trenes en el archivo JSON
        String trainFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "trains.json";
        saveTrainsToJson(trainFilePath);
        
        JOptionPane.showMessageDialog(null,"Tren agregado correctamente al sistema."); // Imprime un mensaje de éxito
    }
    
    

    // Método para verificar si ya existe un tren con el mismo identificador
private boolean isTrainExists(String identifier) {
    // Obtener el tamaño de la lista de trenes
    int size = trains.size();
    
    // Inicializar el índice del bucle
    int index = 0;
    
    // Bucle mientras el índice sea menor que el tamaño de la lista
    while (index < size) {
        // Obtener el tren en la posición del índice
        TrainModel train = trains.get(index);
        
        // Verificar si el identificador del tren coincide con el identificador proporcionado
        if (train.getIdentifier().equals(identifier)) {
            return true; // Devolver true si se encuentra un tren con el mismo identificador
        }
        
        // Incrementar el índice para pasar al siguiente elemento
        index++;
    }
    
    // Si no se encuentra el tren, devolver false
    return false;
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
    // Especifica la ruta completa del archivo JSON para los trenes
    String trainFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "trains.json";
    
    // Carga los datos de trenes desde el archivo JSON antes de devolver la lista de trenes
    loadTrainsFromJson(trainFilePath);

    // Retorna la lista de trenes
    return trains;
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
        
        // Limpiar la lista original de trenes
        trains.clear();
        
        // Agregar los trenes de la lista temporal a la lista original uno por uno
        for (int i = 0; i < tempTrainList.size(); i++) {
            trains.add(tempTrainList.get(i));
        }
        String trainFilePath = "FerroCarreada" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "trains.json";
        saveTrainsToJson(trainFilePath);
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
        // Método para cargar los datos de trenes desde un archivo JSON
        public void loadTrainsFromJson(String trainFilePath) {
            // Crear una instancia de FileJsonAdapter para trenes
            FileJsonAdapter<TrainModel> trainJsonAdapter = FileJsonAdapter.getInstance();
        
            // Leer los datos del archivo JSON y establecer la lista de trenes en el controlador de trenes
            LinkedList<TrainModel> trainList = trainJsonAdapter.getObjects(trainFilePath, TrainModel[].class);
        
            // Verificar si se leyeron correctamente los datos
            if (trainList != null) {
                // Actualizar la lista de trenes en el controlador de trenes
                this.trains = trainList;
            } else {
                System.out.println("Error al leer los datos de trenes desde el archivo JSON.");
            }
        }
            // Método para guardar los trenes en el archivo JSON
    public void saveTrainsToJson(String trainFilePath) {
        // Crear una instancia de FileJsonAdapter para trenes
        FileJsonAdapter<TrainModel> trainJsonAdapter = FileJsonAdapter.getInstance();

        // Guardar los datos de trenes en un archivo JSON
        boolean success = trainJsonAdapter.writeObjects(trainFilePath, trains);

        // Mostrar mensaje de éxito o error para trenes
        if (success) {
            System.out.println("Datos de trenes guardados correctamente en el archivo JSON.");
        } else {
            System.out.println("Error al guardar los datos de trenes en el archivo JSON.");
        }
    }
}

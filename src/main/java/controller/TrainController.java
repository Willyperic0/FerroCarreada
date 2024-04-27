package controller;

import java.io.File;

import javax.swing.JOptionPane;

import model.RouteModel;
import model.TrainModel;

import willy.linkedlist.doubly.LinkedList;

public class TrainController {
    private LinkedList<TrainModel> trains; // Lista enlazada para almacenar los trenes
    private TrainModel model; // Modelo de tren
    


    // Constructor de la clase TrainController
    public TrainController(TrainModel model) { // Este metodo se esta usando en App
        this.model = model; // Asigna el modelo de tre
        this.trains = new LinkedList<>(); // Inicializa la lista de trenes
        loadTrainsFromJson();
    }
    public void updateTrainDataTicket(String trainIdToUpdate, String category) {
        // Crear una LinkedList temporal para almacenar los datos
        LinkedList<TrainModel> tempTrainList = new LinkedList<>();
        
        // Recorrer los elementos originales y agregar aquellos cuyo identificador no coincida con el identificador a eliminar
        for (int i = 0; i < trains.size(); i++) {
            TrainModel train = trains.get(i);
            if (!train.getIdentifier().equals(trainIdToUpdate)) {
                tempTrainList.add(train); // Agregar el tren a la lista temporal
            } else {
                // Actualizar el número de vagones según la categoría
                switch (category) {
                    case "VIP":
                        int updatedVipVagons = updateVip(train);
                        train.setvipVagons(updatedVipVagons);
                        break;
                    case "Executive":
                        int updatedExecutiveVagons = updateExecutive(train);
                        train.setExecutiveVagons(updatedExecutiveVagons);
                        break;
                    case "Standard":
                    default:
                        int updatedStandardVagons = updateStandard(train);
                        train.setStandardVagons(updatedStandardVagons);
                        break;
                }
                tempTrainList.add(train); // Agregar el tren actualizado a la lista temporal
            }
        }
        
        // Limpiar la lista original de trenes
        trains.clear();
        
        // Agregar los trenes de la lista temporal a la lista original uno por uno
        for (int i = 0; i < tempTrainList.size(); i++) {
            trains.add(tempTrainList.get(i));
        }
        
        // Guardar la lista actualizada de trenes en el archivo JSON
        saveTrainsToJson();
    }
    public void modifyTrain(String trainIdToModify, String newIdentifier, int oldCapacityLoad, int newCapacityLoad) {
        RouteController routeController = new RouteController();
            // Obtener la lista temporal de rutas
    LinkedList<RouteModel> tempRoutes = routeController.getRoutes();
        // Verificar si el tren existe en la base de datos
        boolean trainFound = false;
        TrainModel trainToModify = null;
        for (int i = 0; i < trains.size(); i++) {
            TrainModel train = trains.get(i);
            if (train.getIdentifier().equals(trainIdToModify)) {
                trainToModify = train;
                trainFound = true;
                break;
            }
        }
        System.out.println(trainIdToModify);
        // Si no se encontró el tren a modificar, mostrar un mensaje y salir del método
        if (!trainFound) {
            System.out.println("No se encontró ningún tren con el identificador " + trainIdToModify);
            return;
        }
        // Verificar que el nuevo identificador no sea el identificador de un tren existente
        if (trainIdToModify != newIdentifier){
            trainFound = false;

            for (int i = 0; i < trains.size(); i++) {
                TrainModel train = trains.get(i);
                if (train.getIdentifier().equals(newIdentifier)) {
                    trainFound = true;
                    break;
                }
            }
            if (trainFound == true) {
                System.out.println("No se puede agregar este identificador debido a que ya hay uno existente " + newIdentifier);
                return;
            }
        }

    
    // Verificar si el tren está asignado a alguna ruta
    trainFound = false;
    for (int i = 0; i < tempRoutes.size(); i++) {
        RouteModel route = tempRoutes.get(i);
        if (route.getTrainModel().getIdentifier().equals(trainIdToModify)) {
            trainFound = true;
            break;
        }
    }
    if (trainFound == true) {
        System.out.println("No se puede modificar el tren " + trainIdToModify+ " debido a que ya se encuentra en ruta");
        return;
    }
    
        // Calcular la cantidad original de vagones VIP, ejecutivos y estándar
        int originalVipVagons = trainToModify.calculateVIP(trainToModify.getPassenger());
        int originalExecutiveVagons = trainToModify.calculateExecutive(trainToModify.getPassenger());
        int originalStandardVagons = trainToModify.calculateStandard(trainToModify.getPassenger());
    
        // Calcular la diferencia entre la cantidad original y la cantidad nueva de vagones VIP
        int vipVagonsDifference = originalVipVagons - trainToModify.getVipVagons();
        int executiveVagonsDifference = originalExecutiveVagons - trainToModify.getExecutiveVagons();
        int standardVagonsDifference = originalStandardVagons - trainToModify.getStandardVagons();

        int cargoCount = trainToModify.calculateCargo(newCapacityLoad);
        // Calcular la cantidad de pasajeros del tren modificado
        int passengerCount = trainToModify.calculatePassenger(newCapacityLoad, cargoCount);
    
        // Calcular la cantidad de vagones VIP, ejecutivos y estándar según la cantidad de pasajeros
        int updatedVipVagons = trainToModify.calculateVIP(passengerCount);
        int updatedExecutiveVagons = trainToModify.calculateExecutive(passengerCount);
        int updatedStandardVagons = trainToModify.calculateStandard(passengerCount) - 6;
    
        // Ajustar la cantidad de vagones VIP, ejecutivos y estándar con las diferencias calculadas
        updatedVipVagons += vipVagonsDifference;
        updatedExecutiveVagons += executiveVagonsDifference;
        updatedStandardVagons += standardVagonsDifference;
    
        // Actualizar la cantidad de vagones con los nuevos valores
        trainToModify.setIdentifier(newIdentifier);
        trainToModify.setvipVagons(updatedVipVagons);
        trainToModify.setExecutiveVagons(updatedExecutiveVagons);
        trainToModify.setStandardVagons(updatedStandardVagons);
        trainToModify.setCapacityLoad(newCapacityLoad); // Actualizar la capacidad de carga
    
        // Recalcular la cantidad de vagones y puestos disponibles
        trainToModify.calculateAndUpdatePassengerAndCargo();
    
        // Guardar la lista actualizada de trenes en el archivo JSON
        saveTrainsToJson();
    
        System.out.println("Datos del tren modificados correctamente.");
    }
    
    
    public int updateVip(TrainModel train) {
        // Actualizar la cantidad de vagones VIP en el tren seleccionado
        if (train.getVipVagons() > 0) {
            return train.getVipVagons() - 1;
        }
        else {
            return train.getVipVagons(); // No hay vagones VIP disponibles para actualizar
        }
    }
    
    public int updateStandard(TrainModel train) {
        // Actualizar la cantidad de vagones Standard en el tren seleccionado
        if (train.getStandardVagons() > 0) {
            return train.getStandardVagons() - 1;
        } else {
            return train.getStandardVagons(); // No hay vagones Standard disponibles para actualizar
        }
    }
    
    public int updateExecutive(TrainModel train) {
        // Actualizar la cantidad de vagones Executive en el tren seleccionado
        if (train.getExecutiveVagons() > 0) {
            return train.getExecutiveVagons() - 1;
        } else {
            return train.getExecutiveVagons(); // No hay vagones Executive disponibles para actualizar
        }
    }
    
    
// Método para guardar los trenes en el archivo JSON
public void saveTrainsToJson(String trainFilePath, LinkedList<TrainModel> trainList) {
    // Crear una instancia de FileJsonAdapter para trenes
    FileJsonAdapter<TrainModel> trainJsonAdapter = FileJsonAdapter.getInstance();

    // Guardar los datos de trenes en un archivo JSON
    boolean success = trainJsonAdapter.writeObjects(trainFilePath, trainList);

    // Mostrar mensaje de éxito o error para trenes
    if (success) {
        System.out.println("Datos de trenes guardados correctamente en el archivo JSON.");
    } else {
        System.out.println("Error al guardar los datos de trenes en el archivo JSON.");
    }
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
        int standardVagons = newTrain.calculateStandard(passenger)-6 ;
        int vipVagons = newTrain.calculateVIP(passenger);

        newTrain.setExecutiveVagons(executiveVagons);
        newTrain.setStandardVagons(standardVagons);
        newTrain.setvipVagons(vipVagons);
        
        trains.add(newTrain); // Agrega el nuevo tren a la lista de trenes
        
        // Guardar la lista actualizada de trenes en el archivo JSON
        saveTrainsToJson();
        
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
    
    // Carga los datos de trenes desde el archivo JSON antes de devolver la lista de trenes
    loadTrainsFromJson();

    // Retorna la lista de trenes
    return trains;
}

    // Método para establecer la lista de trenes
    public void setTrainList(LinkedList<TrainModel> trainList) {
        this.trains = trainList; // Asigna la lista de trenes recibida como parámetro
    }

// En el método deleteAndReorganize de TrainController
public void deleteAndReorganize(String trainIdToDelete) {
    RouteController routeController = new RouteController();
    // Verificar si el tren está asignado a una ruta
    if (!routeController.isTrainAvailable(trainIdToDelete)) {
        // Si el tren está asignado a una ruta, mostrar un JOptionPane
        JOptionPane.showMessageDialog(null, "El tren seleccionado no puede ser eliminado porque está asignado a una ruta.");
        return; // Salir del método sin eliminar el tren
    }
    
    // Si el tren no está asignado a ninguna ruta, proceder con la eliminación
    LinkedList<TrainModel> tempTrainList = new LinkedList<>();
    
    for (int i = 0; i < trains.size(); i++) {
        TrainModel train = trains.get(i);
        if (!train.getIdentifier().equals(trainIdToDelete)) {
            tempTrainList.add(train);
        }
    }
    
    trains.clear();
    
    for (int i = 0; i < tempTrainList.size(); i++) {
        trains.add(tempTrainList.get(i));
    }
    
    saveTrainsToJson();
}

    
    
    public void deleteAllTrains() {
        trains.clear(); // Borra toda la lista de trenes
    }    

    public TrainModel findTrainByIdentifier(String identifier) {
        // Obtener la lista de trenes
        LinkedList<TrainModel> trainList = getTrainList();
        System.out.println("Número de trenes en la lista: " + trainList.size());
        
        // Recorrer la lista de trenes para encontrar el tren con el identificador dado
        for (int i = 0; i < trainList.size(); i++) {
            TrainModel train = trainList.get(i);
            System.out.println("Identificador del tren en la posición " + i + ": " + train.getIdentifier());
            if (train.getIdentifier().equals(identifier)) {
                System.out.println("Tren encontrado con el identificador " + identifier + ": " + train.getIdentifier());
                return train; // Devolver el tren si se encuentra
            }
        }
        System.out.println("No se encontró ningún tren con el identificador " + identifier);
        return null; // Devolver null si no se encuentra ningún tren con el identificador dado
    }
    
        // Método para cargar los datos de trenes desde un archivo JSON
        public void loadTrainsFromJson() {
            String trainFilePath = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "trains.json";

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
    public void saveTrainsToJson() {
        String trainFilePath = "src" + File.separator + "main" + File.separator + "java" + File.separator + "database" + File.separator + "trains.json";

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

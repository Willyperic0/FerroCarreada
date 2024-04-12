package controller;

import model.TrainModel;
import view.TrainView;
import willy.linkedlist.doubly.LinkedList;

public class TrainController {
    private LinkedList<TrainModel> trains;
    private TrainModel model;
    private TrainView view;

    public TrainController(TrainModel model, TrainView view) {
        this.model = model;
        this.view = view;
        this.trains = new LinkedList<>(); //agregada la lista, por ello se devolvia nulo siempre
    }

    public void setTrainId(int trainId) {
        model.setTrainId(trainId);
    }

    public int getTrainId() {
        return model.getTrainId();
    }
    
    public void updateView() {
        view.TrainList(model); // Modificación aquí
        view.printTrainDetails();
    }

        // Método para agregar un nuevo tren al sistema
    public void addTrain(int trainId, String name, String identifier, int capacityLoad, int mileage) {
        TrainModel nuevoTren = new TrainModel(trainId, name, identifier, capacityLoad, mileage);
        trains.add(nuevoTren);
        System.out.println("Tren agregado correctamente al sistema.");
    }
    
     // Método para obtener la lista de trenes en el sistema 
     public String getTrains() {    // CORREGIDO METODO GETTRAINS
        LinkedList<TrainModel> trains = this.trains; // Accede directamente a la lista de trenes en el controlador
        StringBuilder message = new StringBuilder();
        
        if (trains != null && !trains.isEmpty()) {
            message.append("Lista de trenes:\n");
            for (int i = 0; i < trains.size(); i++) {
                TrainModel train = trains.get(i);
                message.append("ID: ").append(train.getTrainId()).append("\n")
                        .append("Nombre: ").append(train.getName()).append("\n")
                        .append("Identificador: ").append(train.getIdentifier()).append("\n")
                        .append("Capacidad de carga: ").append(train.getCapacityLoad()).append("\n")
                        .append("Millas: ").append(train.getMileage()).append("\n\n");
            }
        } else {
            message.append("No hay trenes en el sistema.");
        }
        
        return message.toString();
    }
    
    
}

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
    public LinkedList<TrainModel> getTrains() {
      return trains;
    }
    
}

package view;

import model.TrainModel;
import willy.linkedlist.singly.LinkedList;

public class TrainView {
    private LinkedList listTrain = new LinkedList<>();
    public void TrainList(TrainModel train) {
        listTrain.add("ID: "+train.getTrainId()+", ");
        listTrain.add("Nombre: " + train.getName());
        listTrain.add("Identificador: " + train.getIdentifier());
        listTrain.add("Capacidad de carga: " + train.getCapacityLoad());
        listTrain.add("Millas: " + train.getMileage());
    }
    public void printTrainDetails() {
        System.out.println("Lista de detalles del tren:");
        for (int i = 0; i < listTrain.size(); i++) {
            System.out.println(listTrain.get(i));
        }
    }
    
}
package view;

import model.TrainModel;

public class TrainView {
    public void printTrainDetails(TrainModel train) {
        System.out.println("Detalles del tren:");
        System.out.println("ID: " + train.getTrainId());
        System.out.println("Nombre: " + train.getName());
        System.out.println("Identificador: " + train.getIdentifier());
        System.out.println("Capacidad de carga: " + train.getCapacityLoad());
        System.out.println("Millas: " + train.getMileage());
    }
}

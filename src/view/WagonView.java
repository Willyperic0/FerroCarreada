package view;

import model.WagonModel;

public class WagonView {
    public void displayWagon(WagonModel wagon) {
        System.out.println("Detalles del vagon:");
        System.out.println("ID: " + wagon.getIdWagon());
        System.out.println("Tipo de vagon: " + wagon.getType());
        System.out.println("Capacidad: " + wagon.getCapacity());
        System.out.println("Capacidad de pasajeros: " + wagon.getPassengerCapacity());
        System.out.println("Capacidad de maletas: " + wagon.getLuggageCapacity());
        System.out.println("ID del tren al que pertenece: " + wagon.getTrain().getTrainId());
    }
}

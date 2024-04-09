package view;

import model.WagonModel;
import willy.linkedlist.singly.LinkedList;

public class WagonView {
    private LinkedList listWagon = new LinkedList<>();
    public void WagonList(WagonModel wagon) {
        listWagon.add("ID: " + wagon.getIdWagon());
        listWagon.add("Tipo de vagon: " + wagon.getType());
        listWagon.add("Capacidad: " + wagon.getCapacity());
        listWagon.add("Capacidad de pasajeros: " + wagon.getPassengerCapacity());
        listWagon.add("Capacidad de maletas: " + wagon.getLuggageCapacity());
        listWagon.add("ID del tren al que pertenece: " + wagon.getTrain().getTrainId());
    }

    public void printWagonDetails() {
        System.out.println("Lista de detalles del vagon:");
        for (int i = 0; i < listWagon.size(); i++) {
            System.out.println(listWagon.get(i));
        }
    }
}

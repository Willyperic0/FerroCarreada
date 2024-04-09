package view;

import model.WagonModel;
import willy.linkedlist.singly.LinkedList;

public class WagonView {
    private LinkedList listWagon = new LinkedList<>();
    public void WagonList(WagonModel wagon) {
        listWagon.add("ID: " + wagon.getIdWagon()+
        "\n"+"Tipo de vagon: " + wagon.getType()+
        "\n"+"Capacidad: " + wagon.getCapacity()+
        "\n"+"Capacidad de pasajeros: " + wagon.getPassengerCapacity()+
        "\n"+"Capacidad de maletas: " + wagon.getLuggageCapacity()+
        "\n"+"ID del tren al que pertenece: " + wagon.getTrainId());
    }

    public void printWagonDetails() {
        System.out.println("Lista de vagones:");
        for (int i = 0; i < listWagon.size(); i++) {
            System.out.println(listWagon.get(i));
        }
    }
}

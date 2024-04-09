package view;

import model.TrainModel;
import willy.linkedlist.singly.LinkedList;
 

public class TrainView {
    private LinkedList listTrain = new LinkedList<>();
    public void TrainList(TrainModel train) {
        listTrain.add("ID: "+train.getTrainId()+
        "\n"+"Nombre: " + train.getName()+
        "\n"+"Identificador: " + train.getIdentifier()+
        "\n"+"Capacidad de carga: " + train.getCapacityLoad()+
        "\n"+"Millas: " + train.getMileage());
    }
    public void printTrainDetails() {
        System.out.println("Lista de trenes:");
        for (int i = 0; i < listTrain.size(); i++) {
            System.out.println(listTrain.get(i));
        }
    }
    
}
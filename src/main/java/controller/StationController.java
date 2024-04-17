package controller;

import java.util.*;

import model.StationModel;
import willy.linkedlist.doubly.LinkedList;


public class StationController{

private int numVertices;
private LinkedList<LinkedList<StationModel>> matrizAdyacencia;

public StationController(int numVertices) {
    this.numVertices = numVertices;
    this.matrizAdyacencia = new ArrayList<>(numVertices); // Especificamos el tipo de elementos
    for (int i = 0; i < numVertices; i++) {
        matrizAdyacencia.add(new ArrayList<StationModel>());
    }
    
}

public void agregarArista(int origen, StationModel arco) {
    matrizAdyacencia.get(origen).add(arco);
}

public void imprimirMatrizAdyacencia() {
    if (matrizAdyacencia == null) {
        System.out.println("La matriz de adyacencia no ha sido inicializada.");
        return;
    }
    for (int i = 0; i < numVertices; i++) {
        System.out.print("Vértice " + i + ": ");
        for (StationModel arco : matrizAdyacencia.get(i)) {
            System.out.print("(" + arco.getDestino() + ", " + arco.getDistancia() + ") ");
        }
        System.out.println();
    }
}

}
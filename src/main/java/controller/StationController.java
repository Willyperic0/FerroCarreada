package controller;

import java.util.*;

import model.StationModel;
import willy.linkedlist.doubly.LinkedList;

public class StationController {

    private int numVertices;
    private LinkedList<LinkedList<StationModel>> matrizAdyacencia;

    public StationController(int numVertices) {
        this.numVertices = numVertices;
        this.matrizAdyacencia = new LinkedList<>();
        for (int i = 0; i < numVertices; i++) {
            LinkedList<StationModel> nuevaLista = new LinkedList<>();
            matrizAdyacencia.add(nuevaLista); 
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
            LinkedList<StationModel> listaAdyacente = matrizAdyacencia.get(i);
            for (int j = 0; j < listaAdyacente.size(); j++) {
                StationModel arco = listaAdyacente.get(j);
                System.out.print("(" + arco.getDestino() + ", " + arco.getDistancia() + ") ");
            }
            System.out.println();
        }
    }
}
package model;

import willy.linkedlist.doubly.LinkedList;

public class RouteModel {
    private int numVertices;
    private LinkedList<LinkedList<Arista>> listaAdyacencia;

    public RouteModel(int numVertices) {
        this.numVertices = numVertices;
        listaAdyacencia = new LinkedList<>(); // Especificar el tipo genérico
        for (int i = 0; i < numVertices; i++) {
            listaAdyacencia.add(new LinkedList<Arista>()); // Especificar el tipo genérico
        }
    }

    public void agregarArista(int origen, int destino, double distancia) {
        listaAdyacencia.get(origen).add(new Arista(destino, distancia));
        listaAdyacencia.get(destino).add(new Arista(origen, distancia)); // Grafo no dirigido
    }

    public LinkedList<Arista> obtenerAristas(int vertice) {
        return listaAdyacencia.get(vertice);
    }
    public int obtenerNumeroVertices() {
        return numVertices;
    }
    

    // Clase interna para representar una arista
    public static class Arista {
        private int destino;
        private double distancia;

        public Arista(int destino, double distancia) {
            this.destino = destino;
            this.distancia = distancia;
        }

        public int getDestino() {
            return destino;
        }

        public double getDistancia() {
            return distancia;
        }
    }

}

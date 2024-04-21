package controller;


import model.RouteModel;
import view.RouteView;
import willy.linkedlist.doubly.LinkedList;


public class StationController{
    
    private RouteModel modelo;
    private RouteView vista;

    public StationController(RouteModel modelo, RouteView vista) {
        
        this.modelo = modelo;
        this.vista = vista;
    }

    // Método para agregar una conexión entre dos ciudades con su respectiva distancia
    public void agregarConexion(int ciudadOrigen, int ciudadDestino, double distancia) {
        modelo.agregarArista(ciudadOrigen, ciudadDestino, distancia);
    }

    // Método para obtener la distancia entre dos ciudades
    public double obtenerDistancia(int ciudadOrigen, int ciudadDestino) {
        LinkedList<RouteModel.Arista> aristas = modelo.obtenerAristas(ciudadOrigen);
        for (int i = 0; i < aristas.size(); i++) {
            RouteModel.Arista arista = aristas.get(i);
            if (arista.getDestino() == ciudadDestino) {
                return arista.getDistancia();
            }
        }
        // Si no se encuentra una conexión directa entre las ciudades, devolver un valor negativo o infinito
        return -1; // o Double.POSITIVE_INFINITY;
    }
}
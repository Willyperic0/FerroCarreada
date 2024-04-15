package model;

import willy.linkedlist.doubly.LinkedList;

public class RouteModel {
    //PENDIENTE MODIFICAR RUTAS
    //Graffo
    private LinkedList<StationModel> estaciones;

    public RouteModel() {
        this.estaciones = new LinkedList<>();
    }

    public void agregarEstacion(StationModel estacion) {
        estaciones.add(estacion);
    }

    public LinkedList<StationModel> getEstaciones() {
        return estaciones;
    }
}

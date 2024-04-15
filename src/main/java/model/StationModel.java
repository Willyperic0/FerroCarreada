package model;

import java.util.HashMap;
import java.util.Map;

public class StationModel {
    //PENDIENTE MODIFICAR O IMPLEMENTAR ESTACIONES
    private String name;
    private Map<StationModel, Double> neighbors;

    public StationModel(String name) {
        this.name = name;
        this.neighbors = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addNeighbor(StationModel neighbor, double distance) {
        neighbors.put(neighbor, distance);
    }

    public Map<StationModel, Double> getNeighbors() {
        return neighbors;
    }
}

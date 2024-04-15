package controller;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.StationModel;

public class RouteController {
    private Map<StationModel, Map<StationModel, Double>> graph;

    public RouteController(String filePath) {
        this.graph = new HashMap<>();
        loadGraphFromFile(filePath);
    }

    private void loadGraphFromFile(String filePath) {
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] elements = line.split(", ");
                if (elements.length == 3) {
                    String station1Name = elements[0];
                    String station2Name = elements[1];
                    double distance = Double.parseDouble(elements[2]);

                    StationModel station1 = getOrCreateStation(station1Name);
                    StationModel station2 = getOrCreateStation(station2Name);

                    station1.addNeighbor(station2, distance);
                    station2.addNeighbor(station1, distance);
                } else {
                    System.err.println("Incorrect line format: " + line);
                }
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private StationModel getOrCreateStation(String stationName) {
        for (StationModel station : graph.keySet()) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        StationModel newStation = new StationModel(stationName);
        graph.put(newStation, new HashMap<>());
        return newStation;
    }

    public void traverseStationsWithDistance() {
        Set<StationModel> visited = new HashSet<>();
        double totalDistance = 0.0;
        for (StationModel station : graph.keySet()) {
            if (!visited.contains(station)) {
                totalDistance += dfsWithDistance(visited, station, 0.0);
            }
        }
        System.out.println("Total distance: " + totalDistance + " km");
    }

    private double dfsWithDistance(Set<StationModel> visited, StationModel station, double totalDistance) {
        visited.add(station);
        System.out.println("Visiting station: " + station.getName());
        Map<StationModel, Double> neighbors = graph.get(station);
        if (neighbors != null) {
            for (Map.Entry<StationModel, Double> entry : neighbors.entrySet()) {
                StationModel neighbor = entry.getKey();
                if (!visited.contains(neighbor)) {
                    double distance = entry.getValue();
                    totalDistance += distance;
                    totalDistance = dfsWithDistance(visited, neighbor, totalDistance);
                }
            }
        }
        return totalDistance;
    }
}
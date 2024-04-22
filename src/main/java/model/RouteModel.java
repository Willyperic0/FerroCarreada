package model;

import java.io.Serializable;


public class RouteModel implements Serializable {
    private String routeName;
    private TrainModel trainModel;
    private String waypoints;
    private int distance;

    public RouteModel(String routeName, TrainModel trainModel, String waypoints, int distance) {
        this.routeName = routeName;
        this.trainModel = trainModel;
        this.waypoints = waypoints;
        this.distance = distance;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public TrainModel getTrainModel() {
        return trainModel;
    }

    public void setTrainModel(TrainModel trainModel) {
        this.trainModel = trainModel;
    }

    public String getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(String waypoints) {
        this.waypoints = waypoints;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getTrainIdentifier() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTrainIdentifier'");
    }
}

package model;

public class RouteModel {
    //Graffo
    TrainModel train = new TrainModel(0, null, null, 0, 0);
    StationModel stationOrigin = new StationModel(null, 0);
    StationModel stationDeStation = new StationModel(null, 0);


    public TrainModel getTrain() {
        return train;
    }

    

    public void setTrain(TrainModel train) {
        this.train = train;
    }

    public StationModel getStationOrigin() {
        return stationOrigin;
    }

    public void setStationOrigin(StationModel stationOrigin) {
        this.stationOrigin = stationOrigin;
    }
    
    public StationModel getStationDeStation() {
        return stationDeStation;
    }
    
    public void setStationDeStation(StationModel stationDeStation) {
        this.stationDeStation = stationDeStation;
    }
    
    @Override
    public String toString() {
        return "Route{" + "train=" + train + ", stationOrigin=" + stationOrigin + ", stationDeStation=" + stationDeStation + '}';
    }

    
}

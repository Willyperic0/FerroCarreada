package model;

public class TrainModel {
    public int trainId;
    public String name;
    public String identifier;
    public int capacityLoad;
    public int mileage;

    //LinkedList<Wagon> wagon = new LinkedList<>();

    public TrainModel(int trainId, String name, String identifier, int capacityLoad, int mileage) {   
        this.trainId = trainId;
        this.name = name;
        this.identifier = identifier;
        this.capacityLoad = capacityLoad;
        this.mileage = mileage;
    }


    
    public int getTrainId() {
        return this.trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getCapacityLoad() {
        return this.capacityLoad;
    }

    public void setCapacityLoad(int capacityLoad) {
        this.capacityLoad = capacityLoad;
    }

    public double getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    
}

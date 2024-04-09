package model;

public class WagonModel {
    private int idWagon;
    private String type;
    private int capacity;
    private int passengerCapacity; // Capacidad de pasajeros
    private int luggageCapacity; // Capacidad de equipaje
    private String trainId;
    private String identifier;

    public WagonModel(int idWagon, String type, int capacity,String trainId, String identifier) {
        this.idWagon = idWagon;
        this.type = type;
        this.capacity = capacity;
        this.trainId = trainId;
        this.identifier = identifier;
        initializeCapacities();
    }
    public WagonModel() {
    }

    private void initializeCapacities() {
        if (type.equals("Passenger")) {
            // Capacidades de pasajeros y equipaje para vagones de pasajeros
            passengerCapacity = 40; // Incluyendo 2 pilotos y 4 personal de abordo
            luggageCapacity = 160; // Dos maletas de 80 Kilogramos cada una
        } else if (type.equals("Cargo")) {
            // Capacidades de carga para vagones de carga
            passengerCapacity = 0;
            luggageCapacity = capacity; // Capacidad de carga igual a la capacidad total del vagón
        }
    }

    public int getIdWagon() {
        return idWagon;
    }

    public void setIdWagon(int idWagon) {
        this.idWagon = idWagon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getLuggageCapacity() {
        return luggageCapacity;
    }

    public String getTrainId(){
        return trainId;
    }

    public void settrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}

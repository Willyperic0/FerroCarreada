package model;

import java.io.Serializable;

import java.io.Serializable;

public class TrainModel implements Serializable {
    public String name;
    public String identifier;
    public int capacityLoad;
    public int mileage;
    public int vipVagons; // Número de vagones VIP
    public int executiveVagons; // Número de vagones Ejecutivo
    public int standardVagons; // Número de vagones Estándar
    public int cargo; // Cantidad de carga
    public int passenger; // Cantidad de pasajeros

    public TrainModel(String name, String identifier, int capacityLoad, int mileage) {
        this.name = name;
        this.identifier = identifier;
        this.capacityLoad = capacityLoad;
        this.mileage = mileage;
        this.vipVagons = 0; // Inicializamos en 0 por defecto
        this.executiveVagons = 0;
        this.standardVagons = 0;
        this.cargo = 0; // Inicializamos en 0 por defecto
        this.passenger = 0; // Inicializamos en 0 por defecto
    }

    public TrainModel() {
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

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }
    public void setCargo(int cargo) {
        this.cargo = cargo;
    }
    public int getPassenger() {
        return this.passenger;
    }
    public int getCargo() {
        return this.cargo;
    }
    public void setvipVagons(int vipVagons) {   this.vipVagons = vipVagons; }
    public void setExecutiveVagons(int executiveVagons) { this.executiveVagons = executiveVagons; }
    public void setStandardVagons(int standardVagons) { this.standardVagons = standardVagons; }
    
    public int getVipVagons() {
        return this.vipVagons;
    }
    
    public int getExecutiveVagons() {
        return this.executiveVagons;
    }
    
    public int getStandardVagons() {
        return this.standardVagons;
    }
    
    public void calculateAndUpdatePassengerAndCargo() {
        // Recalcular la cantidad de carga y pasajeros
        int cargo = calculateCargo(capacityLoad);
        int passenger = calculatePassenger(capacityLoad, cargo);
        
        // Establecer la cantidad de carga y pasajeros en el objeto TrainModel
        setCargo(cargo);
        setPassenger(passenger);
    }
    

   // Método para calcular los tickets VIP
public int calculateVIP(int passengerCount) {
    return passengerCount * 4; 
}

// Método para calcular los tickets Estándar
public int calculateStandard(int passengerCount) {
    return passengerCount * 22; 
}

// Método para calcular los tickets Ejecutivo
public int calculateExecutive(int passengerCount) {
    return passengerCount * 8; 
}
    
    // Método para calcular la cantidad de carga
    public int calculateCargo(int capacityLoad) {
        return capacityLoad / 3;
    }

    // Método para calcular la cantidad de pasajeros
    public int calculatePassenger(int capacityLoad, int cargo) {
        return capacityLoad - cargo;
    }
}

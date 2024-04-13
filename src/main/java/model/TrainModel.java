package model;

import java.io.Serializable;

public class TrainModel implements Serializable { // Define la clase TrainModel que implementa la interfaz Serializable
    // Variables de instancia para los atributos de un tren
    public String name; // Nombre del tren
    public String identifier; // Identificador del tren
    public int capacityLoad; // Capacidad de carga del tren
    public int mileage; // Kilometraje del tren

    // Constructor con parámetros para inicializar los atributos de un tren
    public TrainModel(String name, String identifier, int capacityLoad, int mileage) {   // Este metodo se esta usando en App
        this.name = name; // Asigna el nombre del tren
        this.identifier = identifier; // Asigna el identificador del tren
        this.capacityLoad = capacityLoad; // Asigna la capacidad de carga del tren
        this.mileage = mileage; // Asigna el kilometraje del tren
    }

    // Constructor sin parámetros (por defecto)
    public TrainModel() { // Este metodo se esta usando en App
    }

    // Método para obtener el nombre del tren
    public String getName() {
        return this.name; // Retorna el nombre del tren
    }

    // Método para establecer el nombre del tren
    public void setName(String name) {
        this.name = name; // Asigna el nombre del tren
    }

    // Método para obtener el identificador del tren
    public String getIdentifier() {
        return this.identifier; // Retorna el identificador del tren
    }

    // Método para establecer el identificador del tren
    public void setIdentifier(String identifier) {
        this.identifier = identifier; // Asigna el identificador del tren
    }

    // Método para obtener la capacidad de carga del tren
    public int getCapacityLoad() {
        return this.capacityLoad; // Retorna la capacidad de carga del tren
    }

    // Método para establecer la capacidad de carga del tren
    public void setCapacityLoad(int capacityLoad) {
        this.capacityLoad = capacityLoad; // Asigna la capacidad de carga del tren
    }

    // Método para obtener el kilometraje del tren
    public int getMileage() {
        return this.mileage; // Retorna el kilometraje del tren
    }

    // Método para establecer el kilometraje del tren
    public void setMileage(int mileage) {
        this.mileage = mileage; // Asigna el kilometraje del tren
    }
}
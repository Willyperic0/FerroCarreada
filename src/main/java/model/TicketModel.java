package model;

import java.io.Serializable;

public class TicketModel implements Serializable {
    private String registrationId;
    private String purchaseDateTime;
    private String departureDateTime;
    private String arrivalDateTime;
    private PassengerModel passenger;
    private TrainModel train;
    private double ticketValue;
    private String category;

    public TicketModel(String registrationId, String purchaseDateTime, String departureDateTime, String arrivalDateTime, PassengerModel passenger, TrainModel train, double ticketValue, String category) {
        this.registrationId = registrationId;
        this.purchaseDateTime = purchaseDateTime;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.passenger = passenger;
        this.train = train;
        this.ticketValue = ticketValue;
        this.category = category;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(String purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public PassengerModel getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerModel passenger) {
        this.passenger = passenger;
    }

    public TrainModel getTrain() {
        return train;
    }

    public void setTrain(TrainModel train) {
        this.train = train;
    }

    public double getTicketValue() {
        return ticketValue;
    }

    public void setTicketValue(double ticketValue) {
        this.ticketValue = ticketValue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Override
public String toString() {
    return "Ticket ID: " + registrationId +
           "\nFecha de compra: " + purchaseDateTime +
           "\nFecha de salida: " + departureDateTime +
           "\nFecha de llegada: " + arrivalDateTime +
           "\nPasajero: " + passenger.getName() + " " + passenger.getLastName() +
           "\nTren: " + train.getIdentifier() +
           "\nValor del ticket: " + ticketValue +
           "\nCategoría: " + category;
}

}
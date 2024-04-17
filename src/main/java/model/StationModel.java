package model;

public class StationModel {
    private String destino;
    private Double distancia;
    private StationModel next;
    
    public StationModel(String destino) {
        this.destino = destino;
        this.next = null;
    }

    public StationModel(String destino, Double distancia) {
        this.destino = destino;
        this.distancia = distancia;
        this.next = null;
    }

    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public Double getDistancia() {
        return distancia;
    }
    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }
    public StationModel getNext() {
        return next;
    }
    public void setNext(StationModel next) {
        this.next = next;
    }     
}

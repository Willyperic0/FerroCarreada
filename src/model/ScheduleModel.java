package model;

import java.time.LocalDateTime;

public class ScheduleModel {
    private LocalDateTime entrada;
    private LocalDateTime salida;

    public ScheduleModel(LocalDateTime entrada, LocalDateTime salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    // Getters y setters para entrada y salida
    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }
}
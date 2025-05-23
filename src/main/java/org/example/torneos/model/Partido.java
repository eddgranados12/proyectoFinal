package org.example.torneos.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Partido {
    private int id_partido;
    private int id_torneo;
    private LocalDate fecha;
    private LocalTime hora;
    private String lugar;
    private String status;
    private int id_equipo_local;
    private int id_equipo_visitante;

    public Partido() {
    }

    public int getId_partido() {
        return id_partido;
    }

    public void setId_partido(int id_partido) {
        this.id_partido = id_partido;
    }

    public int getId_torneo() {
        return id_torneo;
    }

    public void setId_torneo(int id_torneo) {
        this.id_torneo = id_torneo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_equipo_local() {
        return id_equipo_local;
    }

    public void setId_equipo_local(int id_equipo_local) {
        this.id_equipo_local = id_equipo_local;
    }

    public int getId_equipo_visitante() {
        return id_equipo_visitante;
    }

    public void setId_equipo_visitante(int id_equipo_visitante) {
        this.id_equipo_visitante = id_equipo_visitante;
    }
}

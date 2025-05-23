package org.example.torneos.model;

import java.time.LocalDate;

public class Torneo {
    private int id_torneo;
    private String nombre;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String categoria;

    public Torneo() {
        this.fecha_inicio = LocalDate.now();
        this.fecha_fin = LocalDate.now().plusMonths(1);
    }

    public int getId_torneo() {
        return id_torneo;
    }

    public void setId_torneo(int id_torneo) {
        this.id_torneo = id_torneo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nombre + " (" + categoria + ")";
    }
}

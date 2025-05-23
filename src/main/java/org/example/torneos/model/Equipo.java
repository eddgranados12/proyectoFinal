package org.example.torneos.model;

import java.time.LocalDate;

public class Equipo {
    private int id_equipo;
    private String nombre;
    private String categoria;
    private LocalDate fecha_creacion;
    private String status;

    public Equipo() {
        this.fecha_creacion = LocalDate.now();
        this.status = "Activo";
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nombre + " (" + categoria + ")";
    }
}

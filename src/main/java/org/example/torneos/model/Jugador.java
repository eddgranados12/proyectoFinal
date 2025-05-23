package org.example.torneos.model;

public class Jugador {
    private int id_jugador;
    private String nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String nacimiento;
    private String posicion;
    private String numero_camiseta;
    private int id_equipo;

    public Jugador() {

    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getNumero_camiseta() {
        return numero_camiseta;
    }

    public void setNumero_camiseta(String numero_camiseta) {
        this.numero_camiseta = numero_camiseta;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombreCompleto() {
        return nombre + " " + primer_apellido +
                (segundo_apellido != null ? " " + segundo_apellido : "");
    }
}

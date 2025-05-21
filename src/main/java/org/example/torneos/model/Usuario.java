package org.example.torneos.model;

public class Usuario {
    private Integer idUsuario;
    private String nombreUsuario;
    private String rol;
    private String contrasena;

    // Constructor
    public Usuario() {}

    // Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Método para verificar si es admin
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(this.rol);
    }
}

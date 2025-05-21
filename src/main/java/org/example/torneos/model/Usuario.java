package org.example.torneos.model;

public class Usuario {
    private Integer id_usuario;
    private String usuario;
    private String rol;
    private String contrasena;

    // Constructor
    public Usuario() {}

    // Getters y Setters


    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

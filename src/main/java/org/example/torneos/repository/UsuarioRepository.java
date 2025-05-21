package org.example.torneos.repository;


import org.example.torneos.model.Usuario;
import org.example.torneos.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {

    public Usuario findByCredentials(String username, String password) {
        String sql = "SELECT id_usuario, usuario, rol, contrasena FROM user WHERE usuario = ? AND contrasena = ?";

        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUsuario(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por credenciales: " + e.getMessage());
        }
        return null;
    }

    // Resto de métodos manteniendo la misma estructura pero usando MySQL
    // ...


    // Método auxiliar para mapear ResultSet a Usuario
    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(rs.getInt("id_usuario"));
        usuario.setUsuario(rs.getString("usuario"));
        usuario.setRol(rs.getString("rol"));
        usuario.setContrasena(rs.getString("contrasena"));
        return usuario;
    }
}






package org.example.torneos.repository;


import org.example.torneos.model.Jugador;
import org.example.torneos.BD.MySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorRepository {
    public boolean save(Jugador jugador) {
        String sql = "INSERT INTO jugador (nombre, primer_apellido, segundo_apellido, " +
                "nacimiento, posicion, numero_camiseta, id_equipo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, jugador.getNombre());
            stmt.setString(2, jugador.getPrimer_apellido());
            stmt.setString(3, jugador.getSegundo_apellido());
            stmt.setString(4, jugador.getNacimiento());
            stmt.setString(5, jugador.getPosicion());
            stmt.setString(6, jugador.getNumero_camiseta());
            stmt.setInt(7, jugador.getId_equipo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        jugador.setId_jugador(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Jugador> findByEquipo(int idEquipo) {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugador WHERE id_equipo = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEquipo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Jugador j = new Jugador();
                j.setId_jugador(rs.getInt("id_jugador"));
                j.setNombre(rs.getString("nombre"));
                j.setPrimer_apellido(rs.getString("primer_apellido"));
                j.setSegundo_apellido(rs.getString("segundo_apellido"));
                j.setNacimiento(rs.getString("nacimiento"));
                j.setPosicion(rs.getString("posicion"));
                j.setNumero_camiseta(rs.getString("numero_camiseta"));
                j.setId_equipo(rs.getInt("id_equipo"));

                jugadores.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }
}

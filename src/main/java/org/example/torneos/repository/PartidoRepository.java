package org.example.torneos.repository;

import org.example.torneos.model.Partido;
import org.example.torneos.BD.MySQLConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PartidoRepository {
    public boolean save(Partido partido) {
        String sql = "INSERT INTO partido (id_torneo, fecha, hora, lugar, status, " +
                "id_equipo_local, id_equipo_visitante) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, partido.getId_torneo());
            stmt.setDate(2, Date.valueOf(partido.getFecha()));
            stmt.setTime(3, Time.valueOf(partido.getHora()));
            stmt.setString(4, partido.getLugar());
            stmt.setString(5, partido.getStatus());
            stmt.setInt(6, partido.getId_equipo_local());
            stmt.setInt(7, partido.getId_equipo_visitante());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        partido.setId_partido(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Partido> findAll() {
        List<Partido> partidos = new ArrayList<>();
        String sql = "SELECT * FROM partido";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Partido p = new Partido();
                p.setId_partido(rs.getInt("id_partido"));
                p.setId_torneo(rs.getInt("id_torneo"));
                p.setFecha(rs.getDate("fecha").toLocalDate());
                p.setHora(rs.getTime("hora").toLocalTime());
                p.setLugar(rs.getString("lugar"));
                p.setStatus(rs.getString("status"));
                p.setId_equipo_local(rs.getInt("id_equipo_local"));
                p.setId_equipo_visitante(rs.getInt("id_equipo_visitante"));

                partidos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partidos;
    }

    // Método adicional para obtener partidos por torneo
    public List<Partido> findByTorneo(int idTorneo) {
        List<Partido> partidos = new ArrayList<>();
        String sql = "SELECT * FROM partido WHERE id_torneo = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTorneo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Partido p = new Partido();
                // ... (igual que en findAll) ...
                partidos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partidos;
    }
}

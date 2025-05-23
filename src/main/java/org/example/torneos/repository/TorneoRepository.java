package org.example.torneos.repository;

import org.example.torneos.model.Torneo;
import org.example.torneos.BD.MySQLConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TorneoRepository {
    public boolean save(Torneo torneo) {
        String sql = "INSERT INTO torneo (nombre, fecha_inicio, fecha_fin, categoria) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, torneo.getNombre());
            stmt.setDate(2, Date.valueOf(torneo.getFecha_inicio()));
            stmt.setDate(3, Date.valueOf(torneo.getFecha_fin()));
            stmt.setString(4, torneo.getCategoria());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        torneo.setId_torneo(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Torneo> findAll() {
        List<Torneo> torneos = new ArrayList<>();
        String sql = "SELECT * FROM torneo";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Torneo t = new Torneo();
                t.setId_torneo(rs.getInt("id_torneo"));
                t.setNombre(rs.getString("nombre"));
                t.setFecha_inicio(rs.getDate("fecha_inicio").toLocalDate());
                t.setFecha_fin(rs.getDate("fecha_fin").toLocalDate());
                t.setCategoria(rs.getString("categoria"));

                torneos.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return torneos;
    }

    public Torneo findById(int id) {
        String sql = "SELECT * FROM torneo WHERE id_torneo = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Torneo t = new Torneo();
                t.setId_torneo(rs.getInt("id_torneo"));
                t.setNombre(rs.getString("nombre"));
                t.setFecha_inicio(rs.getDate("fecha_inicio").toLocalDate());
                t.setFecha_fin(rs.getDate("fecha_fin").toLocalDate());
                t.setCategoria(rs.getString("categoria"));
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

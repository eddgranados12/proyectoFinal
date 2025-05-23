package org.example.torneos.repository;

import org.example.torneos.BD.MySQLConnection;
import org.example.torneos.model.Equipo;
import org.example.torneos.model.Partido;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EquipoRepository {
    public boolean save(Equipo equipo) {
        String sql = "INSERT INTO equipo (nombre, categoria, fecha_creacion, status) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getCategoria());
            stmt.setDate(3, Date.valueOf(equipo.getFecha_creacion()));
            stmt.setString(4, equipo.getStatus());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        equipo.setId_equipo(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    public List<Equipo> findAll() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipo";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipo e = new Equipo();
                e.setId_equipo(rs.getInt("id_equipo"));
                e.setNombre(rs.getString("nombre"));
                e.setCategoria(rs.getString("categoria"));
                e.setFecha_creacion(rs.getDate("fecha_creacion").toLocalDate());
                e.setStatus(rs.getString("status"));

                equipos.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

     */

    public List<Equipo> findAll() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipo";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setId_equipo(rs.getInt("id_equipo"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setCategoria(rs.getString("categoria"));
                equipo.setFecha_creacion(rs.getDate("fecha_creacion").toLocalDate());
                equipo.setStatus(rs.getString("status"));

                equipos.add(equipo);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener equipos: " + e.getMessage());
            e.printStackTrace();
        }
        return equipos;
    }


    public Equipo findById(int id) {
        String sql = "SELECT * FROM equipo WHERE id_equipo = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Equipo e = new Equipo();
                e.setId_equipo(rs.getInt("id_equipo"));
                e.setNombre(rs.getString("nombre"));
                e.setCategoria(rs.getString("categoria"));
                e.setFecha_creacion(rs.getDate("fecha_creacion").toLocalDate());
                e.setStatus(rs.getString("status"));
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


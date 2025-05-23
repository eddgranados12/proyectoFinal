package org.example.torneos.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author niluxer
 */


public class MySQLConnection {
    private static Connection conn = null;
    private static String hostname = "localhost";
    private static String dbname = "torneos";
    private static String dbport = "3306";
    private static String dbuser = "root";
    private static String dbpass = "Ed2819d12.";

    public static synchronized Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Connect();
            }
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + hostname + ":" + dbport + "/" + dbname +
                    "?useSSL=false&serverTimezone=UTC&autoReconnect=true";
            conn = DriverManager.getConnection(url, dbuser, dbpass);

            // Configuración adicional para evitar que se cierre

            conn.setNetworkTimeout(Executors.newFixedThreadPool(1), 10000);

            System.out.println("Conexión establecida con MySQL");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, "Error cargando el driver JDBC", e);
        } catch (SQLException e) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, "Error de conexión con MySQL", e);
        }
    }

    public static void Disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
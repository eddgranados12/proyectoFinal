package org.example.torneos.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import  org.example.torneos.BD.MySQLConnection;

public class DatabaseUtil {
    private static DatabaseUtil instance;

    private DatabaseUtil() {
        // Inicialización de la conexión
        Connection conn = MySQLConnection.getConnection();
    }

    public static synchronized DatabaseUtil getInstance() {
        if (instance == null) {
            instance = new DatabaseUtil();
        }
        return instance;
    }

    public Connection getConnection() {
        return MySQLConnection.getConnection();
    }

    public void disconnect() {
        MySQLConnection.Disconnect();
    }

    public void initializeDatabase() {

    }
}
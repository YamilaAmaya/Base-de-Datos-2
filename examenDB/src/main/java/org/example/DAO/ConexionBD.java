package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Cambia estas credenciales según tu configuración
    private static final String URL = "jdbc:postgresql://localhost:5432/estudiantes";
    private static final String USUARIO = "postgres";
    private static final String CONTRASEÑA = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error al conectar a la base de datos");
        }
    }
}

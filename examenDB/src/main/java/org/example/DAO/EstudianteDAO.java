package org.example.DAO;

import org.example.Models.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstudianteDAO {
    private Connection connection;

    public EstudianteDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para agregar un estudiante a la base de datos
    public void agregarEstudiante(Estudiante estudiante) throws SQLException {
        String query = "INSERT INTO estudiante (nombre_completo, fecha_nacimiento, carrera) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, estudiante.getNombreCompleto());
            statement.setDate(2, estudiante.getFechaNacimiento());
            statement.setString(3, estudiante.getCarrera());
            statement.executeUpdate();
        }
    }

    // Método para obtener todos los estudiantes de la base de datos
    public List<Estudiante> obtenerTodosLosEstudiantes() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String query = "SELECT * FROM estudiante";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombreCompleto = resultSet.getString("nombre_completo");
                Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                String carrera = resultSet.getString("carrera");
                estudiantes.add(new Estudiante(id, nombreCompleto, fechaNacimiento, carrera));
            }
        }
        return estudiantes;
    }

    public Estudiante obtenerEstudiantePorId(int id) throws SQLException {
        String query = "SELECT * FROM estudiante WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombreCompleto = resultSet.getString("nombre_completo");
                    Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                    String carrera = resultSet.getString("carrera");
                    return new Estudiante(id, nombreCompleto, fechaNacimiento, carrera);
                }
            }
        }
        return null;
    }

    public void actualizarEstudiante(Estudiante estudiante) throws SQLException {
        String query = "UPDATE estudiante SET nombre_completo = ?, fecha_nacimiento = ?, carrera = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, estudiante.getNombreCompleto());
            statement.setDate(2, estudiante.getFechaNacimiento());
            statement.setString(3, estudiante.getCarrera());
            statement.setInt(4, estudiante.getId());
            statement.executeUpdate();
        }
    }

    public void eliminarEstudiante(int id) throws SQLException {
        String query = "DELETE FROM estudiante WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

}

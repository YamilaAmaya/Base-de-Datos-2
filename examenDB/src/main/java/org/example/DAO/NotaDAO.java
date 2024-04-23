package org.example.DAO;

import org.example.Models.Nota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {
    private Connection connection;

    public NotaDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarNota(Nota nota) throws SQLException {
        String query = "INSERT INTO notas (id_estudiante, codigo_materia, nota) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nota.getIdEstudiante());
            statement.setString(2, nota.getCodigoMateria());
            statement.setFloat(3, nota.getNota());
            statement.executeUpdate();
        }
    }

    public List<Nota> obtenerNotasPorEstudianteYMateria(int idEstudiante, String codigoMateria) throws SQLException {
        List<Nota> notas = new ArrayList<>();
        String query = "SELECT * FROM notas WHERE id_estudiante = ? AND codigo_materia = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idEstudiante);
            statement.setString(2, codigoMateria);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                float nota = resultSet.getFloat("nota");
                notas.add(new Nota(id, idEstudiante, codigoMateria, nota));
            }
        }
        return notas;
    }

    public List<Nota> obtenerNotasPorEstudiante(int idEstudiante) throws SQLException {
        List<Nota> notas = new ArrayList<>();
        String query = "SELECT * FROM notas WHERE id_estudiante = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String codigoMateria = resultSet.getString("codigo_materia");
                float nota = resultSet.getFloat("nota");
                notas.add(new Nota(id, idEstudiante, codigoMateria, nota));
            }
        }
        return notas;
    }


    public void actualizarNota(Nota nota) throws SQLException {
        String query = "UPDATE notas SET nota = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, nota.getNota());
            statement.setInt(2, nota.getId());
            statement.executeUpdate();
        }
    }

    public void eliminarNota(int idNota) throws SQLException {
        String query = "DELETE FROM notas WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idNota);
            statement.executeUpdate();
        }
    }
}

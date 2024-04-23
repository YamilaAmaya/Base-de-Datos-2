package org.example.DAO;

import org.example.Models.Materia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {
    private Connection connection;

    public MateriaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para agregar una materia a la base de datos
    public void agregarMateria(Materia materia) throws SQLException {
        String query = "INSERT INTO materias (codigo, nombre, creditos) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, materia.getCodigo());
            statement.setString(2, materia.getNombre());
            statement.setInt(3, materia.getCreditos());
            statement.executeUpdate();
        }
    }

    // Método para obtener todas las materias de la base de datos
    public List<Materia> obtenerTodasLasMaterias() throws SQLException {
        List<Materia> materias = new ArrayList<>();
        String query = "SELECT * FROM materias";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String codigo = resultSet.getString("codigo");
                String nombre = resultSet.getString("nombre");
                int creditos = resultSet.getInt("creditos");
                materias.add(new Materia(codigo, nombre, creditos));
            }
        }
        return materias;
    }

    // Otros métodos CRUD para actualizar y eliminar materias

    public void actualizarMateria(Materia materia) throws SQLException {
        String query = "UPDATE materias SET nombre = ?, creditos = ? WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, materia.getNombre());
            statement.setInt(2, materia.getCreditos());
            statement.setString(3, materia.getCodigo());
            statement.executeUpdate();
        }
    }

    public void eliminarMateria(String codigo) throws SQLException {
        String query = "DELETE FROM materias WHERE codigo = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codigo);
            statement.executeUpdate();
        }
    }
}

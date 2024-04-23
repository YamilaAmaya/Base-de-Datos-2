package org.example.Models;

public class Nota {
    private int id;
    private int idEstudiante;
    private String codigoMateria;
    private float nota;

    // Constructor
    public Nota(int id, int idEstudiante, String codigoMateria, float nota) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.codigoMateria = codigoMateria;
        this.nota = nota;
    }

    // Getters y Setters
    // ...

    // Método toString para representación textual de la nota
    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", idEstudiante=" + idEstudiante +
                ", codigoMateria='" + codigoMateria + '\'' +
                ", nota=" + nota +
                '}';
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public float getNota() {
        return nota;
    }

    public int getId() {
        return id;
    }
}

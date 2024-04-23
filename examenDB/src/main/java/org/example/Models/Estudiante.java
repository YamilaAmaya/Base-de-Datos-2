package org.example.Models;

import java.util.Date;

public class Estudiante {
    private int id;
    private String nombreCompleto;
    private Date fechaNacimiento;
    private String carrera;

    // Constructor
    public Estudiante(int id, String nombreCompleto, Date fechaNacimiento, String carrera) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.carrera = carrera;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public java.sql.Date getFechaNacimiento() {
        return new java.sql.Date(fechaNacimiento.getTime());
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    // Método toString para representación textual del estudiante
    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", carrera='" + carrera + '\'' +
                '}';
    }
}

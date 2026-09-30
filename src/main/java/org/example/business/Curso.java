package org.example.business;

public class Curso {
    private String codigo;
    private String nombre;
    private int creditos;

    // Constructor vacío (necesario para Gson)
    public Curso() {
    }

    // Constructor con parámetros
    public Curso(String codigo, String nombre, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
    }
}
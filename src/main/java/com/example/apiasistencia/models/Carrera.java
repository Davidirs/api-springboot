package com.example.apiasistencia.models;

import java.util.List;

public class Carrera {
    private String id;
    private String nombre;
    private List<String> estudiantes;

    // Constructor vacío necesario para la deserialización
    public Carrera() {
    }

    public Carrera(String id, String nombre, List<String> estudiantes) {
        this.id = id;
        this.nombre = nombre;
        this.estudiantes = estudiantes;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }       
public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}

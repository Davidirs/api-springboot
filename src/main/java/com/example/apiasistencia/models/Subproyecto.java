package com.example.apiasistencia.models;


public class Subproyecto {
        private String id;
        private String nombre;
        private String profesor;

        // Constructor vacío necesario para la deserialización
        public Subproyecto() {
        }

        public Subproyecto(String id,String nombre, String profesor) {
            this.id = id;
            this.nombre = nombre;
            this.profesor = profesor;
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

        public String getProfesor() {
            return profesor;
        }

        public void setProfesor(String profesor) {
            this.profesor = profesor;
        }
    }
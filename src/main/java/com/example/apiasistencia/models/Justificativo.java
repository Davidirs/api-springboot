package com.example.apiasistencia.models;


public class Justificativo {
        private String id;
        private String descripcion;
        private String profesor;
        private String fecha;

        // Constructor vacío necesario para la deserialización
        public Justificativo() {
        }

        public Justificativo(String id,String descripcion, String profesor,String fecha ) {
            this.id = id;
            this.descripcion = descripcion;
            this.profesor = profesor;
            this.fecha = fecha;
        }

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProfesor() {
            return profesor;
        }

        public void setProfesor(String profesor) {
            this.profesor = profesor;
        }
        public String getDescripcion() {
            return descripcion;
        }
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
        public String getFecha() {
            return fecha;
        }
        public void setFecha(String fecha) {
            this.fecha = fecha;
        }
    }
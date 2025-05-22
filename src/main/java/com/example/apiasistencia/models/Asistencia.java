package com.example.apiasistencia.models;

import java.util.List;

public class Asistencia {
        private String id;
        private String fecha;
        private List<String> estudiantes;
        private String subproyecto;
        private String profesor;
        private String descripcion;

        // Constructor vacío necesario para la deserialización
        public Asistencia() {
        }

        public Asistencia(String id,String fecha, List<String> estudiantes, String subproyecto, String profesor, String descripcion) {
            this.id = id;
            this.fecha = fecha;
            this.estudiantes = estudiantes;
            this.subproyecto = subproyecto;
            this.profesor = profesor;
            this.descripcion = descripcion;
        }

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public List<String> getEstudiantes() {
            return estudiantes;
        }

        public void setEstudiantes(List<String> estudiantes) {
            this.estudiantes = estudiantes;
        }

        public String getSubproyecto() {
            return subproyecto;
        }

        public void setSubproyecto(String subproyecto) {
            this.subproyecto = subproyecto;
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
    }
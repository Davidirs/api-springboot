package com.example.apiasistencia.models;


public class Estudiante {
        private String cedula;
        private String nombre;
        private String correo;
        private String imagen;
        private String carrera;
        private String semestre;
        private String telefono;

        // Constructor vacío necesario para la deserialización
        public Estudiante() {
        }

        public Estudiante(String cedula,String nombre, String correo, String imagen, String carrera, String semestre, String telefono) {
            this.cedula = cedula;
            this.nombre = nombre;
            this.correo = correo;
            this.imagen = imagen;
            this.carrera = carrera;
            this.semestre = semestre;
            this.telefono = telefono;
        }

        // Getters y Setters
        public String getcedula() {
            return cedula;
        }

        public void  setcedula(String cedula) {
            this.cedula = cedula;
        }
        public String getNombre() {
            return nombre;
        }

        public void  setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getcorreo() {
            return correo;
        }

        public void setcorreo(String correo) {
            this.correo = correo;
        }
        public String getImagen() {
            return imagen;
        }
        public void setImagen(String imagen) {
            this.imagen = imagen;
        }
        public String getCarrera() {
            return carrera;
        }
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }
        public String getSemestre() {
            return semestre;
        }
        public void setSemestre(String semestre) {
            this.semestre = semestre;
        }
        public String getTelefono() {
            return telefono;
        }
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
    }
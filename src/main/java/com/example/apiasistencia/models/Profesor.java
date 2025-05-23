package com.example.apiasistencia.models;


public class Profesor {
        private String id;
        private String nombre;
        private String correo;
        private String imagen;
        private String telefono;
        private boolean isAprobado;

        // Constructor vacío necesario para la deserialización
        public Profesor() {
        }

        public Profesor(String id,String nombre, String correo, String imagen, String telefono, boolean isAprobado) {
            this.id = id;
            this.nombre = nombre;
            this.correo = correo;
            this.imagen = imagen;
            this.telefono = telefono;
            this.isAprobado = isAprobado;
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

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
        public boolean isAprobado() {
            return isAprobado;
        }
    }
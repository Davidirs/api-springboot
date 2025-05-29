package com.example.apiasistencia.models;


public class Profesor {
        private String id;
        private String ci;
        private String nombre;
        private String correo;
        private String imagen;
        private String telefono;
        private String aprobado;

        // Constructor vacío necesario para la deserialización
        public Profesor() {
        }

        public Profesor(String id,String ci,String nombre, String correo, String imagen, String telefono, String aprobado) {
            this.id = id;
            this.ci = ci;
            this.nombre = nombre;
            this.correo = correo;
            this.imagen = imagen;
            this.telefono = telefono;
            this.aprobado = aprobado;
        }

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        public String getCi() {
            return ci;
        }


        public void setCi(String ci) {
            this.ci = ci;
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
        public String getAprobado() {
            return aprobado;
        }
        public void setAprobado(String aprobado) {
            this.aprobado = aprobado;
        }
    }
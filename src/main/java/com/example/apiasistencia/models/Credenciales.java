package com.example.apiasistencia.models;

public class Credenciales {
        private String usuario;
        private String contrasena;

        // Constructor vacío necesario para la deserialización
        public Credenciales() {
        }

        public Credenciales(
                String usuario, String contrasena) {
            this.usuario = usuario;
            this.contrasena = contrasena;
        }

        // Getters y Setters
        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }
    }
package com.example.apiasistencia.controller;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    public static class Credenciales {
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

    @PostMapping("/validarcredenciales")
    public ResponseEntity<?> validarCredenciales(@RequestBody Credenciales credenciales) {
        try {
            // Aquí deberías implementar la lógica para validar las credenciales
            // Por simplicidad, asumimos que las credenciales son válidas si el usuario
            // y la contraseña no están vacíos
            if (credenciales.getUsuario().isEmpty() || credenciales.getContrasena().isEmpty()) {
                return ResponseEntity.badRequest().body("Usuario o contraseña inválidos");
            }

            // Simulamos una validación exitosa
            Object[] validCredentials = { "usuario", "contrasena" };
            if (!Arrays.asList(validCredentials).contains(credenciales.getUsuario()) ||
                    !Arrays.asList(validCredentials).contains(credenciales.getContrasena())) {
                return ResponseEntity.badRequest().body("Usuario o contraseña inválidos");
            }
            HashMap<String, Object> profesor = new HashMap<String, Object>() {
                {
                    put("cedula", "24114415");
                    put("nombre", "Gabriel Vielma");
                    put("telefono", "04145021471");
                    put("correo", "gabrielvielma91@gmail.com");
                    put("imagen",
                            "https://lh3.googleusercontent.com/cm/AGPWSu9E4K66u1GRzKXEbgoqerRKCGDtMzMaNt50-8szNfgiZhmDJwptPK_Ta8_Om1jva7HOBw=s48-p");
                }
            };

            return ResponseEntity.ok(profesor);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }
}

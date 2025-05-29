package com.example.apiasistencia.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.apiasistencia.models.Credenciales;
import com.example.apiasistencia.models.Profesor;
import com.example.apiasistencia.services.AuthService;

@RestController
public class AuthController {

    @PostMapping("/crearusuario")
    public ResponseEntity<?> crearUsuario(@RequestBody Credenciales credenciales) {
        try {
            // Aquí deberías implementar la lógica para validar las credenciales
            // Por simplicidad, asumimos que las credenciales son válidas si el usuario
            // y la contraseña no están vacíos
            if (credenciales.getUsuario().isEmpty() || credenciales.getContrasena().isEmpty()) {
                return ResponseEntity.badRequest().body("Usuario o contraseña inválidos");
            }

            Profesor usuariocreado = AuthService.crearUsuario(credenciales);

            return ResponseEntity.ok(usuariocreado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }
    @PostMapping("/validartoken")
    public ResponseEntity<?> validarToken(@RequestBody String token) {
        try {
            AuthService.validarToken(token);
            return ResponseEntity.ok("usuariocreado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }
}

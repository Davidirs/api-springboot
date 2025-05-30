package com.example.apiasistencia.services;

import java.io.IOException;
import com.example.apiasistencia.models.Credenciales;
import com.example.apiasistencia.models.Profesor;
import com.example.apiasistencia.resources.Auth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;

public class AuthService {

    public static Profesor crearUsuario(Credenciales credenciales) {
        Profesor profesor = new Profesor();
        try {
            // Obtener instancia de Auth
            Auth auth = Auth.getInstance();

            // Crear un nuevo usuario
            UserRecord userRecord = auth.createUser(credenciales.getUsuario(), credenciales.getContrasena());
            System.out.println("Usuario creado: " + userRecord.getUid());
            profesor.setId(userRecord.getUid());
            profesor.setNombre(userRecord.getDisplayName());
            profesor.setCorreo(userRecord.getEmail());
            profesor.setImagen(userRecord.getPhotoUrl());
            profesor.setTelefono(userRecord.getPhoneNumber());

        } catch (FirebaseAuthException e) {
            System.err.println("Error de autenticación: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de configuración: " + e.getMessage());
        }

        return profesor;
    }

    public static Profesor validarToken(String token) {
        Profesor profesor = new Profesor();

        // Obtener instancia de Auth
        try {
            Auth auth = Auth.getInstance();
            // 1. Obtener token del cuerpo de la petición

            // 2. Verificar token en backend
            FirebaseToken decodedToken = auth.verifyLogin(token);
            System.out.println("Token verificado: " + decodedToken.getUid());

        } catch (FirebaseAuthException e) {
            System.err.println("Error de autenticación: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de configuración: " + e.getMessage());
        }

        return profesor;
    }
}
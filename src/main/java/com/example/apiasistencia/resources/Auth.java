package com.example.apiasistencia.resources;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.OidcProviderConfig;

import java.io.FileInputStream;
import java.io.IOException;

public class Auth {
    private FirebaseAuth auth;
    private static Auth instance;

    // Constructor privado para singleton
    private Auth() throws IOException {
        // Ruta al archivo de configuración descargado de Firebase Console
        FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);

        this.auth = FirebaseAuth.getInstance();
    }

    // Método para obtener la instancia singleton
    public static Auth getInstance() throws IOException {
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }

    // Método para crear usuario
    public UserRecord createUser(String email, String password) throws FirebaseAuthException {
        CreateRequest request = new CreateRequest()
                .setEmail(email)
                .setPassword(password);

        return auth.createUser(request);
    }

    // Método para verificar login
public FirebaseToken verifyLogin(String idToken) throws FirebaseAuthException {
    // Verifica el token ID que recibiste del cliente
    return FirebaseAuth.getInstance().verifyIdToken(idToken);
}

   
    public void getUser(String uid) {
        try {
            UserRecord userRecord = auth.getUser(uid);
            System.out.println("Usuario: " + userRecord.getEmail());
        } catch (FirebaseAuthException e) {
            System.out.println("Error al obtener usuario: " + e.getMessage());
        }
    }

    // Por email
    public void getUserByEmail(String email) {
        try {
            UserRecord userRecord = auth.getUserByEmail(email);
            System.out.println("Usuario: " + userRecord.getUid());
        } catch (FirebaseAuthException e) {
            System.out.println("Error al obtener usuario: " + e.getMessage());
        }
    }

    public void updateUser(String uid, String newEmail, String newPassword) {
        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(uid)
                .setEmail(newEmail)
                .setPassword(newPassword);

        try {
            UserRecord userRecord = auth.updateUser(request);
            System.out.println("Usuario actualizado: " + userRecord.getUid());
        } catch (FirebaseAuthException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    public void deleteUser(String uid) {
        try {
            auth.deleteUser(uid);
            System.out.println("Usuario eliminado");
        } catch (FirebaseAuthException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    public void updateProviderConfig() {
        // Configurar proveedor de Google
        OidcProviderConfig.CreateRequest googleRequest = new OidcProviderConfig.CreateRequest()
                .setProviderId("google.com")
                .setDisplayName("Google")
                .setEnabled(true)
                .setClientId("TU_CLIENT_ID_GOOGLE")
                .setIssuer("https://accounts.google.com");

        try {
            OidcProviderConfig googleConfig = auth.createOidcProviderConfig(googleRequest);
            System.out.println("Proveedor Google configurado" + googleConfig.getProviderId());
        } catch (FirebaseAuthException e) {
            System.out.println("Error al configurar Google: " + e.getMessage());
        }
    }

    public void sendEmailVerification(String uid) {
        try {
            String link = auth.generateEmailVerificationLink("user@example.com");
            // Envía este link por email al usuario
            System.out.println("Link de verificación: " + link);
        } catch (FirebaseAuthException e) {
            System.out.println("Error al generar link: " + e.getMessage());
        }
    }

    public void sendPasswordResetEmail(String email) {
        try {
            String link = auth.generatePasswordResetLink(email);
            // Envía este link por email al usuario
            System.out.println("Link de restablecimiento: " + link);
        } catch (FirebaseAuthException e) {
            System.out.println("Error al generar link: " + e.getMessage());
        }
    }

    public void listAllUsers() {
        // Listar usuarios por lotes de 1000
        ListUsersPage page;
        try {
            page = auth.listUsers(null);
            for (ExportedUserRecord user : page.iterateAll()) {
                System.out.println("Usuario: " + user.getUid() + ", Email: " + user.getEmail());
            }

            // Si hay más usuarios
            while (page.hasNextPage()) {
                page = page.getNextPage();
                for (ExportedUserRecord user : page.iterateAll()) {
                    System.out.println("Usuario: " + user.getUid() + ", Email: " + user.getEmail());
                }
            }
        } catch (FirebaseAuthException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
    }
}
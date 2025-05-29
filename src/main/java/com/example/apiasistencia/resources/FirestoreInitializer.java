package com.example.apiasistencia.resources;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.FirestoreOptions;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FirestoreInitializer {
    private static final String[] REQUIRED_ENV_VARS = {
            "FIREBASE_TYPE", "FIREBASE_PROJECT_ID", "FIREBASE_PRIVATE_KEY_ID",
            "FIREBASE_PRIVATE_KEY", "FIREBASE_CLIENT_EMAIL", "FIREBASE_CLIENT_ID",
            "FIREBASE_AUTH_URI", "FIREBASE_TOKEN_URI",
            "FIREBASE_AUTH_PROVIDER_CERT_URL", "FIREBASE_CLIENT_CERT_URL"
    };

    private static final String ENV_FILE_PATH = ".env"; // o "./config/.env" si está en esa ubicación
    boolean isProd = true;
     // Cambiar a true si es producción

    public Firestore initialize() {
        String credentialsJson;
        Firestore firestore;
        try {
            // 3. Construir credenciales
            if (isProd) {

                credentialsJson = buildCredentialsJsonFromEnv();
                GoogleCredentials credentials = GoogleCredentials.fromStream(
                        new ByteArrayInputStream(credentialsJson.getBytes(StandardCharsets.UTF_8)));

                FirestoreOptions options = FirestoreOptions.newBuilder()
                        .setCredentials(credentials)
                        .setProjectId(System.getenv("FIREBASE_PROJECT_ID"))
                        .build();

                firestore = options.getService();
            } else {
                // 1. Cargar configuración
                Dotenv dotenv = loadDotenv();
                System.out.println("✅ Variables de entorno cargadas correctamente");

                // 2. Validar variables
                validateEnvironmentVariables(dotenv);

                credentialsJson = buildCredentialsJson(dotenv);

                System.out.println("🔑 Credenciales JSON generadas correctamente");

                GoogleCredentials credentials = GoogleCredentials.fromStream(
                        new ByteArrayInputStream(credentialsJson.getBytes(StandardCharsets.UTF_8)));
                System.out.println("🔐 Credenciales de Google autenticadas");

                // 4. Configurar opciones de Firestore con timeout
                FirestoreOptions options = FirestoreOptions.newBuilder()
                        .setCredentials(credentials)
                        .setProjectId(dotenv.get("FIREBASE_PROJECT_ID"))
                        // .setConnectTimeout(30_000) // 30 segundos timeout de conexión
                        // .setReadTimeout(30_000) // 30 segundos timeout de lectura
                        .build();

                System.out.println("⚙️ Opciones de Firestore configuradas");

                // 5. Crear instancia
                firestore = options.getService();
                System.out.println("🚀 Firestore inicializado exitosamente");
            }
            return firestore;

        } catch (IOException e) {
            System.err.println("🔴 Error de E/S: " + e.getMessage());
            throw new FirestoreInitializationException("Error de comunicación con Firebase", e);
        } catch (FirestoreException e) {
            System.err.println("🔴 Error específico de Firestore: " + e.getMessage());
            throw new FirestoreInitializationException("Error al configurar Firestore", e);
        } catch (Exception e) {
            System.err.println("🔴 Error inesperado: " + e.getMessage());
            throw new FirestoreInitializationException("Error general al inicializar Firestore", e);
        }
    }

    private Dotenv loadDotenv() {

        try {
            // Obtener la ruta absoluta del archivo .env
            Path envPath = Paths.get(ENV_FILE_PATH).toAbsolutePath();
            System.out.println("Buscando archivo .env en: " + envPath);

            // Verificar existencia del archivo
            if (Files.exists(envPath)) {
                System.out.println("Archivo .env encontrado. Tamaño: " + Files.size(envPath) + " bytes");
            } else {
                System.out.println("Archivo .env NO encontrado en la ubicación especificada");
                throw new RuntimeException("Archivo .env no encontrado en: " + envPath);
            }

            // Configurar Dotenv considerando que puede estar en el directorio raíz
            Dotenv dotenv;
            if (envPath.getParent() != null) {
                // Si tiene directorio padre (no está en raíz)
                dotenv = Dotenv.configure()
                        .directory(envPath.getParent().toString())
                        .filename(envPath.getFileName().toString())
                        .load();
            } else {
                // Si está en el directorio raíz (getParent() == null)
                dotenv = Dotenv.configure()
                        .filename(envPath.getFileName().toString())
                        .load();
            }
            System.out.println("Variables cargadas correctamente:");
            // dotenv.entries().forEach(entry -> System.out.println(entry.getKey() + "=" +
            // entry.getValue()));
            return dotenv;
        } catch (Exception e) {
            System.err.println("Error durante la inicialización:");
            e.printStackTrace();
            throw new RuntimeException("Fallo en la inicialización de Firestore", e);
        }
        /*
         * try {
         * return Dotenv.configure()
         * .directory(".env")
         * .ignoreIfMissing()
         * .load();
         * } catch (Exception e) {
         * System.out.
         * println("No se pudo cargar archivo .env, usando variables de sistema");
         * return null;
         * }
         */
    }

    private void validateEnvironmentVariables(Dotenv dotenv) {
        for (String varName : REQUIRED_ENV_VARS) {
            // System.out.println("Validando variable de entorno: " + varName);
            if (getEnv(dotenv, varName) == null) {
                throw new MissingEnvironmentVariableException(
                        "Variable de entorno requerida no encontrada: " + varName);
            }
        }
    }

    private String buildCredentialsJson(Dotenv dotenv) {
        return String.format("""
                {
                  "type": "%s",
                  "project_id": "%s",
                  "private_key_id": "%s",
                  "private_key": "%s",
                  "client_email": "%s",
                  "client_id": "%s",
                  "auth_uri": "%s",
                  "token_uri": "%s",
                  "auth_provider_x509_cert_url": "%s",
                  "client_x509_cert_url": "%s",
                  "universe_domain": "googleapis.com"
                }
                """,
                getEnv(dotenv, "FIREBASE_TYPE"),
                getEnv(dotenv, "FIREBASE_PROJECT_ID"),
                getEnv(dotenv, "FIREBASE_PRIVATE_KEY_ID"),
                getEnv(dotenv, "FIREBASE_PRIVATE_KEY").replace("\n", "\\n").replace("\r", ""),
                getEnv(dotenv, "FIREBASE_CLIENT_EMAIL"),
                getEnv(dotenv, "FIREBASE_CLIENT_ID"),
                getEnv(dotenv, "FIREBASE_AUTH_URI"),
                getEnv(dotenv, "FIREBASE_TOKEN_URI"),
                getEnv(dotenv, "FIREBASE_AUTH_PROVIDER_CERT_URL"),
                getEnv(dotenv, "FIREBASE_CLIENT_CERT_URL"));
    }

    private String buildCredentialsJsonFromEnv() {
        // Para Render, las variables ya vienen con \n como literales
        String privateKey = System.getenv("FIREBASE_PRIVATE_KEY");

        return String.format("""
                {
                  "type": "%s",
                  "project_id": "%s",
                  "private_key_id": "%s",
                  "private_key": "%s",
                  "client_email": "%s",
                  "client_id": "%s",
                  "auth_uri": "%s",
                  "token_uri": "%s",
                  "auth_provider_x509_cert_url": "%s",
                  "client_x509_cert_url": "%s"
                }
                """,
                System.getenv("FIREBASE_TYPE"),
                System.getenv("FIREBASE_PROJECT_ID"),
                System.getenv("FIREBASE_PRIVATE_KEY_ID"),
                privateKey,
                System.getenv("FIREBASE_CLIENT_EMAIL"),
                System.getenv("FIREBASE_CLIENT_ID"),
                System.getenv("FIREBASE_AUTH_URI"),
                System.getenv("FIREBASE_TOKEN_URI"),
                System.getenv("FIREBASE_AUTH_PROVIDER_CERT_URL"),
                System.getenv("FIREBASE_CLIENT_CERT_URL"));
    }

    private String getEnv(Dotenv dotenv, String key) {
        // 1. Intentar desde Dotenv
        if (dotenv != null) {
            String value = dotenv.get(key);
            // System.out.println("Valor de variable de entorno desde Dotenv: " + value);
            if (value != null && !value.trim().isEmpty()) {
                return value;
            }
        }
        // System.out.println("Buscando variable de entorno: " + key);

        // 2. Intentar desde variables de sistema
        return System.getenv(key);
    }
}

// Excepciones personalizadas
class FirestoreInitializationException extends RuntimeException {
    public FirestoreInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}

class MissingEnvironmentVariableException extends RuntimeException {
    public MissingEnvironmentVariableException(String message) {
        super(message);
    }
}
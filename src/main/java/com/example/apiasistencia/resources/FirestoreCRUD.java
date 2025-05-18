package com.example.apiasistencia.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.lang.NonNull;
import org.springframework.scheduling.config.Task;

import com.example.apiasistencia.controller.AsistenciasController.Asistencia;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
/* import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions; */
/* 
import java.util.concurrent.ExecutionException; */
import com.google.cloud.firestore.Query.Direction;

public class FirestoreCRUD {
    private final Firestore db;

    public FirestoreCRUD() {
        // Cargar el archivo de credenciales
        try {
            // Ruta del archivo de configuración
            String path = "serviceAccountKey.json";
            // Cargar las credenciales desde el archivo
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(path));
            // Opción 1: Usar InputStream directamente
            FirestoreOptions options = FirestoreOptions.getDefaultInstance().toBuilder()
                    .setCredentials(credentials)
                    .build();
            // Opción 2: Usar la ruta del archivo de credenciales
            this.db = options.getService();

            System.out.println("Conexión a Firestore establecida");
        } catch (Exception e) {
            System.err.println("Error al conectar con Firestore: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // creamos una funcion para crear un nuevo estudiante en Firestore
    public static void crearEstudiante() {
        try {

            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();

            // Crear una referencia al documento
            DocumentReference docRef = firestoreCRUD.db
                    .collection("estudiantes")
                    .document("30740994");

            // Crear un mapa con los datos del estudiante
            Map<String, Object> estudiante = new HashMap<>();

            // Agregar los datos del estudiante
            estudiante.put("apellido", "guevara");
            estudiante.put("nombre", "anthony");
            estudiante.put("edad", "20");
            // Guardar el documento en Firestore
            ApiFuture<WriteResult> result = docRef.set(estudiante);

            System.out.println("Documento guardado con ID: " + result.get().getUpdateTime());
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error al guardar el documento: " + e.getMessage());
        }
    }

    public static void leerEstudiantes() {

        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            ApiFuture<QuerySnapshot> busqueda = firestoreCRUD.db.collection("estudiantes").get();
            QuerySnapshot querySnapshot = busqueda.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                System.out.println("Cedula: " + document.getId());
                System.out.println("nombre: " + document.getString("nombre"));
                System.out.println("apellido: " + document.getString("apellido"));
                System.out.println("edad: " + document.getString("edad"));
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error al obtener los estudiantes: " + e.getMessage());
        }
    }

    public static void EliminarEstudiante() {

        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            ApiFuture<WriteResult> result = firestoreCRUD.db.collection("estudiantes")
                    .document("24114415").delete();
            System.out.println("Documento eliminado con ID: " + result.get().getUpdateTime());
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error al eliminar el documento: " + e.getMessage());
        }
    }

    public static void actualizarEstudiante() {
        try {

            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();

            // Crear una referencia al documento
            DocumentReference docRef = firestoreCRUD.db
                    .collection("estudiantes")
                    .document("24114415");

            // Crear un mapa con los datos del estudiante
            Map<String, Object> estudiante = new HashMap<>();

            // Agregar los datos del estudiante
            estudiante.put("apellido", "AROCHA");
            estudiante.put("nombre", "RAFAEL");
            estudiante.put("edad", "22");
            // Guardar el documento en Firestore
            ApiFuture<WriteResult> result = docRef.set(estudiante);

            System.out.println("Documento guardado con ID: " + result.get().getUpdateTime());
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error al guardar el documento: " + e.getMessage());
        }
    }

    public static String leerUnEstudiante() {
        // creo un string para guardar la nombre y apellido
        String nombrecompleto = "";
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            ApiFuture<DocumentSnapshot> busqueda = firestoreCRUD.db.collection("estudiantes").document("30740994")
                    .get();
            DocumentSnapshot documentSnapshot = busqueda.get();
            System.out.println("Cedula: " + documentSnapshot.getId());
            // lleno la nombrecompleto con la nombre y apellido del estudiante
            nombrecompleto = documentSnapshot.getString("nombre") + " " + documentSnapshot.getString("apellido");
            System.out.println("nombre: " + documentSnapshot.getString("nombre"));
            System.out.println("apellido: " + documentSnapshot.getString("apellido"));
            System.out.println("edad: " + documentSnapshot.getString("edad"));
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error al obtener los estudiantes: " + e.getMessage());
        }
        // devuelvo la nombrecompleto para poder usarla en el controlador
        return nombrecompleto;
    }

    public static List<Map<String, Object>> leerProfesores() {
        List<Map<String, Object>> profesores = new ArrayList<>();

        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("profesores");

            ApiFuture<QuerySnapshot> future = colRef.get();
            QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                Map<String, Object> profesor = new HashMap<>();
                profesor.put("id", documentSnapshot.getId());
                profesor.put("nombre", documentSnapshot.getString("nombre"));
                profesor.put("telefono", documentSnapshot.getString("telefono"));
                profesor.put("correo", documentSnapshot.getString("correo"));
                profesor.put("imagen", documentSnapshot.getString("imagen"));
                profesores.add(profesor);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener los profesores: " + e.getMessage());
        }
        return profesores;
    }

    public static Map<String, Object> leerUnProfesor(String idProfesor) {

        Map<String, Object> profesor = new HashMap<>();
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            DocumentReference docRef = firestoreCRUD.db.collection("profesores").document(idProfesor);
            DocumentSnapshot documentSnapshot = docRef.get().get();
            profesor.put("id", documentSnapshot.getId());
            profesor.put("nombre", documentSnapshot.getString("nombre"));
            profesor.put("telefono", documentSnapshot.getString("telefono"));
            profesor.put("correo", documentSnapshot.getString("correo"));
            profesor.put("imagen", documentSnapshot.getString("imagen"));

        } catch (Exception e) {
            System.err.println("Error al obtener el profesor: " + e.getMessage());
        }
        return profesor;
    }


    public static List<Map<String, Object>> leerAsistencias() {
        List<Map<String, Object>> asistencias = new ArrayList<>();

        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("asistencias");

            ApiFuture<QuerySnapshot> future = colRef.get();
            QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                Map<String, Object> asistencia = new HashMap<>();
                asistencia.put("id", documentSnapshot.getId());
                asistencia.put("fecha", documentSnapshot.getString("fecha"));
                asistencia.put("asistencias", documentSnapshot.getString("asistencias"));
                asistencia.put("subproyecto", documentSnapshot.getString("subproyecto"));
                asistencia.put("profesor", documentSnapshot.getString("profesor"));
                asistencias.add(asistencia);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener las asistencias: " + e.getMessage());
        }
        return asistencias;
    }

    public static List<Map<String, Object>> leerAsistenciasUnProfesor(String profesor) {
    List<Map<String, Object>> asistencias = new ArrayList<>();

    try {
        FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
        CollectionReference colRef = firestoreCRUD.db.collection("asistencias");

        // Consulta con filtro y ordenamiento
        Query query = colRef.whereEqualTo("profesor", profesor)
                          .orderBy("fecha", Direction.DESCENDING);

        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                Map<String, Object> asistencia = new HashMap<>();
                asistencia.put("id", documentSnapshot.getId());
                asistencia.put("fecha", documentSnapshot.getString("fecha"));
                asistencia.put("asistencias", documentSnapshot.getString("asistencias"));
                asistencia.put("subproyecto", documentSnapshot.getString("subproyecto"));
                asistencia.put("profesor", documentSnapshot.getString("profesor"));
                asistencias.add(asistencia);
            }   
    } catch (Exception e) {
        System.err.println("Error al obtener asistencias ordenadas: " + e.getMessage());
    }
    return asistencias;
}

public static Asistencia crearAsistencia(Asistencia asistencia) {
        try {

            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();

            // Crear una referencia al documento
            DocumentReference docRef = firestoreCRUD.db
                    .collection("estudiantes")
                    .document(asistencia.getId());

            // Crear un mapa con los datos del estudiante
            Map<String, Object> estudiante = new HashMap<>();

            // Agregar los datos del estudiante
            estudiante.put("id", asistencia.getId());
            estudiante.put("fecha", asistencia.getFecha());
            estudiante.put("subproyecto", asistencia.getSubproyecto());
            estudiante.put("profesor", asistencia.getProfesor());
            estudiante.put("estudiantes", asistencia.getEstudiantes());
            // Guardar el documento en Firestore
            ApiFuture<WriteResult> result = docRef.set(estudiante);

            System.out.println("Documento guardado con ID: " + result.get().getUpdateTime());
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error al guardar el documento: " + e.getMessage());
        }
        return asistencia;
    }


    /*
     * public static void main(String[] args) {
     * 
     * //crearEstudiante();
     * // leerEstudiantes();
     * //leerUnEstudiante();
     * //actualizarEstudiante();
     * EliminarEstudiante();
     * }
     */
}

/*
 * C = Create - Crear
 * R = Read - Leer (leer todos, leer solo uno)
 * U = Update - Actualizar
 * D = Delete - Eliminar
 */

// leer un solo estudiante
// borrar un estudiante
// actualizar un estudiante
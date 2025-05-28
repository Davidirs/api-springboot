package com.example.apiasistencia.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.apiasistencia.models.Asistencia;
import com.example.apiasistencia.resources.FirestoreCRUD;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

public class AsistenciaService {

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
                asistencia.put("estudiantes", (List<String>) documentSnapshot.get("estudiantes"));
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
            Query query = colRef.whereEqualTo("profesor", profesor);
            // .orderBy("fecha", Direction.DESCENDING);

            ApiFuture<QuerySnapshot> future = query.get();
            QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                Map<String, Object> asistencia = new HashMap<>();
                asistencia.put("id", documentSnapshot.getId());
                asistencia.put("fecha", documentSnapshot.getString("fecha"));
                asistencia.put("estudiantes", (List<String>) documentSnapshot.get("estudiantes"));
                asistencia.put("subproyecto", documentSnapshot.getString("subproyecto"));
                asistencia.put("profesor", documentSnapshot.getString("profesor"));
                asistencias.add(asistencia);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener asistencias ordenadas: " + e.getMessage());
        }
        return asistencias;
    }

    public static List<Map<String, Object>> leerAsistenciasUnSubproyecto(String subproyecto) {
        List<Map<String, Object>> asistencias = new ArrayList<>();

        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("asistencias");

            // Consulta con filtro y ordenamiento
            Query query = colRef.whereEqualTo("subproyecto", subproyecto);
            // .orderBy("fecha", Direction.DESCENDING);

            ApiFuture<QuerySnapshot> future = query.get();
            QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                Map<String, Object> asistencia = new HashMap<>();
                asistencia.put("id", documentSnapshot.getId());
                asistencia.put("fecha", documentSnapshot.getString("fecha"));
                asistencia.put("estudiantes", (List<String>) documentSnapshot.get("estudiantes"));
                asistencia.put("subproyecto", documentSnapshot.getString("subproyecto"));
                asistencia.put("profesor", documentSnapshot.getString("profesor"));
                asistencia.put("descripcion", documentSnapshot.getString("descripcion"));
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
            /*
             * // Crear una referencia al documento
             * DocumentReference docRef = firestoreCRUD.db
             * .collection("asistencias")
             * .document(asistencia.getId());
             */
            // Crear una referencia a una nueva colección con ID automático
            DocumentReference docRef = firestoreCRUD.db
                    .collection("asistencias")
                    .document(); // Sin parámetro = ID automático

            // Establecer el ID generado en el objeto asistencia
            asistencia.setId(docRef.getId());
            // Crear un mapa con los datos del asistencia
            Map<String, Object> nuevaAsistencia = new HashMap<>();

            // Agregar los datos del asistencia
            nuevaAsistencia.put("id", asistencia.getId());
            nuevaAsistencia.put("fecha", asistencia.getFecha());
            nuevaAsistencia.put("subproyecto", asistencia.getSubproyecto());
            nuevaAsistencia.put("profesor", asistencia.getProfesor());
            nuevaAsistencia.put("estudiantes", asistencia.getEstudiantes());
            nuevaAsistencia.put("descripcion", asistencia.getDescripcion());
            // Guardar el documento en Firestore
            ApiFuture<WriteResult> result = docRef.set(nuevaAsistencia);

            System.out.println("Documento guardado con ID: " + result.get().getUpdateTime());
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Error al guardar el documento: " + e.getMessage());
        }
        return asistencia;
    }

}

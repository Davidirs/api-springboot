package com.example.apiasistencia.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.apiasistencia.models.Profesor;
import com.example.apiasistencia.models.Subproyecto;
import com.example.apiasistencia.resources.FirestoreCRUD;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;

public class SubproyectoService {
    public static Subproyecto crearSubproyecto(Subproyecto subproyecto) {
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("subproyectos");
            // crear el subproyecto en Firestore
            colRef.add(subproyecto);
        } catch (Exception e) {
            System.err.println("Error al crear subproyecto: " + e.getMessage());
        }
        return subproyecto;
    }

    public static Subproyecto actualizarSubproyecto(Subproyecto subproyecto) {
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("subproyectos");
            // Actualizar el subproyecto en Firestore
            colRef.document(subproyecto.getId())
                    .set(subproyecto);
        } catch (Exception e) {
            System.err.println("Error al obtener subproyectos. ordenadas: " + e.getMessage());
        }
        return subproyecto;
    }

    public static List<Map<String, Object>> leerSubproyectos() {
        List<Map<String, Object>> subproyectos = new ArrayList<>();
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            System.out.println("subproyectos");
            CollectionReference colRef = firestoreCRUD.db.collection("subproyectos");

            ApiFuture<QuerySnapshot> future = colRef.get();
            QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                Map<String, Object> subproyecto = new HashMap<>();
                subproyecto.put("id", documentSnapshot.getId());
                subproyecto.put("nombre", documentSnapshot.getString("nombre"));
                subproyecto.put("profesor", documentSnapshot.getString("profesor"));
                subproyecto.put("carrera", documentSnapshot.getString("carrera"));
                subproyectos.add(subproyecto);
            }
            
        } catch (Exception e) {
            System.err.println("Error al obtener las subproyectos: " + e.getMessage());
        }
        return subproyectos;
    }

    public static List<Map<String, Object>> leerSubproyectosUnProfesor(Profesor profesor) {
        List<Map<String, Object>> subproyectos = new ArrayList<>();

        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("subproyectos");

            // Consulta con filtro y ordenamiento
            Query query = colRef.whereEqualTo("profesor", profesor.getId());
            // .orderBy("fecha", Direction.DESCENDING);

            ApiFuture<QuerySnapshot> future = query.get();
            QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {

                Map<String, Object> subproyecto = new HashMap<>();
                subproyecto.put("id", documentSnapshot.getId());
                subproyecto.put("nombre", documentSnapshot.getString("nombre"));
                subproyecto.put("profesor", documentSnapshot.getString("profesor"));
                subproyecto.put("carrera", documentSnapshot.getString("carrera"));

                subproyectos.add(subproyecto);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener subproyectos. ordenadas: " + e.getMessage());
        }
        return subproyectos;
    }

}
package com.example.apiasistencia.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.apiasistencia.models.Profesor;
import com.example.apiasistencia.models.Justificativo;
import com.example.apiasistencia.resources.FirestoreCRUD;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;

public class JustificativoService {
    public static Justificativo crearJustificativo(Justificativo justificativo) {
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("justificativos");
            // crear el justificativo en Firestore
            colRef.add(justificativo);
        } catch (Exception e) {
            System.err.println("Error al crear justificativo: " + e.getMessage());
        }
        return justificativo;
    }

    public static Justificativo actualizarJustificativo(Justificativo justificativo) {
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("justificativos");
            // Actualizar el justificativo en Firestore
            colRef.document(justificativo.getId())
                    .set(justificativo);
        } catch (Exception e) {
            System.err.println("Error al obtener justificativos. ordenadas: " + e.getMessage());
        }
        return justificativo;
    }

    public static List<Map<String, Object>> leerJustificativos() {
        List<Map<String, Object>> justificativos = new ArrayList<>();
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            System.out.println("justificativos");
            CollectionReference colRef = firestoreCRUD.db.collection("justificativos");
            
            ApiFuture<QuerySnapshot> future = colRef.get();
            QuerySnapshot querySnapshot = future.get();
            System.out.println("justificativos");
            for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                System.out.println(documentSnapshot);

                Map<String, Object> justificativo = new HashMap<>();
                justificativo.put("id", documentSnapshot.getId());
                justificativo.put("descripcion", documentSnapshot.getString("descripcion"));
                justificativo.put("profesor", documentSnapshot.getString("profesor"));
                justificativo.put("fecha", documentSnapshot.getString("fecha"));
                justificativo.put("imageUrl", documentSnapshot.getString("imageUrl"));
                justificativos.add(justificativo);
            }
            
        } catch (Exception e) {
            System.err.println("Error al obtener las justificativos: " + e.getMessage());
        }
        return justificativos;
    }

    public static List<Map<String, Object>> leerJustificativosUnProfesor(Profesor profesor) {
        List<Map<String, Object>> justificativos = new ArrayList<>();

        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("justificativos");

            // Consulta con filtro y ordenamiento
            Query query = colRef.whereEqualTo("profesor", profesor.getId());
            // .orderBy("fecha", Direction.DESCENDING);

            ApiFuture<QuerySnapshot> future = query.get();
            QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {

                Map<String, Object> justificativo = new HashMap<>();
                justificativo.put("id", documentSnapshot.getId());
                justificativo.put("descripcion", documentSnapshot.getString("descripcion"));
                justificativo.put("profesor", documentSnapshot.getString("profesor"));
                justificativo.put("fecha", documentSnapshot.getString("fecha"));
                justificativo.put("imageUrl", documentSnapshot.getString("imageUrl"));

                justificativos.add(justificativo);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener justificativos. ordenadas: " + e.getMessage());
        }
        return justificativos;
    }

}
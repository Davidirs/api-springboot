package com.example.apiasistencia.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.apiasistencia.models.Profesor;
import com.example.apiasistencia.resources.FirestoreCRUD;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

public class ProfesorService {

    public static Profesor crearProfesor(Profesor profesor) {
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("profesores");
            // crear el Profesor en Firestore
            colRef.add(profesor);
        } catch (Exception e) {
            System.err.println("Error al crear Profesor: " + e.getMessage());
        }
        return profesor;
    }

    public static Profesor actualizarProfesor(Profesor profesor) {
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("profesores");
            // Actualizar el Profesor en Firestore
            colRef.document(profesor.getId())
                    .set(profesor);
        } catch (Exception e) {
            System.err.println("Error al obtener Profesors. ordenadas: " + e.getMessage());
        }
        return profesor;
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

}

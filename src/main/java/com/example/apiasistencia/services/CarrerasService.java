package com.example.apiasistencia.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.apiasistencia.models.Carrera;
import com.example.apiasistencia.models.Estudiante;
import com.example.apiasistencia.resources.FirestoreCRUD;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

public class CarrerasService {
    public static List<Map<String, Object>> leerCarreras() {
        // Implementación para crear una nueva carrera
        // Aquí se puede utilizar FirestoreCRUD para agregar la carrera a la base de
        // datos
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            // Obtener todos los documentos de la colección
            ApiFuture<QuerySnapshot> future = firestoreCRUD.db.collection("carreras").get();
            QuerySnapshot querySnapshot = future.get();
            
            // Convertir documentos a objetos Estudiante
            List<Map<String, Object>> carreras = new ArrayList<>();
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                Map<String, Object> carrera = new HashMap<>();
                carrera.put("id", document.getId());
                carrera.put("nombre", document.getString("nombre"));
                carreras.add(carrera);
            }

            return carreras;

        } catch (Exception e) {
            System.err.println("Error al obtener los estudiantes: " + e.getMessage());
            throw new RuntimeException("Error al leer estudiantes", e);
        }
    }
public static Carrera crearCarrera(Carrera carrera) {
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("carreras");
            // crear el Carrera en Firestore
            colRef.add(carrera);
        } catch (Exception e) {
            System.err.println("Error al crear Carrera: " + e.getMessage());
        }
        return carrera;
    }

    public static Carrera actualizarCarrera(Carrera carrera) {
        try {
            FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
            CollectionReference colRef = firestoreCRUD.db.collection("carreras");
            // Actualizar el Carrera en Firestore
            colRef.document(carrera.getId())
                    .set(carrera);
        } catch (Exception e) {
            System.err.println("Error al obtener Carreras. ordenadas: " + e.getMessage());
        }
        return carrera;
    }
    
}

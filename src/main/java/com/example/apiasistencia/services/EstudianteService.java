package com.example.apiasistencia.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.apiasistencia.resources.FirestoreCRUD;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.example.apiasistencia.models.Estudiante;

public class EstudianteService {
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


    public static List<Estudiante> leerEstudiantes() {
    try {
        FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
        /* list<String> estudiantes = [
            "24114415",
            "30740994"
        ]; */
        // Obtener todos los documentos de la colección
        ApiFuture<QuerySnapshot> future = firestoreCRUD.db.collection("estudiantes").get();
        QuerySnapshot querySnapshot = future.get();
        
        // Convertir documentos a objetos Estudiante
        List<Estudiante> estudiantes = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            Estudiante estudiante = document.toObject(Estudiante.class);
            estudiante.setcedula(document.getId()); // Asignar el ID del documento como cédula
            estudiantes.add(estudiante);
        }
        
        return estudiantes;
        
    } catch (Exception e) {
        System.err.println("Error al obtener los estudiantes: " + e.getMessage());
        throw new RuntimeException("Error al leer estudiantes", e);
    }
}
public static List<Estudiante> buscarEstudiantesPorCedulasParallel(List<String> cedulas) {
    List<List<String>> lotes = dividirLista(cedulas, 10);
    
        FirestoreCRUD firestoreCRUD = new FirestoreCRUD();
    return lotes.parallelStream()
        .flatMap(lote -> {
            try {
                QuerySnapshot snapshot = firestoreCRUD.db
                    .collection("estudiantes")
                    .whereIn("cedula", lote)
                    .get()
                    .get();
                return snapshot.getDocuments().stream()
                    .map(doc -> doc.toObject(Estudiante.class));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        })
        .collect(Collectors.toList());
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

// Método auxiliar para dividir listas
private static <T> List<List<T>> dividirLista(List<T> listaOriginal, int tamañoLote) {
    List<List<T>> listasDivididas = new ArrayList<>();
    for (int i = 0; i < listaOriginal.size(); i += tamañoLote) {
        listasDivididas.add(listaOriginal.subList(i, Math.min(i + tamañoLote, listaOriginal.size())));
    }
    return listasDivididas;
}
}

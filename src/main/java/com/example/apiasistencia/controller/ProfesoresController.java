package com.example.apiasistencia.controller;

import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiasistencia.models.Profesor;
import com.example.apiasistencia.resources.FirestoreCRUD;
import com.example.apiasistencia.services.ProfesorService;

@RestController
public class ProfesoresController {
    // importandola clase creadapor arocha
    FirestoreCRUD firestoreCRUD = new FirestoreCRUD();

    @GetMapping("/profesores")
    public List<Map<String, Object>> profesoresGet() {
        List<Map<String, Object>> profesoresEncontrados = ProfesorService.leerProfesores();
        return profesoresEncontrados;
    }
    @PostMapping("/profesor")
    public ResponseEntity<?> buscarProfesor(@RequestBody  String idProfesor ) {
        Map<String, Object> profesorEncontrado = ProfesorService.leerUnProfesor(idProfesor);
        if (profesorEncontrado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profesorEncontrado);
    }
    @PostMapping("/crearprofesor")
    public ResponseEntity<?> crearProfesor(@RequestBody  Profesor profesor ) {
        Profesor profesorcreado = ProfesorService.crearProfesor(profesor);
        if (profesorcreado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profesorcreado);
    }
    @PostMapping("/actualizarprofesor")
    public ResponseEntity<?> actualizarProfesor(@RequestBody  Profesor profesor ) {
        Profesor profesorActualizado = ProfesorService.actualizarProfesor(profesor);
        if (profesorActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profesorActualizado);
    }    
}

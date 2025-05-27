package com.example.apiasistencia.controller;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiasistencia.resources.FirestoreCRUD;

@RestController
public class ProfesoresController {
    // importandola clase creadapor arocha
    FirestoreCRUD firestoreCRUD = new FirestoreCRUD();

    @GetMapping("/leerprofesores")
    public List<Map<String, Object>> profesoresGet() {

        List<Map<String, Object>> profesoresEncontrados = FirestoreCRUD.leerProfesores();

        return profesoresEncontrados;
    }
    @PostMapping("/leerprofesor")
    public ResponseEntity<?> buscarProfesor(@RequestBody  String idProfesor ) {
        

        Map<String, Object> profesorEncontrado = FirestoreCRUD.leerUnProfesor(idProfesor);
        if (profesorEncontrado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profesorEncontrado);
    }

    @GetMapping("/prueba")
    public String prueba() {
        // creo una variable para devolver la nombrecompleto
        // porque al ejecutar la funcion leer un estudiante me devuelve un string
        String nombrecompletoDevuelta = FirestoreCRUD.leerUnEstudiante();
        return nombrecompletoDevuelta;
    }




    
}

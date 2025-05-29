package com.example.apiasistencia.controller;

import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.apiasistencia.models.Estudiante;
import com.example.apiasistencia.services.EstudianteService;

@RestController
public class EstudiantesController {

    @GetMapping("/estudiantes")
    public List<Estudiante> estudiantesGet() {
        List<Estudiante> estudiantes = EstudianteService.leerEstudiantes();
        return estudiantes;
    }
    @PostMapping("/buscarestudiantes")
    public ResponseEntity<?> buscarEstudiantes(@RequestBody  List<String> idEstudiantes ) {
        List<Estudiante> estudiantesEncontrados = EstudianteService.buscarEstudiantesPorCedulasParallel(idEstudiantes);
        if (estudiantesEncontrados == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estudiantesEncontrados);
    }
}


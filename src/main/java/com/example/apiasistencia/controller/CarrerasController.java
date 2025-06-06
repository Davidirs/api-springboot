package com.example.apiasistencia.controller;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiasistencia.models.Carrera;
import com.example.apiasistencia.models.Estudiante;
import com.example.apiasistencia.models.Profesor;
import com.example.apiasistencia.services.CarrerasService;
import com.example.apiasistencia.services.EstudianteService;
import com.example.apiasistencia.services.ProfesorService;
@RestController
public class CarrerasController {
    @GetMapping("/carreras")
    public List<Map<String, Object>> obtenerCarreras() {
        // List<Estudiante> estudiantes = EstudianteService.leerEstudiantes();
        List<Map<String, Object>> carreras = CarrerasService.leerCarreras();
        
        return carreras; 
    }
    
    @PostMapping("/crearcarrera")
    public ResponseEntity<?> crearCarrera(@RequestBody Carrera carrera ) {
        Carrera carreraCreada = CarrerasService.crearCarrera(carrera);
        if (carreraCreada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carreraCreada);
    }
    @PostMapping("/actualizarcarrera")
    public ResponseEntity<?> actualizarCarrera(@RequestBody  Carrera carrera ) {
        Carrera carreraActualizado = CarrerasService.actualizarCarrera(carrera);
        if (carreraActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carreraActualizado);
    }    
}

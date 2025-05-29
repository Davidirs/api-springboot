package com.example.apiasistencia.controller;

import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.apiasistencia.models.Asistencia;
import com.example.apiasistencia.models.Profesor;
import com.example.apiasistencia.models.Subproyecto;
import com.example.apiasistencia.resources.FirestoreCRUD;
import com.example.apiasistencia.services.AsistenciaService;

@RestController
public class AsistenciasController {
    

    @PostMapping("/agregarasistencia")
    public ResponseEntity<?> agregarAsistencia(@RequestBody Asistencia asistencia) {
        try {
            Asistencia asistenciaGuardada = AsistenciaService.crearAsistencia(asistencia);
            return ResponseEntity.ok(asistenciaGuardada);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/asistencias")
    public List<Map<String, Object>> asistenciasGet() {
        List<Map<String, Object>> asistencias = AsistenciaService.leerAsistencias();

        return asistencias;
    }

    @PostMapping("/asistenciasprofesor")
    public List<Map<String, Object>> asistenciasProfesor(@RequestBody Profesor profesor) {
        List<Map<String, Object>> asistencias = AsistenciaService.leerAsistenciasUnProfesor(profesor);

        return asistencias;
    }
    
    @PostMapping("/asistenciassubproyecto")
    public List<Map<String, Object>> asistenciasSubproyecto(@RequestBody Subproyecto subproyecto) {
        List<Map<String, Object>> asistencias = AsistenciaService.leerAsistenciasUnSubproyecto(subproyecto);

        return asistencias;
    }

    public class ErrorResponse {
        private String mensaje;
        private String detalles;

        public ErrorResponse(String mensaje, String detalles) {
            this.mensaje = mensaje;
            this.detalles = detalles;
        }

        public String getMensaje() {
            return mensaje;
        }

        public String getDetalles() {
            return detalles;
        }
    }
}

package com.example.apiasistencia.controller;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
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
public class AsistenciasController {
    public static class Asistencia {
        private String id;
        private String fecha;
        private List<String> estudiantes;
        private String subproyecto;
        private String profesor;

        // Constructor vacío necesario para la deserialización
        public Asistencia() {
        }

        public Asistencia(String id,String fecha, List<String> estudiantes, String subproyecto, String profesor) {
            this.id = id;
            this.fecha = fecha;
            this.estudiantes = estudiantes;
            this.subproyecto = subproyecto;
            this.profesor = profesor;
        }

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public List<String> getEstudiantes() {
            return estudiantes;
        }

        public void setEstudiantes(List<String> estudiantes) {
            this.estudiantes = estudiantes;
        }

        public String getSubproyecto() {
            return subproyecto;
        }

        public void setSubproyecto(String subproyecto) {
            this.subproyecto = subproyecto;
        }

        public String getProfesor() {
            return profesor;
        }

        public void setProfesor(String profesor) {
            this.profesor = profesor;
        }
    }

    @PostMapping("/agregarasistencia")
    public ResponseEntity<?> agregarAsistencia(@RequestBody Asistencia asistencia) {
        try {
            Asistencia asistenciaGuardada = FirestoreCRUD.crearAsistencia(asistencia);
            return ResponseEntity.ok(asistenciaGuardada);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/asistencias")
    public List<Map<String, Object>> asistenciasGet() {
        List<Map<String, Object>> asistencias = FirestoreCRUD.leerAsistencias();

        return asistencias;
    }

    @PostMapping("/asistenciasprofesor")
    public List<Map<String, Object>> asistenciasProfesor(@RequestBody String idProfesor) {
        List<Map<String, Object>> asistencias = FirestoreCRUD.leerAsistenciasUnProfesor(idProfesor);

        return asistencias;
    }
    
    @PostMapping("/asistenciassubproyecto")
    public List<Map<String, Object>> asistenciasSubproyecto(@RequestBody String idSubproyecto) {
        List<Map<String, Object>> asistencias = FirestoreCRUD.leerAsistenciasUnSubproyecto(idSubproyecto);

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

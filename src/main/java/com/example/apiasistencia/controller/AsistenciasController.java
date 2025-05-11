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

@RestController
public class AsistenciasController {
    public static class Asistencia {
        private String fecha;
        private List<String> asistencias;
        private String subproyecto;
        private String profesor;

        // Constructor vacío necesario para la deserialización
        public Asistencia() {
        }

        public Asistencia(String fecha, List<String> asistencias, String subproyecto, String profesor) {
            this.fecha = fecha;
            this.asistencias = asistencias;
            this.subproyecto = subproyecto;
            this.profesor = profesor;
        }

        // Getters y Setters
        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public List<String> getAsistencias() {
            return asistencias;
        }

        public void setAsistencias(List<String> asistencias) {
            this.asistencias = asistencias;
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
            // Simplemente devolvemos la asistencia recibida como prueba
            // En una aplicación real, aquí guardarías en base de datos
            Asistencia asistenciaGuardada = guardarAsistencia(asistencia);
            return ResponseEntity.ok(asistenciaGuardada);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/asistencias")
    public List<Map<String, Object>> asistenciasGet() {
        List<Map<String, Object>> asistencias = new ArrayList<>();

        asistencias.add(new HashMap<String, Object>() {
            {
                put("fecha", "02/05/2025");
                put("asistencias", Arrays.asList("24114415", "30321239", "31204836", "30740994", "31598995"));
                put("subproyecto", "subproyectoID");
                put("profesor", "profesorID");
            }
        });

        asistencias.add(new HashMap<String, Object>() {
            {
                put("fecha", "26/04/2025");
                put("asistencias", Arrays.asList("24114415", "30321239", "31204836", "30740994"));
                put("subproyecto", "subproyectoID");
                put("profesor", "profesorID");
            }
        });

        return asistencias;
    }

    private Asistencia guardarAsistencia(Asistencia asistencia) {
        // Aquí debes implementar la lógica para guardar la asistencia en la base de
        // datos
        // o realizar cualquier otra acción necesaria
        return asistencia;
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

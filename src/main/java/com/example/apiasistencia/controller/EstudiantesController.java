package com.example.apiasistencia.controller;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstudiantesController {

    @GetMapping("/estudiantes")
    public List<Map<String, Object>> estudiantesGet() {
        List<Map<String, Object>> estudiantes = new ArrayList<>();
        estudiantes.add(new HashMap<String, Object>() {
            {
                put("cedula", "24114415");
                put("nombre", "David Reyes");
                put("carrera", "Ingenieria informatica");
                put("semestre", "5");
                put("telefono", "31.204.836");
                put("correo", "O8g4P@example.com");
                put("imagen",
                        "https://images.pexels.com/photos/220451/pexels-photo-220451.jpeg?auto=compress&cs=tinysrgb&w=600");
            }
        });

        estudiantes.add(new HashMap<String, Object>() {
            {
                put("cedula", "30321239");
                put("nombre", "Rafael Arocha");
                put("carrera", "Ingenieria informatica");
                put("semestre", "5");
                put("telefono", "31.204.836");
                put("correo", "O8g4P@example.com");
                put("imagen",
                        "https://images.pexels.com/photos/220452/pexels-photo-220452.jpeg?auto=compress&cs=tinysrgb&w=600");
            }
        });

        estudiantes.add(new HashMap<String, Object>() {
            {
                put("cedula", "31204836");
                put("nombre", "Andres Calles");
                put("carrera", "Ingenieria informatica");
                put("semestre", "5");
                put("telefono", "04127575904");
                put("correo", "O8g4P@example.com");
                put("imagen",
                        "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=600");
            }
        });

        estudiantes.add(new HashMap<String, Object>() {
            {
                put("cedula", "30740994");
                put("nombre", "Antony Guevara");
                put("carrera", "Ingenieria informatica");
                put("semestre", "5");
                put("telefono", "31204836");
                put("correo", "O8g4P@example.com");
                put("imagen",
                        "https://images.pexels.com/photos/220454/pexels-photo-220454.jpeg?auto=compress&cs=tinysrgb&w=600");
            }
        });

        estudiantes.add(new HashMap<String, Object>() {
            {
                put("cedula", "31598995");
                put("nombre", "Jonathan Leal");
                put("carrera", "Ingenieria informatica");
                put("semestre", "5");
                put("telefono", "31204836");
                put("correo", "O8g4P@example.com");
                put("imagen",
                        "https://images.pexels.com/photos/220455/pexels-photo-220455.jpeg?auto=compress&cs=tinysrgb&w=600");
            }
        });

        return estudiantes;
    }

    
    
}



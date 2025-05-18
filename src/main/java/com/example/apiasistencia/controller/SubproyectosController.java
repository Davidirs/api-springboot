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

import com.example.apiasistencia.resources.FirestoreCRUD;

@RestController
public class SubproyectosController {

    @GetMapping("/subproyectos")
    public List<Map<String, Object>> subproyectosGet() {
        List<Map<String, Object>> subproyectos= FirestoreCRUD.leerSubproyectos();
        return subproyectos;
    }
       @PostMapping("/listasubproyectosprofesor")
    public List<Map<String, Object>> listasubproyectosProfesor(@RequestBody String idProfesor) {
        List<Map<String, Object>> subproyectos = FirestoreCRUD.leerSubproyectosUnProfesor(idProfesor);

        return subproyectos;
    }
    
}


package com.example.apiasistencia.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiasistencia.models.Subproyecto;
import com.example.apiasistencia.services.SubproyectoService;

@RestController
public class SubproyectosController {

    @PostMapping("/crearsubproyecto")
    public Subproyecto crearsubproyecto(@RequestBody Subproyecto subproyecto) {
        Subproyecto subproyectoActualizado = SubproyectoService.crearSubproyecto(subproyecto);
        return subproyectoActualizado;
    }

    @PostMapping("/actualizarsubproyecto")
    public Subproyecto actualizarsubproyecto(@RequestBody Subproyecto subproyecto) {
        Subproyecto subproyectoActualizado = SubproyectoService.actualizarSubproyecto(subproyecto);
        return subproyectoActualizado;
    }
    
    @GetMapping("/subproyectos")
    public List<Map<String, Object>> subproyectos() {

        List<Map<String, Object>> subproyectos = SubproyectoService.leerSubproyectos();

        return subproyectos;
    }
boolean esJoven;
    @PostMapping("/listasubproyectosprofesor")
    public List<Map<String, Object>> listasubproyectosProfesor(@RequestBody String idProfesor) {
        List<Map<String, Object>> subproyectos = SubproyectoService.leerSubproyectosUnProfesor(idProfesor);
        return subproyectos;
    }


}

package com.example.apiasistencia.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiasistencia.models.Profesor;
import com.example.apiasistencia.models.Justificativo;
import com.example.apiasistencia.services.JustificativoService;

@RestController
public class JustificativosController {

    @PostMapping("/crearjustificativo")
    public Justificativo crearjustificativo(@RequestBody Justificativo justificativo) {
        Justificativo justificativoActualizado = JustificativoService.crearJustificativo(justificativo);
        return justificativoActualizado;
    }

    @PostMapping("/actualizarjustificativo")
    public Justificativo actualizarjustificativo(@RequestBody Justificativo justificativo) {
        Justificativo justificativoActualizado = JustificativoService.actualizarJustificativo(justificativo);
        return justificativoActualizado;
    }
    
    @GetMapping("/justificativos")
    public List<Map<String, Object>> justificativos() {

        List<Map<String, Object>> justificativos = JustificativoService.leerJustificativos();

        return justificativos;
    }
    
    @PostMapping("/listajustificativosprofesor")
    public List<Map<String, Object>> listajustificativosProfesor(@RequestBody Profesor profesor) {
        List<Map<String, Object>> justificativos = JustificativoService.leerJustificativosUnProfesor(profesor);
        return justificativos;
    }


}

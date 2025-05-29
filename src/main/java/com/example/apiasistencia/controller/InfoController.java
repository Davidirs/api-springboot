package com.example.apiasistencia.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping("/status")
    public String status() {
        return "Servicio funcionando correctamente";
    }
}
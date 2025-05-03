package com.example.apiasistencia.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("/hello")
    public String hello(@RequestBody(required = false) String name) {
        return "¡Hola " + (name != null ? name : "Mundo") + "!";
    }

    @GetMapping("/hello")
    public String helloGet(@RequestParam(required = false) String name) {
        return "¡Hola " + (name != null ? name : "Mundo") + "!";
    }
} 
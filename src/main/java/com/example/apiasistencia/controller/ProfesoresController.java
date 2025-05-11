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
public class ProfesoresController {

    @GetMapping("/profesores")
    public List<Map<String, Object>> profesoresGet() {
        List<Map<String, Object>> profesores = new ArrayList<>();
        profesores.add(new HashMap<String, Object>() {
            {
                put("cedula", "24114415");
                put("nombre", "Gabriel Vielma");
                put("telefono", "04145021471");
                put("correo", "gabrielvielma91@gmail.com");
                put("imagen",
                        "https://lh3.googleusercontent.com/cm/AGPWSu9E4K66u1GRzKXEbgoqerRKCGDtMzMaNt50-8szNfgiZhmDJwptPK_Ta8_Om1jva7HOBw=s48-p");
            }
        });
        return profesores;
    }
}


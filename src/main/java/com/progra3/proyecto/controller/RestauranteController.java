package com.progra3.proyecto.controller;

import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> getAllRestaurantes() {
        return restauranteService.getAllRestaurantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestauranteById(@PathVariable Long id) {
        return restauranteService.getRestauranteById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Restaurante createRestaurante(@RequestBody Restaurante restaurante) {
        return restauranteService.saveRestaurante(restaurante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id) {
        restauranteService.deleteRestaurante(id);
        return ResponseEntity.noContent().build();
    }
}


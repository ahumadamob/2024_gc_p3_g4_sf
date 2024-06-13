package com.progra3.proyecto.controller;

import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.service.IRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteController {
    @Autowired
    private IRestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> getAllRestaurantes() {
        return restauranteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestauranteById(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.getById(id);
        return (restaurante != null) ? ResponseEntity.ok(restaurante) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Restaurante createRestaurante(@RequestBody Restaurante restaurante) {
        return restauranteService.save(restaurante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id) {
        if (restauranteService.exists(id)) {
            restauranteService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> updateRestaurante(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        if (restauranteService.exists(id)) {
            restaurante.setId(id);
            Restaurante updatedRestaurante = restauranteService.save(restaurante);
            return ResponseEntity.ok(updatedRestaurante);
        }
        return ResponseEntity.notFound().build();
    }
}


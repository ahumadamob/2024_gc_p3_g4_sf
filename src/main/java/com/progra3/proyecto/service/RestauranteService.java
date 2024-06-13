package com.progra3.proyecto.service;

import com.progra3.proyecto.entity.Restaurante;


import java.util.List;
import java.util.Optional;

public interface RestauranteService {
    List<Restaurante> getAllRestaurantes();
    Optional<Restaurante> getRestauranteById(Long id);
    Restaurante saveRestaurante(Restaurante restaurante);
    void deleteRestaurante(Long id);
}


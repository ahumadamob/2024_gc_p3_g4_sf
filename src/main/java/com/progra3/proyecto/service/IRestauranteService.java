package com.progra3.proyecto.service;

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.entity.Restaurante;
import java.util.List;

public interface IRestauranteService {
    List<Restaurante> getAll();
    Restaurante getById(Long id);
    Restaurante save(Restaurante restaurante);
    void delete(Long id);
    boolean exists(Long id);
    List<Restaurante> buscarPorNombre(String nombre);
    List<Repartidor> getRepartidoresPorRestaurante(Long restauranteId);
   
  }

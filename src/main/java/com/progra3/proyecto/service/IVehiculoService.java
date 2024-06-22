package com.progra3.proyecto.service;

import java.util.List;

import com.progra3.proyecto.entity.Vehiculo;

public interface IVehiculoService {
    List<Vehiculo> getAll();
    Vehiculo getById(Long id);
    Vehiculo save(Vehiculo vehiculo);
    void delete(Long id);
    boolean exists(Long id);
  }
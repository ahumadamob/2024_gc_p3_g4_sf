package com.progra3.proyecto.service;

import java.util.List;

import com.progra3.proyecto.entity.Vehiculo;

public interface IVehiculoService {
    List<Vehiculo> getAll();
    Vehiculo getById(Integer id);
    Vehiculo save(Vehiculo vehiculo);
    void delete(Integer id);
    boolean exists(Integer id);
  }
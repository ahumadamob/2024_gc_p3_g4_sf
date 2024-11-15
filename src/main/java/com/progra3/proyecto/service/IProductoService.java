package com.progra3.proyecto.service;

import java.util.List;
import com.progra3.proyecto.entity.Producto;

public interface IProductoService {
    List<Producto> getAll();
    Producto getById(Long id);
    Producto save(Producto producto);
    void delete(Long id);
    boolean exists(Long id);
    List<Producto> buscarPorNombre(String nombre);
  }

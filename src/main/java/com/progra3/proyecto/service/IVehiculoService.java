package com.progra3.proyecto.service;

import java.util.List;

import com.progra3.proyecto.entity.Vehiculo;

public interface IVehiculoService {
	
    public List<Vehiculo> getAll();
    public Vehiculo getById(Long id);
    public Vehiculo save(Vehiculo vehiculo);
    public void delete(Long id);
    public boolean exists(Long id);
    
    public List<Vehiculo> findByTipo (String tipo);
    
}
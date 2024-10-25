package com.progra3.proyecto.service;

import java.util.List;

import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.entity.Vehiculo;

public interface IVehiculoService {
	
    public List<Vehiculo> getAll();
    public Vehiculo getById(Integer id);
    public Vehiculo save(Vehiculo vehiculo);
    public void delete(Integer id);
    public boolean exists(Integer id);
    
    public List<Vehiculo> findByTipo (String tipo);
    
    Restaurante getRestauranteByVehiculoId(Integer id);
    
}
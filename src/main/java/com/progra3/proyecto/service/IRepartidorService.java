package com.progra3.proyecto.service;

import java.util.List;

import com.progra3.proyecto.entity.Repartidor;

public interface IRepartidorService {
	
	public List<Repartidor> getAll();
	public Repartidor getById (Long id);
	public Repartidor save (Repartidor repartidor);
	public void delete (Long id);
	public boolean exists (Long id);
	public boolean exists (String repartidor);
	List<Repartidor> buscarRepartidor(String nombre);
	List<Vehiculo> obtenerVehiculosPorRepartidorId(Long repartidorId);
	
	

}

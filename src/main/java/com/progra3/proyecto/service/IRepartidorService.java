package com.progra3.proyecto.service;

import java.util.List;

import com.progra3.proyecto.entity.Repartidor;

public interface IRepartidorService {
	
	public List<Repartidor> getAll();
	public Repartidor getById (Integer id);
	public Repartidor save (Repartidor repartidor);
	public void delete (Integer id);
	public boolean exists (Integer id);
	public boolean exists (String repartidor);
	List<Repartidor> buscarRepartidor(String nombre);
	
	//public boolean findByVehiculo_id (Integer id);


}

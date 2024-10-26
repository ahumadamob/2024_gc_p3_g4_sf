package com.progra3.proyecto.service;

import java.util.List;
import com.progra3.proyecto.entity.Repartidor;

public interface IRepartidorService {
	
	List<Repartidor> getAll();
	Repartidor getById (Long id);
	Repartidor save (Repartidor repartidor);
	void delete (Long id);
	boolean exists (Long id);
	boolean exists (String repartidor);
	List<Repartidor> buscarRepartidor(String nombre);


}

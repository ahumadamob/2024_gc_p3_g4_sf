package com.progra3.proyecto.service;

import java.time.LocalDate;
import java.util.List;

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.entity.Vehiculo;
import com.progra3.proyecto.exceptions.EdadMinimaException;

public interface IRepartidorService {
	
	List<Repartidor> getAll();
	Repartidor getById (Long id);
	Repartidor save (Repartidor repartidor) throws EdadMinimaException;
	void delete (Long id);
	boolean exists (Long id);
	boolean exists (String repartidor);
	List<Repartidor> buscarRepartidor(String nombre);
	List<Vehiculo> obtenerVehiculosPorRepartidorId(Long repartidorId);
	List<Repartidor> buscarPorRestaurante(Long restauranteId);
	int calcularAntiguedad(LocalDate fechaContratacion);
	

}

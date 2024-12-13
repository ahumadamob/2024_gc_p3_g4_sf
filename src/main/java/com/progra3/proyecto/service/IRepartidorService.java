package com.progra3.proyecto.service;

import java.time.LocalDate;
import java.util.List;

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.entity.Vehiculo;
import com.progra3.proyecto.exceptions.EdadMinimaException;
import com.progra3.proyecto.service.jpa.VehiculoNoDisponibleException;

public interface IRepartidorService {
	
	List<Repartidor> getAll();
	Repartidor getById (Long id);
	Repartidor save (Repartidor repartidor) throws EdadMinimaException;
	void delete (Long id);
	boolean exists (Long id);
	boolean existsById(Long id);
	boolean exists (String repartidor);
	List<Repartidor> buscarRepartidor(String nombre);
	List<Vehiculo> obtenerVehiculosPorRepartidorId(Long repartidorId);
	List<Repartidor> buscarPorRestaurante(Long restauranteId);
	int calcularAntiguedad(LocalDate fechaContratacion);
	Repartidor saveEntregasRepartidor(Repartidor repartidor) throws EdadMinimaException, VehiculoNoDisponibleException;
	Repartidor saveRepartidorIMP(Repartidor repartidor) throws EdadMinimaException, VehiculoNoDisponibleException;
	Repartidor saveRepartidor(Repartidor repartidor) throws EdadMinimaException, VehiculoNoDisponibleException;
	boolean isVehiculoDisponible(Vehiculo vehiculo);
	boolean isAntiguedadValida(LocalDate fechaContratacion);
	List<Repartidor> findByRestauranteId(Long restauranteId);
	boolean validarAntiguedad(LocalDate fechaContratacion);
	

}

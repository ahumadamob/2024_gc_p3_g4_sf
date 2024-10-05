package com.progra3.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progra3.proyecto.entity.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
	
	List<Vehiculo> findByTipo(String tipo);

	
}

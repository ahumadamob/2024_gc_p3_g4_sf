package com.progra3.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progra3.proyecto.entity.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
	
}

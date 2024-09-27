package com.progra3.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progra3.proyecto.entity.Repartidor;

public interface RepartidorRepository extends JpaRepository<Repartidor, Integer>{
	
	List<Repartidor> findByNombre(String nombre);
	
}

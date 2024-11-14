package com.progra3.proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.entity.Vehiculo;

public interface RepartidorRepository extends JpaRepository<Repartidor, Long>{
	
	List<Repartidor> findByNombre(String nombre);
	 @Query("SELECT r.vehiculo FROM Repartidor r WHERE r.id = :repartidorId")
	    Optional<Vehiculo> findVehiculoByRepartidorId(Long repartidorId);
	
	}

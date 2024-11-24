package com.progra3.proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progra3.proyecto.entity.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    void deleteById(Long id);
    boolean existsById(Long id);
    List<Vehiculo> findAll();
    Vehiculo save(Vehiculo vehiculo);
    List<Vehiculo> findByTipo(String tipo); 
    Optional<Vehiculo> findById(Long id);  
}


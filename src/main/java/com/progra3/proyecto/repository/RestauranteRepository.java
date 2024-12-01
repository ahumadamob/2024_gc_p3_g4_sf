package com.progra3.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progra3.proyecto.entity.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

 List<Restaurante> findByNombre(String nombre);
 boolean existsByIdAndEstado(Long id, String estado);


}


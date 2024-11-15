package com.progra3.proyecto.repository;

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.entity.Restaurante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

 List<Restaurante> findByNombre(String nombre);



}


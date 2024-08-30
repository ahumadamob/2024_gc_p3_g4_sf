package com.progra3.proyecto.repository;
import com.progra3.proyecto.entity.Restaurante;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}


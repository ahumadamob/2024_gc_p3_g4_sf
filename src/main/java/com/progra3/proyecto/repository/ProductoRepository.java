package com.progra3.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progra3.proyecto.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}

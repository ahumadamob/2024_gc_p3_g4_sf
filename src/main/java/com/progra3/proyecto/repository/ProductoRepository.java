package com.progra3.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.progra3.proyecto.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	List<Producto> findByNombre(String nombre);
	List<Producto> findAllById(Iterable<Long> ids);
}

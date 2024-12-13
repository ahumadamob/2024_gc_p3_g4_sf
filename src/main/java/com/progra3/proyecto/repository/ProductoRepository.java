package com.progra3.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.progra3.proyecto.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombre(String nombre);
    List<Producto> findAllById(Iterable<Long> ids);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
    	       "FROM Producto p " +
    	       "WHERE p.id = :productoId AND p.restaurante.id = :restauranteId AND p.stock > :stock")
    	boolean existsByIdAndRestauranteIdAndStockGreaterThan(@Param("productoId") Long productoId, 
    	                                                    @Param("restauranteId") Long restauranteId, 
    	                                                    @Param("stock") int stock);


}

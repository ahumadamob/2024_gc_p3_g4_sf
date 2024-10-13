package com.progra3.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progra3.proyecto.entity.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido , Long>{

	List<Pedido> findByCliente(String cliente);
	
	
}

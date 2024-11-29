package com.progra3.proyecto.service;

import java.util.List;

import com.progra3.proyecto.entity.Pedido;

public interface IPedidoService {

	  List<Pedido> getAll();
	  Pedido getById(Long id);
	  Pedido save(Pedido pedido);

	  void delete(Long id);
	  boolean exists(Long id);
	  boolean exists(String cliente);
	  List<Pedido> buscarPorCliente(String cliente);
	  Pedido createNuevoPedido(Pedido pedido);
	
	}


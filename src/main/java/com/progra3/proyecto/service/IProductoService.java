package com.progra3.proyecto.service;

import java.util.List;

import com.progra3.proyecto.entity.Producto;

public interface IProductoService {
	
	public List<Producto> buscarTodos();
	public Producto buscardPorId(Long id);
	public Producto guardar(Producto producto);
	public String eliminar(Long id);
	
	public boolean existe(Long Id);

}

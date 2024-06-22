package com.progra3.proyecto.service;

import com.progra3.proyecto.entity.Restaurante;
import java.util.List;

public interface IRestauranteService {
	public List<Restaurante> findAll();
	public Restaurante findById(Long id);
	public boolean exists(Long id);
	public Restaurante save(Restaurante restaurante);
	public void delete(Long id);
}


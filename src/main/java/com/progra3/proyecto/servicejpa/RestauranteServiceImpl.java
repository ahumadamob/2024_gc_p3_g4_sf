package com.progra3.proyecto.servicejpa;

import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.repository.RestauranteRepository;
import com.progra3.proyecto.service.IRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RestauranteServiceImpl implements IRestauranteService {

	@Autowired
	private RestauranteRepository repo;
	
	@Override
	public List<Restaurante> getAll() {
		return repo.findAll();
	}

	@Override
	public Restaurante getById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public boolean exists(Long id) {
		return id == null ? false : repo.existsById(id);
	}

	@Override
	public Restaurante save(Restaurante restaurante) {
		return repo.save(restaurante);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}
	@Override
    public List<Restaurante> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
        }
	}
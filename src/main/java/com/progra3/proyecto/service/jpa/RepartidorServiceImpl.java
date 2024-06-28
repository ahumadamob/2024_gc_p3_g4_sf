package com.progra3.proyecto.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.repository.RepartidorRepository;
import com.progra3.proyecto.service.IRepartidorService;

@Service
public class RepartidorServiceImpl implements IRepartidorService {
	
	@Autowired
	RepartidorRepository repo;

	@Override
	public List<Repartidor> getAll() {
		return repo.findAll();
	}

	@Override
	public Repartidor getById(Integer id) {
		Optional<Repartidor> optional = repo.findById(id);
		return optional.orElse(null);
	}

	@Override
	public Repartidor save(Repartidor repartidor) {
		return repo.save(repartidor);
	}

	@Override
	public void delete(Integer id) {
		Optional<Repartidor> optional = repo.findById(id);
		if (optional.isPresent()) {
			repo.deleteById(id);
		}
	}

	@Override
	public boolean exists(Integer id) {
		return (id == null) ? false : repo.existsById(id);
	}

}

package com.progra3.proyecto.servicejpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progra3.proyecto.entity.Vehiculo;
import com.progra3.proyecto.repository.VehiculoRepository;
import com.progra3.proyecto.service.IVehiculoService;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Override
    public List<Vehiculo> getAll() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo getById(Long id) {
        Optional<Vehiculo> restaurante = vehiculoRepository.findById(id);
        return restaurante.orElse(null); 
    }

	@Override
	public Vehiculo save(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public void delete(Long id) {
		vehiculoRepository.deleteById(id);
	}
	
	@Override
	public boolean exists(Long id) {
		return id == null ? false : vehiculoRepository.existsById(id);
	}

}


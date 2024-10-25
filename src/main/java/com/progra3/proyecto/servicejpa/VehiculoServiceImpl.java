package com.progra3.proyecto.servicejpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progra3.proyecto.entity.Restaurante;
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
    public Vehiculo getById(Integer id) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        return vehiculo.orElse(null); 
    }

	@Override
	public Vehiculo save(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public void delete(Integer id) {
		vehiculoRepository.deleteById(id);
	}
	
	@Override
	public boolean exists(Integer id) {
	    return id == null ? false : vehiculoRepository.existsById(id);
	}

	@Override
	public List<Vehiculo> findByTipo(String tipo) {
		return vehiculoRepository.findByTipo(tipo);
	}
	
	@Override
	public Restaurante getRestauranteByVehiculoId(Integer id) {
	    Vehiculo vehiculo = vehiculoRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Vehículo no encontrado para el ID: " + id));
	    
	    Restaurante restaurante = vehiculo.getRestaurante();
	    if (restaurante == null) {
	        throw new RuntimeException("Restaurante no encontrado para el vehículo ID: " + id);
	    }
	    return restaurante;
	}

}


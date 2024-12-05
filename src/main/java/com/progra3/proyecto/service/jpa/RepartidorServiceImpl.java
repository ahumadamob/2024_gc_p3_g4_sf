package com.progra3.proyecto.service.jpa;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progra3.proyecto.exceptions.EdadMinimaException;
import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.entity.Vehiculo;
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
    public List<Repartidor> buscarRepartidor(String nombre) {
        return repo.findByNombre(nombre);
    }
	@Override
	public Repartidor getById(Long id) {
		Optional<Repartidor> optional = repo.findById(id);
		return optional.orElse(null);
	}

	@Override
    public Repartidor save(Repartidor repartidor) throws EdadMinimaException {
        validarEdadMinima(repartidor.getFechaNacimiento());
        repartidor.marcarComoInactivoSiInactividadProlongada();
        return repo.save(repartidor);
    }

	@Override
	public void delete(Long id) {
		Optional<Repartidor> optional = repo.findById(id);
		if (optional.isPresent()) {
			repo.deleteById(id);
		}
	}

	@Override
	public boolean exists(Long id) {
		return (id == null) ? false : repo.existsById(id);
	}

	@Override
	public boolean exists(String repartidor) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Vehiculo> obtenerVehiculosPorRepartidorId(Long repartidorId) {
	    Optional<Vehiculo> vehiculo = repo.findVehiculoByRepartidorId(repartidorId);
	    return vehiculo.map(Collections::singletonList) 
	                  .orElse(Collections.emptyList());  
	}

	
	    @Override
	    public List<Repartidor> buscarPorRestaurante(Long restauranteId) {
	        return repo.findByRestauranteId(restauranteId);
	    }


	    public Repartidor saveRepartidor(Repartidor repartidor) throws EdadMinimaException {
	        this.validarEdadMinima(repartidor.getFechaNacimiento());
	        return repo.save(repartidor);
	    }

	    private void validarEdadMinima(LocalDate fechaNacimiento) throws EdadMinimaException {
	        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
	        if (edad < 18) {
	            throw new EdadMinimaException("El repartidor debe tener al menos 18 años.");
	        }
	    }

		@Override
		public int calcularAntiguedad(LocalDate fechaContratacion) {
			// TODO Auto-generated method stub
			return 0;
		}

}

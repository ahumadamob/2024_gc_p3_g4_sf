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
import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.entity.Vehiculo;
import com.progra3.proyecto.repository.RepartidorRepository;
import com.progra3.proyecto.repository.VehiculoRepository;
import com.progra3.proyecto.service.IRepartidorService;

@Service
public class RepartidorServiceImpl implements IRepartidorService {

    @Autowired
    private RepartidorRepository repo;

    @Autowired
    private VehiculoRepository vehiculoRepo;

    @Override
    public List<Repartidor> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Repartidor> buscarRepartidor(String nombre) {
        return repo.findByNombre(nombre);
    }

    @Override
    public List<Repartidor> buscarPorRestaurante(Long restauranteId) {
        return repo.findByRestauranteId(restauranteId);
    }

    @Override
    public Repartidor getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return repo.existsById(id);
    }
        @Override
        public boolean isVehiculoDisponible(Vehiculo vehiculo) {
            return vehiculo != null && vehiculo.isDisponible();
        }

        @Override
        public boolean isAntiguedadValida(LocalDate fechaContratacion) {
            return Period.between(fechaContratacion, LocalDate.now()).getYears() >= 1;
        }

        @Override
        public Repartidor save(Repartidor repartidor) {
            if (repartidor.getEntregasCompletadas() > 10) {
                repartidor.setActivo(true);
            } else {
                repartidor.setActivo(false);
            }
            
            return repo.save(repartidor);
        }

        @Override
        public List<Repartidor> findByRestauranteId(Long restauranteId) {
            return repo.findByRestauranteId(restauranteId);
        }

		@Override
		public boolean existsById(Long id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean exists(String repartidor) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public List<Vehiculo> obtenerVehiculosPorRepartidorId(Long repartidorId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int calcularAntiguedad(LocalDate fechaContratacion) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Repartidor saveEntregasRepartidor(Repartidor repartidor)
				throws EdadMinimaException, VehiculoNoDisponibleException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Repartidor saveRepartidorIMP(Repartidor repartidor)
				throws EdadMinimaException, VehiculoNoDisponibleException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Repartidor saveRepartidor(Repartidor repartidor)
				throws EdadMinimaException, VehiculoNoDisponibleException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean validarAntiguedad(LocalDate fechaContratacion) {
			// TODO Auto-generated method stub
			return false;
		}
    }
package com.progra3.proyecto.servicejpa;

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.repository.RepartidorRepository;
import com.progra3.proyecto.repository.RestauranteRepository;
import com.progra3.proyecto.service.IRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RestauranteServiceImpl implements IRestauranteService {

	@Autowired
    private RepartidorRepository repartidorRepo;
	@Autowired
	private RestauranteRepository repo;
	
    @Autowired
    private RepartidorRepository repo2;
	
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
    
    @Override
    public Restaurante asignarRepartidor(Long restauranteId, Long repartidorId) throws Exception {
        Restaurante restaurante = repo.findById(restauranteId)
            .orElseThrow(() -> new Exception("Restaurante no encontrado"));
        
        Repartidor repartidor = repo2.findById(repartidorId)
            .orElseThrow(() -> new Exception("Repartidor no encontrado"));

        repartidor.setRestaurante(restaurante); // establece la relación
        restaurante.getRepartidores().add(repartidor); // agrega el repartidor al restaurante
        
        repo2.save(repartidor); // guarda el repartidor en la base de datos
        return repo.save(restaurante); // guarda el restaurante actualizado
}

	@Override
	public void desvincularRepartidores(Long id) {
		// TODO Auto-generated method stub
		
	 @Override
	    public List<Repartidor> getRepartidoresPorRestaurante(Long restauranteId) {
	        return repartidorRepo.findByRestauranteId(restauranteId);
	    }
	}

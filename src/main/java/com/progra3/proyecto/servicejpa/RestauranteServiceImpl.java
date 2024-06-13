package com.progra3.proyecto.servicejpa;

import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.repository.RestauranteRepository;
import com.progra3.proyecto.service.IRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteServiceImpl implements IRestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> getAll() {
        return restauranteRepository.findAll();
    }

    @Override
    public Restaurante getById(Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        return restaurante.orElse(null); 
    }

    @Override
    public Restaurante save(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Override
    public void delete(Long id) {
        restauranteRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return restauranteRepository.existsById(id);
    }
}
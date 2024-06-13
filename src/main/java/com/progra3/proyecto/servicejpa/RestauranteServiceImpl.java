package com.progra3.proyecto.servicejpa;

import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.repository.RestauranteRepository;
import com.progra3.proyecto.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteServiceImpl implements RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> getAllRestaurantes() {
        return restauranteRepository.findAll();
    }

    @Override
    public Optional<Restaurante> getRestauranteById(Long id) {
        return restauranteRepository.findById(id);
    }

    @Override
    public Restaurante saveRestaurante(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Override
    public void deleteRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }
}

package com.progra3.proyecto.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progra3.proyecto.entity.Producto;
import com.progra3.proyecto.repository.ProductoRepository;
import com.progra3.proyecto.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private ProductoRepository repo;
	
	@Override
	public List<Producto> getAll() {
		return repo.findAll();
	}

	@Override
	public Producto getById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public boolean exists(Long id) {
		return id == null ? false : repo.existsById(id);
	}

	@Override
	public Producto save(Producto producto) {
		return repo.save(producto);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	@Override
    public List<Producto> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
     }

}
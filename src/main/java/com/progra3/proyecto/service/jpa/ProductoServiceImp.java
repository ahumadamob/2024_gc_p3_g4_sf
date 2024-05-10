package com.progra3.proyecto.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.progra3.proyecto.entity.Producto;
import com.progra3.proyecto.repository.ProductoRepository;
import com.progra3.proyecto.service.IProductoService;


@Service
public class ProductoServiceImp implements IProductoService {

	@Autowired
	ProductoRepository repo;
	
	
	@Override
	public List<Producto> buscarTodos() {
		return repo.findAll();
	}

	
	@Override
	public Producto buscardPorId(Long id) {
		Optional<Producto> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
		// return optional.orElse(null);
	}

	
	@Override
	public Producto guardar(Producto producto) {
		return repo.save(producto);
	}

	
	@Override
	public String eliminar(Long id) {
		Optional<Producto> optional = repo.findById(id);
		if (optional.isPresent()) {
			repo.deleteById(id);
			return "producto ELIMINADO ...";
		} else {
			return "el Id solicitado NO EXISTE ...";
		}
			
	}

	
	@Override
	public boolean existe(Long id) {
		/*
		if (id == null) {
			return false;
		} else {
			return repo.existsById(id);
		}
		*/
		return (id == null) ? false : repo.existsById(id);
	}
	

}

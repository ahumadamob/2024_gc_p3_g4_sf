package com.progra3.proyecto.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progra3.proyecto.entity.Pedido;
import com.progra3.proyecto.repository.PedidoRepository;
import com.progra3.proyecto.service.IPedidoService;

@Service
public class PedidoServiceImplJpa implements IPedidoService{
	
	@Autowired
	private PedidoRepository repo;
	
	@Override
	public List<Pedido> buscarPorCliente(String cliente) {
        return repo.findByCliente(cliente);
    }
	@Override
	public List<Pedido> getAll() {
		return repo.findAll();
	}

	@Override
	public Pedido getById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Pedido save(Pedido pedido) {
		return repo.save(pedido);
	}

	@Override
	public void delete(Long id) {
	repo.deleteById(id);		
	}


	@Override
	public boolean exists(Long id) {
		if(id == null) {
			return false;
		}else {
			return repo.existsById(id);
		}
	}
	@Override
	public boolean exists(String cliente) {
		// TODO Auto-generated method stub
		return false;
	}
	}



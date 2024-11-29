package com.progra3.proyecto.service.jpa;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progra3.proyecto.entity.Pedido;
import com.progra3.proyecto.entity.Producto;
import com.progra3.proyecto.repository.PedidoRepository;
import com.progra3.proyecto.repository.ProductoRepository;
import com.progra3.proyecto.repository.RestauranteRepository;
import com.progra3.proyecto.service.IPedidoService;

@Service
public class PedidoServiceImplJpa implements IPedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private RestauranteRepository restauranteRepo;

    @Autowired
    private ProductoRepository productoRepo;

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
        // Validar que el restaurante esté habilitado
        if (!restauranteRepo.existsByIdAndEstado(pedido.getRestaurante().getId(), "habilitado")) {
            throw new IllegalArgumentException("El restaurante no está habilitado.");
        }

        // Validar que el pedido tenga al menos un producto
        if (pedido.getProductos() == null || pedido.getProductos().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe incluir al menos un producto.");
        }

        // Validar que todos los productos existen
        Set<Long> productoIds = pedido.getProductos().stream()
                                      .map(Producto::getId)
                                      .collect(Collectors.toSet());

        List<Producto> productosValidos = productoRepo.findAllById(productoIds);
        if (productosValidos.size() != productoIds.size()) {
            throw new IllegalArgumentException("Uno o más productos no son válidos.");
        }

        // Asociar productos validados al pedido
        pedido.setProductos(Set.copyOf(productosValidos));

        // Guardar y devolver el pedido
        return repo.save(pedido);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return id != null && repo.existsById(id);
    }

    @Override
    public boolean exists(String cliente) {
        // Método sin implementación (no solicitado)
        return false;
    }
    @Override
    public Pedido createNuevoPedido(Pedido pedido) {
        // Validar que el restaurante esté habilitado
        if (!restauranteRepo.existsByIdAndEstado(pedido.getRestaurante().getId(), "Habilitado")) {
            throw new IllegalArgumentException("El restaurante no está habilitado.");
        }

        // Validar que el pedido tenga al menos un producto
        if (pedido.getProductos() == null || pedido.getProductos().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe incluir al menos un producto.");
        }

        // Guardar y devolver el pedido
        return repo.save(pedido);
    }

}




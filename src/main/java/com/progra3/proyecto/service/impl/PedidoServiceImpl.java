package com.progra3.proyecto.service.impl;



import com.progra3.proyecto.entity.Pedido;
import com.progra3.proyecto.repository.PedidoRepository;
import com.progra3.proyecto.service.IPedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements IPedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void cancelarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if ("cancelado".equalsIgnoreCase(pedido.getEstado())) {
            throw new RuntimeException("El pedido ya está cancelado");
        }

        pedido.setEstado("cancelado");
        pedidoRepository.save(pedido);
    }
}

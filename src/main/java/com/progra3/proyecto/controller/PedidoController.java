package com.progra3.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.progra3.proyecto.entity.Pedido;
import com.progra3.proyecto.service.IPedidoService;

@Controller
@RequestMapping (path="/api/pedido")
public class PedidoController {

	@Autowired
    private IPedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllVehiculos() {
        List<Pedido> pedidos = pedidoService.getAll();
        if (pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable("id") Long id) {
        Pedido pedido = pedidoService.getById(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        if (pedidoService.exists(pedido.getId())) {
            return ResponseEntity.badRequest().body(null);
        }
        Pedido savedPedido = pedidoService.save(pedido);
        return ResponseEntity.ok(savedPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
        if (!pedidoService.exists(id)) {
            return ResponseEntity.badRequest().body(null);
        }
        pedido.setId(id);
        Pedido updatedPedido = pedidoService.save(pedido);
        return ResponseEntity.ok(updatedPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable("id") Long id) {
        if (!pedidoService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
	
}

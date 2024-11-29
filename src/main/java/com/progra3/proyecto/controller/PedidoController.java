package com.progra3.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.progra3.proyecto.entity.Pedido;
import com.progra3.proyecto.service.IPedidoService;
import com.progra3.proyecto.util.APIResponse;
import com.progra3.proyecto.util.ResponseUtil;

import jakarta.validation.ConstraintViolationException;

@Controller
@RequestMapping(path = "/api/pedido")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Pedido>>> getAllPedido() {
        List<Pedido> pedido = pedidoService.getAll();
        return pedido.isEmpty() ? 
                ResponseUtil.notFound("No se encontraron pedidos") :
                ResponseUtil.success(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Pedido>> getPedidoById(@PathVariable("id") Long id) {
        return pedidoService.exists(id) ? 
                ResponseUtil.success(pedidoService.getById(id)) :
                ResponseUtil.notFound("No se encontró el pedido con id " + id);
    }

    @GetMapping("/cliente/{cliente}")
    public ResponseEntity<APIResponse<Object>> getPedidosPorCliente(@PathVariable String cliente) {
        List<Pedido> pedido = pedidoService.buscarPorCliente(cliente);
        return pedido.isEmpty() ? 
                ResponseUtil.notFound("No se encontraron pedidos") :
                ResponseUtil.success(pedido);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<APIResponse<Pedido>> createPedido(@RequestBody Pedido pedido) {
        // Llamada al servicio sin try-catch, ahora gestionado por el @ExceptionHandler
        Pedido nuevoPedido = pedidoService.createNuevoPedido(pedido);
        return ResponseUtil.success(nuevoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Pedido>> updatePedido(@RequestBody Pedido pedido) {
        return pedidoService.exists(pedido.getId()) ? 
                ResponseUtil.success(pedidoService.save(pedido)) :
                ResponseUtil.badRequest("No existe un pedido con id " + pedido.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deletePedido(@PathVariable("id") Long id) {
        if (pedidoService.exists(id)) {
            pedidoService.delete(id);
            return ResponseUtil.successDeleted("Se eliminó el pedido con el id " + id);
        } else {
            return ResponseUtil.badRequest("No se encontró el pedido con el id " + id);
        }
    }

    // Manejo de excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Pedido>> handleException(Exception ex) {
        return ResponseUtil.badRequest(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Pedido>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }
}

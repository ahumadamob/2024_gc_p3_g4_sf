package com.progra3.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progra3.proyecto.entity.Producto;
import com.progra3.proyecto.service.IProductoService;
import com.progra3.proyecto.util.APIResponse;
import com.progra3.proyecto.util.ResponseUtil;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping(path="/producto")
public class ProductoController {
    
    @Autowired
    private IProductoService productoService;
    
    @GetMapping
    public ResponseEntity<APIResponse<List<Producto>>> getAllProducts() {
        List<Producto> productos = productoService.getAll();
        return productos.isEmpty() ? 
                ResponseUtil.notFound("No se encontraron productos") : 
                ResponseUtil.success(productos);        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Producto>> getProductById(@PathVariable("id") Long id){
        Producto producto = productoService.getById(id);
        return producto != null ? 
                ResponseUtil.success(producto) : 
                ResponseUtil.notFound("No se encontró el producto con id {0}");
    }    
    
    @PostMapping
    public ResponseEntity<APIResponse<Producto>> createProducto(@RequestBody Producto producto){
        // Validar existencia y stock antes de proceder con el guardado
        try {
            producto = productoService.validarYAsignarAtributos(producto);
        } catch (IllegalArgumentException e) {
            return ResponseUtil.badRequest(e.getMessage());  // Error en la validación de stock o atributos
        }

        // Si pasa las validaciones, guardar el producto
        return ResponseUtil.success(productoService.save(producto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Producto>> updateProduct(@PathVariable("id") Long id, @RequestBody Producto producto){
        if (!productoService.exists(id)) {
            return ResponseUtil.badRequest("No existe un producto con id {0}");
        }
        
        // Asignar atributos y validar antes de actualizar
        try {
            producto = productoService.validarYAsignarAtributos(producto);
        } catch (IllegalArgumentException e) {
            return ResponseUtil.badRequest(e.getMessage());
        }

        // Actualizar producto
        return ResponseUtil.success(productoService.save(producto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Producto>> deleteProducto(@PathVariable("id") Long id){
        if (productoService.exists(id)) {
            productoService.delete(id);
            return ResponseUtil.successDeleted("Se eliminó el producto con id {0}");
        } else {
            return ResponseUtil.badRequest("No se encontró el producto con id {0}");
        }        
    }
    
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<APIResponse<Object>> getProductByName(@PathVariable String nombre) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return productos.isEmpty() ? 
                ResponseUtil.notFound("No se encontraron productos con el nombre {0}") :
                ResponseUtil.success(productos);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Producto>> handleException(Exception ex) {        
        return ResponseUtil.badRequest(ex.getMessage());
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Producto>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }
}

package com.progra3.proyecto.controller;

import java.util.ArrayList;
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
	public ResponseEntity<APIResponse<List<Producto>>> getAllProjects() {
		List<Producto> producto = productoService.getAll();
		return 	producto.isEmpty()? ResponseUtil.notFound("No se encontraron proyectos") :
				ResponseUtil.success(producto);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Producto>> getProjectById(@PathVariable("id") Long id){
		return 	productoService.exists(id)? ResponseUtil.success(productoService.getById(id)):
				ResponseUtil.notFound("No se encontró el proyecto con id {0}", id);
	}	
	
	@PostMapping
	public ResponseEntity<APIResponse<Producto>> createProject(@RequestBody Producto producto){
		return 	productoService.exists(producto.getId())? ResponseUtil.badRequest("Ya existe un proyecto con id {0}", producto.getId()):
				ResponseUtil.success(productoService.save(producto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<APIResponse<Producto>> updateProject(@RequestBody Producto producto){
		return 	productoService.exists(producto.getId())? ResponseUtil.success(producto):
				ResponseUtil.badRequest("No existe un projecto con id {0}", producto.getId());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Producto>> deleteProducto(@PathVariable("id") Long id){
		if(productoService.exists(id)) {
			productoService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó el proyecto con el id {0}", id);
		}else {
			return ResponseUtil.badRequest("No se encontró el proyecto con el id {0}", id);
		}		
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
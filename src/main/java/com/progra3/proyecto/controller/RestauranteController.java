package com.progra3.proyecto.controller;

import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.service.IRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progra3.proyecto.util.APIResponse;
import com.progra3.proyecto.util.ResponseUtil;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping(path="/restaurante")
public class RestauranteController {
	
	@Autowired
	private IRestauranteService restauranteService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Restaurante>>> getAllRestaurante() {
		List<Restaurante> restaurante = restauranteService.getAll();
		return 	restaurante.isEmpty()? ResponseUtil.notFound("No se encontraron restaurantes") :
				ResponseUtil.success(restaurante);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Restaurante>> getRestauranteById(@PathVariable("id") Long id){
		return 	restauranteService.exists(id)? ResponseUtil.success(restauranteService.getById(id)):
				ResponseUtil.notFound("No se encontró el restaurante con id {0}", id);
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Restaurante>> createRestaurante(@RequestBody Restaurante restaurante){
		return 	restauranteService.exists(restaurante.getId())? ResponseUtil.badRequest("Ya existe un restaurante con id {0}", restaurante.getId()):
				ResponseUtil.success(restauranteService.save(restaurante));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<APIResponse<Restaurante>> updateRestaurante(@RequestBody Restaurante restaurante){
		return 	restauranteService.exists(restaurante.getId())? ResponseUtil.success(restaurante):
				ResponseUtil.badRequest("No existe un restaurante con id {0}", restaurante.getId());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Restaurante>> deleteRestaurante(@PathVariable("id") Long id){
		if(restauranteService.exists(id)) {
			restauranteService.delete(id);
			return ResponseUtil.successDeleted("Se eliminó el restaurante con el id {0}", id);
		}else {
			return ResponseUtil.badRequest("No se encontró el restaurante con el id {0}", id);
		}		
	}
	@ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Restaurante>> handleException(Exception ex) {    	
    	return ResponseUtil.badRequest(ex.getMessage());
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Restaurante>> handleConstraintViolationException(ConstraintViolationException ex) {
    	return ResponseUtil.handleConstraintException(ex);
    }
	
}

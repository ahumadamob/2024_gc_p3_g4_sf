package com.progra3.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.entity.Restaurante;
import com.progra3.proyecto.entity.Vehiculo;
import com.progra3.proyecto.service.IVehiculoService;
import com.progra3.proyecto.util.APIResponse;
import com.progra3.proyecto.util.ResponseUtil;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

@RestController
@RequestMapping("/api/v1/project/vehiculo")
public class VehiculoController {

	@Autowired
	private IVehiculoService vehiculoService;

	/*
	 * @GetMapping public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
	 * List<Vehiculo> vehiculos = vehiculoService.getAll(); if (vehiculos.isEmpty())
	 * { return ResponseEntity.notFound().build(); } return
	 * ResponseEntity.ok(vehiculos); }
	 */

	@GetMapping
	public ResponseEntity<APIResponse<List<Vehiculo>>> getAllVehiculos() {
		List<Vehiculo> vehiculos = vehiculoService.getAll();
		return vehiculos.isEmpty() ? ResponseUtil.notFound("NO hay VEHICULOS en la bbdd ...")
				: ResponseUtil.success(vehiculos);
	}

	// ======================================================================================================================

	/*
	 * @GetMapping("/{id}") public ResponseEntity<Vehiculo>
	 * getVehiculoById(@PathVariable("id") Integer id) { Vehiculo vehiculo =
	 * vehiculoService.getById(id); if (vehiculo == null) { return
	 * ResponseEntity.notFound().build(); } return ResponseEntity.ok(vehiculo); }
	 */

	@GetMapping("{id}")
	public ResponseEntity<APIResponse<Vehiculo>> getVehiculoById(@PathVariable("id") Long id) {
		return vehiculoService.exists(id) ? ResponseUtil.success(vehiculoService.getById(id))
				: ResponseUtil.notFound("NO hay un VEHICULO con ese ID ...");
	}

	// ======================================================================================================================

	@GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Vehiculo>> findByTipo(@PathVariable String tipo) {
    	List<Vehiculo> vehiculos = vehiculoService.findByTipo(tipo);
    	if (vehiculos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculos);
    }
	
	
	// ======================================================================================================================

	/*
	 * @PostMapping public ResponseEntity<Vehiculo> createVehiculo(@RequestBody
	 * Vehiculo vehiculo) { if (vehiculoService.exists(vehiculo.getId())) { return
	 * ResponseEntity.badRequest().body(null); } Vehiculo savedVehiculo =
	 * vehiculoService.save(vehiculo); return ResponseEntity.ok(savedVehiculo); }
	 */

	@PostMapping
	public ResponseEntity<APIResponse<Vehiculo>> createVehiculo(@Valid @RequestBody Vehiculo vehiculo,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseUtil.badRequest("ERRORES de VALIDACION");
		}
		System.out.println(vehiculo.getId() + " - " + vehiculo.getNombre() + " - " + vehiculo.getTipo());
		return vehiculoService.exists(vehiculo.getId()) ? ResponseUtil.badRequest("ya EXISTE un VEHICULO con ese ID")
				: ResponseUtil.success(vehiculoService.save(vehiculo));
	}

	// ======================================================================================================================

	/*
	 * @PutMapping("/{id}") public ResponseEntity<Vehiculo>
	 * updateVehiculo(@PathVariable("id") Integer id, @RequestBody Vehiculo
	 * vehiculo) { if (!vehiculoService.exists(id)) { return
	 * ResponseEntity.badRequest().body(null); } vehiculo.setId(id); Vehiculo
	 * updatedVehiculo = vehiculoService.save(vehiculo); return
	 * ResponseEntity.ok(updatedVehiculo); }
	 */

	@PutMapping
	public ResponseEntity<APIResponse<Vehiculo>> updateVehiculo(@RequestBody Vehiculo vehiculo) {
		return vehiculoService.exists(vehiculo.getId()) ? ResponseUtil.success(vehiculoService.save(vehiculo))
				: ResponseUtil.badRequest("NO hay ningun VEHICULO con ese ID");
	}

	// ======================================================================================================================

	/*
	 * @DeleteMapping("/{id}") public ResponseEntity<Void>
	 * deleteVehiculo(@PathVariable("id") Integer id) { if
	 * (!vehiculoService.exists(id)) { return ResponseEntity.notFound().build(); }
	 * vehiculoService.delete(id); return ResponseEntity.noContent().build(); }
	 */

	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Vehiculo>> deleteVehiculo(@PathVariable("id") Long id) {
		if (vehiculoService.exists(id)) {
			vehiculoService.delete(id);
			return ResponseUtil.successDeleted("vehiculo ELIMINADO");
		} else {
			return ResponseUtil.badRequest("NO hay ningun VEHICULO con ese ID");
		}
	}
	
	
	
	@GetMapping("/{id}/restaurante")
	public ResponseEntity<APIResponse<Restaurante>> getRestauranteByVehiculoId(@PathVariable("id") Integer id) {
	    
	    if (!vehiculoService.exists(id)) {
	        return ResponseUtil.notFound("Vehículo no encontrado para el ID: " + id);
	    }
	   
	    Restaurante restaurante = vehiculoService.getById(id).getRestaurante();

	    if (restaurante == null) {
	        return ResponseUtil.notFound("Restaurante no encontrado para el vehículo ID: " + id);
	    }
	    
	    return ResponseUtil.success(restaurante);
	}

	// ===================================================================================================================
	
	
	@ExceptionHandler(Exception.class)
	//manejo de excepciones de tipo Exception, que no es manejada por otro @ExceptionHandler
	public ResponseEntity<APIResponse<Vehiculo>> handleException (Exception ex) {
		return ResponseUtil.badRequest("EXCEPCION !!! => " + ex.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	//manejo de excepciones cuando hay problemas de validación en los datos 
	//(x ej.: un campo q no cumple con las restricciones definidas)
	public ResponseEntity<APIResponse<Vehiculo>> handleConstraintViolationException (ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
		
	
}

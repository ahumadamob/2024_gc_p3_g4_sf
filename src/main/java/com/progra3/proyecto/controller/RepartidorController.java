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

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.entity.Vehiculo;
import com.progra3.proyecto.exceptions.EdadMinimaException;
import com.progra3.proyecto.service.IRepartidorService;
import com.progra3.proyecto.util.APIResponse;
import com.progra3.proyecto.util.ResponseUtil;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/project/repartidor")
public class RepartidorController {

    @Autowired
    private IRepartidorService service;

    @GetMapping
    public ResponseEntity<APIResponse<List<Repartidor>>> getAll() {
        List<Repartidor> repartidores = service.getAll();
        return repartidores.isEmpty() ? 
            ResponseUtil.notFound("NO hay REPARTIDORES en la bbdd ...") : 
            ResponseUtil.success(repartidores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Repartidor>> getById(@PathVariable("id") Long id) {
        Repartidor repartidor = service.getById(id);
        if (repartidor != null) {
            return ResponseUtil.success(repartidor);
        } else {
            return ResponseUtil.notFound("NO hay un REPARTIDOR con ese ID ...");
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<APIResponse<Object>> getRepartidorNombre(@PathVariable String nombre) {
        List<Repartidor> repartidores = service.buscarRepartidor(nombre);
        return repartidores.isEmpty() ? 
            ResponseUtil.notFound("No se encontró al repartidor") : 
            ResponseUtil.success(repartidores);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Repartidor>> create(@RequestBody Repartidor repartidor) throws EdadMinimaException {
        if (service.exists(repartidor.getId())) {
            return ResponseUtil.badRequest("ya EXISTE un REPARTIDOR con ese ID");
        } else {
            Repartidor savedRepartidor = service.save(repartidor);
            return ResponseUtil.success(savedRepartidor);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Repartidor>> update(@PathVariable("id") Long id, @RequestBody Repartidor repartidor) throws EdadMinimaException {
        if (service.exists(id)) {
            repartidor.setId(id); 
            Repartidor updatedRepartidor = service.save(repartidor);
            return ResponseUtil.success(updatedRepartidor);
        } else {
            return ResponseUtil.badRequest("NO hay ningun REGISTRO con ese ID");
        }
    }

    @GetMapping("/{id}/vehiculos")
    public ResponseEntity<APIResponse<Object>> getVehiculosByRepartidorId(@PathVariable Long id) {
        List<Vehiculo> vehiculos = service.obtenerVehiculosPorRepartidorId(id);
        return vehiculos.isEmpty() ? 
            ResponseUtil.notFound("No se encontraron vehículos para el repartidor especificado") : 
            ResponseUtil.success(vehiculos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> delete(@PathVariable("id") Long id) {
        if (service.exists(id)) {
            service.delete(id);
            return ResponseUtil.successDeleted("Repartidor ELIMINADO");
        } else {
            return ResponseUtil.badRequest("NO hay ningun REGISTRO con ese ID");
        }
    }
    
    /*
    @PostMapping("/api/repartidores/nuevo")
    public ResponseEntity<APIResponse<Repartidor>> crearRepartidor(@RequestBody Repartidor repartidor) {
        
    	try {
            
            service.save(repartidor);
            
            int antiguedad = service.calcularAntiguedad(repartidor.getFechaContratacion());
            return ResponseUtil.success(repartidor, "Repartidor creado con éxito. " + antiguedad);
        } catch (EdadMinimaException e) {
            return ResponseUtil.badRequest(e.getMessage());
        }
    }
    */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Void>> handleException(Exception ex) {
        return ResponseUtil.badRequest(ex.getMessage());
    }
    
    
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Void>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }
    
	@ExceptionHandler(EdadMinimaException.class)
	//manejo de excepciones cuando hay problemas de validación en los datos 
	//(x ej.: un campo q no cumple con las restricciones definidas)
	public ResponseEntity<APIResponse<Vehiculo>> handleEdadMinimaException (EdadMinimaException ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}	    
}


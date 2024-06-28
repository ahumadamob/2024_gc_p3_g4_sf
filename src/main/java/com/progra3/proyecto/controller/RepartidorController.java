package com.progra3.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.service.IRepartidorService;
import com.progra3.proyecto.util.APIResponse;
import com.progra3.proyecto.util.ResponseUtil;

@RestController
@RequestMapping (path="/api/v1/project/repartidor")
public class RepartidorController {
	
	@Autowired
	private IRepartidorService service;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Repartidor>>> getAll() {
		List<Repartidor> repartidores = service.getAll();
		return repartidores.isEmpty() ? ResponseUtil.notFound("NO hay REPARTIDORES en la bbdd ...") : 
			                            ResponseUtil.success(repartidores);
	}
	
	@GetMapping ("{id}")
	public ResponseEntity<APIResponse<Repartidor>> getById(@PathVariable("id") Integer id) {
		return service.exists(id) ? ResponseUtil.success(service.getById(id)) :
			                        ResponseUtil.notFound("NO hay un REPARTIDOR con ese ID ...");
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Repartidor>> create(@RequestBody Repartidor repartidor) {
		System.out.println(repartidor.getId() + " - " + repartidor.getNombre() + " - " + repartidor.getVehiculo());
		return service.exists(repartidor.getId()) ? ResponseUtil.badRequest("ya EXISTE un REPARTIDOR con ese ID") :
			                                        ResponseUtil.success(service.save(repartidor));
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Repartidor>> update(@RequestBody Repartidor repartidor) {
		return service.exists(repartidor.getId()) ? ResponseUtil.success(service.save(repartidor)) :
			                                        ResponseUtil.badRequest("NO hay ningun REGISTRO con ese ID");
	}
	
	@DeleteMapping ("{id}")
	public ResponseEntity<APIResponse<Repartidor>> delete (@PathVariable ("id") Integer id) {
		if (service.exists(id)) {
			service.delete(id);
			return ResponseUtil.successDeleted("repartidor ELIMINADO", id);
		} else {
			return ResponseUtil.badRequest("NO hay ningun REGISTRO con ese ID");
		}
	}

}

package com.progra3.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.progra3.proyecto.entity.Repartidor;
import com.progra3.proyecto.entity.Vehiculo;
import com.progra3.proyecto.service.IVehiculoService;
import com.progra3.proyecto.util.APIResponse;
import com.progra3.proyecto.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project/vehiculo")
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    /*
    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.getAll();
        if (vehiculos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculos);
    }
    */
    
    @GetMapping
	public ResponseEntity<APIResponse<List<Vehiculo>>> getAllVehiculos() {
		List<Vehiculo> vehiculos = vehiculoService.getAll();
		return vehiculos.isEmpty() ? ResponseUtil.notFound("NO hay VEHICULOS en la bbdd ...") : 
			                         ResponseUtil.success(vehiculos);
	}
    
    
    // ======================================================================================================================
    

    /*
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable("id") Integer id) {
        Vehiculo vehiculo = vehiculoService.getById(id);
        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculo);
    }
    */
    
    @GetMapping ("{id}")
	public ResponseEntity<APIResponse<Vehiculo>> getVehiculoById(@PathVariable("id") Integer id) {
		return vehiculoService.exists(id) ? ResponseUtil.success(vehiculoService.getById(id)) :
			                                ResponseUtil.notFound("NO hay un VEHICULO con ese ID ...");
	}
    
    
    // ======================================================================================================================
    

    /*
    @PostMapping
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo) {
        if (vehiculoService.exists(vehiculo.getId())) {
            return ResponseEntity.badRequest().body(null);
        }
        Vehiculo savedVehiculo = vehiculoService.save(vehiculo);
        return ResponseEntity.ok(savedVehiculo);
    }
    */
    
    @PostMapping
	public ResponseEntity<APIResponse<Vehiculo>> createVehiculo(@RequestBody Vehiculo vehiculo) {
		System.out.println(vehiculo.getId() + " - " + vehiculo.getNombre() + " - " + vehiculo.getTipo());
		return vehiculoService.exists(vehiculo.getId()) ? ResponseUtil.badRequest("ya EXISTE un VEHICULO con ese ID") :
			                                              ResponseUtil.success(vehiculoService.save(vehiculo));
	}
    
    
    // ======================================================================================================================
    
    
    /*
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable("id") Integer id, @RequestBody Vehiculo vehiculo) {
        if (!vehiculoService.exists(id)) {
            return ResponseEntity.badRequest().body(null);
        }
        vehiculo.setId(id);
        Vehiculo updatedVehiculo = vehiculoService.save(vehiculo);
        return ResponseEntity.ok(updatedVehiculo);
    }
    */
    
    
    @PutMapping
	public ResponseEntity<APIResponse<Vehiculo>> updateVehiculo(@RequestBody Vehiculo vehiculo) {
		return vehiculoService.exists(vehiculo.getId()) ? ResponseUtil.success(vehiculoService.save(vehiculo)) :
			                                        ResponseUtil.badRequest("NO hay ningun VEHICULO con ese ID");
	}
    
    
    // ======================================================================================================================
    
    
    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable("id") Integer id) {
        if (!vehiculoService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        vehiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    */
    
    
    @DeleteMapping ("{id}")
	public ResponseEntity<APIResponse<Vehiculo>> deleteVehiculo (@PathVariable ("id") Integer id) {
		if (vehiculoService.exists(id)) {
			vehiculoService.delete(id);
			return ResponseUtil.successDeleted("vehiculo ELIMINADO");
		} else {
			return ResponseUtil.badRequest("NO hay ningun VEHICULO con ese ID");
		}
	}
    
    
    
}

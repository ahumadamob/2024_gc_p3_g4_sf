package com.progra3.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.progra3.proyecto.entity.Vehiculo;
import com.progra3.proyecto.service.IVehiculoService;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.getAll();
        if (vehiculos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable("id") Long id) {
        Vehiculo vehiculo = vehiculoService.getById(id);
        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculo);
    }

    @PostMapping
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo) {
        if (vehiculoService.exists(vehiculo.getId())) {
            return ResponseEntity.badRequest().body(null);
        }
        Vehiculo savedVehiculo = vehiculoService.save(vehiculo);
        return ResponseEntity.ok(savedVehiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable("id") Long id, @RequestBody Vehiculo vehiculo) {
        if (!vehiculoService.exists(id)) {
            return ResponseEntity.badRequest().body(null);
        }
        vehiculo.setId(id);
        Vehiculo updatedVehiculo = vehiculoService.save(vehiculo);
        return ResponseEntity.ok(updatedVehiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable("id") Long id) {
        if (!vehiculoService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        vehiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

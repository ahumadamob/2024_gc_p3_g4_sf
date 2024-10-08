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
import com.progra3.proyecto.service.IRepartidorService;
import com.progra3.proyecto.util.APIResponse;
import com.progra3.proyecto.util.ResponseUtil;

import jakarta.validation.ConstraintViolationException;

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
    public ResponseEntity<APIResponse<Repartidor>> getById(@PathVariable("id") Integer id) {
        if (service.exists(id)) {
            return ResponseUtil.success(service.getById(id));
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
    public ResponseEntity<APIResponse<Repartidor>> create(@RequestBody Repartidor repartidor) {
        System.out.println(repartidor.getId() + " - " + repartidor.getNombre() + " - " + repartidor.getVehiculo());
        if (service.exists(repartidor.getId())) {
            return ResponseUtil.badRequest("ya EXISTE un REPARTIDOR con ese ID");
        } else {
            Repartidor savedRepartidor = service.save(repartidor);
            return ResponseUtil.success(savedRepartidor);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Repartidor>> update(@PathVariable("id") Integer id, @RequestBody Repartidor repartidor) {
        if (service.exists(id)) {
            repartidor.setId(id); 
            Repartidor updatedRepartidor = service.save(repartidor);
            return ResponseUtil.success(updatedRepartidor);
        } else {
            return ResponseUtil.badRequest("NO hay ningun REGISTRO con ese ID");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> delete(@PathVariable("id") Integer id) {
        if (service.exists(id)) {
            service.delete(id);
            return ResponseUtil.successDeleted("Repartidor ELIMINADO");
        } else {
            return ResponseUtil.badRequest("NO hay ningun REGISTRO con ese ID");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Void>> handleException(Exception ex) {
        return ResponseUtil.badRequest(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Void>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }
}
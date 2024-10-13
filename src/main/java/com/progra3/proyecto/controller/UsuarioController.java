package com.progra3.proyecto.controller;

import com.progra3.proyecto.entity.Usuario;
import com.progra3.proyecto.service.IUsuarioService;
import com.progra3.proyecto.util.APIResponse;
import com.progra3.proyecto.util.ResponseUtil;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<APIResponse<List<Usuario>>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAll();
        return usuarios.isEmpty() ? 
            ResponseUtil.notFound("No se encontraron usuarios") : 
            ResponseUtil.success(usuarios);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Usuario>> getUsuarioById(@PathVariable Long id) {
        if (usuarioService.exists(id)) {
            Usuario usuario = usuarioService.getById(id);
            return ResponseUtil.success(usuario);
        } else {
            return ResponseUtil.notFound("No se encontró el usuario con id " + id);
        }
    }

    // Crear nuevo usuario
    @PostMapping
    public ResponseEntity<APIResponse<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        if (usuarioService.exists(usuario.getId())) {
            return ResponseUtil.badRequest("Ya existe un usuario con id " + usuario.getId());
        } else {
            Usuario savedUsuario = usuarioService.save(usuario);
            return ResponseUtil.success(savedUsuario);
        }
    }

    // Actualizar usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Usuario>> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        if (usuarioService.exists(id)) {
            usuario.setId(id);
            Usuario updatedUsuario = usuarioService.save(usuario);
            return ResponseUtil.success(updatedUsuario);
        } else {
            return ResponseUtil.notFound("No existe un usuario con id " + id);
        }
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUsuario(@PathVariable Long id) {
        if (usuarioService.exists(id)) {
            usuarioService.delete(id);
            return ResponseUtil.successDeleted("Se eliminó el usuario con el id " + id);
        } else {
            return ResponseUtil.notFound("No se encontró el usuario con el id " + id);
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

package com.progra3.proyecto.controller;


import com.progra3.proyecto.entity.Usuario;
import com.progra3.proyecto.service.IUsuarioService;
import com.progra3.proyecto.exception.UsuarioConPedidosPendientesException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<String> crearUsuario(@RequestBody @Valid Usuario usuario) {
        if (!isValidEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("El correo electrónico no tiene un formato válido");
        }
        try {
            usuarioService.crearUsuario(usuario);
            return ResponseEntity.ok("Usuario creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (UsuarioConPedidosPendientesException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
}

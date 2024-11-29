package com.progra3.proyecto.service.impl;


import com.progra3.proyecto.entity.Usuario;
import com.progra3.proyecto.repository.UsuarioRepository;
import com.progra3.proyecto.service.IUsuarioService;
import com.progra3.proyecto.exception.UsuarioConPedidosPendientesException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean tienePedidosPendientes = usuario.getPedidos().stream()
                .anyMatch(p -> "pendiente".equalsIgnoreCase(p.getEstado()));

        if (tienePedidosPendientes) {
            throw new UsuarioConPedidosPendientesException("No se puede eliminar el usuario porque tiene pedidos pendientes");
        }

        usuarioRepository.delete(usuario);
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}


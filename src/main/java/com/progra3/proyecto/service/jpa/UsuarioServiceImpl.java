package com.progra3.proyecto.service.jpa;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progra3.proyecto.entity.Usuario;
import com.progra3.proyecto.entity.Vehiculo;
import com.progra3.proyecto.repository.UsuarioRepository;
import com.progra3.proyecto.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return usuarioRepository.existsById(id);
    }
	@Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
     }
	
	 @Override
	    public List<Vehiculo> getVehiculosByUsuarioId(Long id) {
	        Usuario usuario = usuarioRepository.findById(id).orElse(null);
	        return (usuario != null) ? usuario.getVehiculos() : Collections.emptyList();
	    }
}

package com.progra3.proyecto.service;

import com.progra3.proyecto.entity.Usuario;
import java.util.List;

public interface IUsuarioService {
    List<Usuario> getAll();
    Usuario getById(Long id);
    Usuario save(Usuario usuario);
    void delete(Long id);
    boolean exists(Long id);
}
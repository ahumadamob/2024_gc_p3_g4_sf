package com.progra3.proyecto.service;



import com.progra3.proyecto.entity.Usuario;

public interface IUsuarioService {

    void eliminarUsuario(Long id);

    void crearUsuario(Usuario usuario);
}

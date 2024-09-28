package com.progra3.proyecto.repository;

import com.progra3.proyecto.entity.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByNombre(String nombre);
}

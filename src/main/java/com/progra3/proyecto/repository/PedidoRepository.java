package com.progra3.proyecto.repository;

import com.progra3.proyecto.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import com.progra3.proyecto.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Aquí puedes agregar consultas personalizadas si lo necesitas
}

package com.progra3.proyecto.entity;

import org.hibernate.validator.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Vehiculo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "El tipo no puede ser nulo ni estar vacío")
    private String tipo;

    @NotEmpty(message = "El nombre no puede ser nulo ni estar vacío")
    private String nombre;

    private String color;

    @ManyToOne
    @JoinColumn(name = "usuario_id") 
    private Usuario usuario;

    @ManyToOne 
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}


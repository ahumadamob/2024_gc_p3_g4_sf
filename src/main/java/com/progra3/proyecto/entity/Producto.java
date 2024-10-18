package com.progra3.proyecto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Producto {

    // atributos
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String descripcion;

    @Min(value = 0, message = "El precio no puede ser negativo")
    private float precio;

    @NotBlank(message = "La categoría no puede estar vacía")
    @Size(min = 3, max = 50, message = "La categoría debe tener entre 3 y 50 caracteres")
    private String categoria;

    @NotBlank(message = "La disponibilidad no puede estar vacía")
    @Size(min = 3, max = 20, message = "La disponibilidad debe tener entre 3 y 20 caracteres")
    private String disponibilidad;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NotNull(message = "La categoría del producto no puede ser nula")
    private CategoriaProducto categoriaproducto;

    @ManyToOne
    @JoinColumn(name="restaurante_id")
    @NotNull(message = "El restaurante no puede ser nulo")
    private Restaurante restaurante;

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}

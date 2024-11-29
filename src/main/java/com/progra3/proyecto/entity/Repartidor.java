package com.progra3.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Repartidor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del repartidor es obligatorio")
    private String nombre;

    @NotNull(message = "El teléfono es obligatorio")
    @Positive(message = "El teléfono debe ser positivo")
    private int telefono;

    private String vehiculoAsignado;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @NotNull(message = "La fecha de contratación es obligatoria")
    @Past(message = "La fecha de contratación debe ser una fecha pasada")
    private LocalDate fechaContratacion;

    @Min(value = 0, message = "Las entregas completadas deben ser al menos 0")
    private int entregasCompletadas;

    private LocalDate ultimaEntrega;

    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JsonIgnore
    private Restaurante restaurante;

	@OneToMany(mappedBy = "repartidor")
    private List<Pedido> pedidos;

    public int calcularAntiguedad() {
        return Period.between(fechaContratacion, LocalDate.now()).getYears();
    }

    public void marcarComoInactivoSiInactividadProlongada() {
        if (ultimaEntrega != null && Period.between(ultimaEntrega, LocalDate.now()).getDays() > 30) {
            this.estado = "inactivo";
        }
    }
    
    public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public int getEntregasCompletadas() {
		return entregasCompletadas;
	}

	public void setEntregasCompletadas(int entregasCompletadas) {
		this.entregasCompletadas = entregasCompletadas;
	}

	public LocalDate getUltimaEntrega() {
		return ultimaEntrega;
	}

	public void setUltimaEntrega(LocalDate ultimaEntrega) {
		this.ultimaEntrega = ultimaEntrega;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

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

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getVehiculoAsignado() {
		return vehiculoAsignado;
	}

	public void setVehiculoAsignado(String vehiculoAsignado) {
		this.vehiculoAsignado = vehiculoAsignado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

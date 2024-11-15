package com.progra3.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Repartidor extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "El nombre del repartidor es obligatorio")
	private String nombre;
	@NotNull
	private int telefono;
	private String vehiculoAsignado;   // Descripción o identificador del vehículo asignado al repartidor.
	@NotBlank(message = "El estado es obligatorio")
	private String estado;             // (disponible, en camino, ocupado, etc.)
	private String vehiculoAsignado;   
	private String estado;
	

	@OneToOne
	@JoinColumn(name = "vehiculo_id")
	private Vehiculo vehiculo;

	@ManyToOne
	@JsonIgnore
	private Restaurante restaurante;
	
	
	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	@OneToOne
	@JoinColumn(name = "vehiculo_id")
	private Vehiculo vehiculo;

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
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

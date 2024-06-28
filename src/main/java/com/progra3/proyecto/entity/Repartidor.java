package com.progra3.proyecto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Repartidor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private int telefono;
	private String vehiculoAsignado;   // Descripción o identificador del vehículo asignado al repartidor.
	private String estado;             // (disponible, en camino, ocupado, etc.)

	@OneToOne
	@JoinColumn(name = "vehiculo_id")
	private Vehiculo vehiculo;

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

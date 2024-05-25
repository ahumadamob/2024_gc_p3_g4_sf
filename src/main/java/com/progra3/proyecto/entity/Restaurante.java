package com.progra3.proyecto.entity;

import jakarta.persistence.*;


@Entity
public class Restaurante {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String nombre;
		private String direccion;
		private String tipoCocina;
		private String horarioAtencion;
		private int atencion;
		private String estado;
		
		
		// Getters y Setters
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

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getTipoCocina() {
			return tipoCocina;
		}

		public void setTipoCocina(String tipoCocina) {
			this.tipoCocina = tipoCocina;
		}

		public String getHorarioAtencion() {
			return horarioAtencion;
		}

		public void setHorarioAtencion(String horarioAtencion) {
			this.horarioAtencion = horarioAtencion;
		}

		public int getAtencion() {
			return atencion;
		}

		public void setAtencion(int atencion) {
			this.atencion = atencion;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}
		
}

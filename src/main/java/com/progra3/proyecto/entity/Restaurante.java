package com.progra3.proyecto.entity;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class Restaurante extends BaseEntity{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String nombre;
		private String direccion;
		private String tipoCocina;
		private String horarioAtencion;
		private int atencion;
		private String estado;
		
		 @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
		    private List<Pedido> pedidos;
		 
	
		//Relación
		@OneToMany(mappedBy="restaurante")
		private List<Producto> productos;
		
		
		// Getters y Setters
		public Long getId() {
			return id;
		}

		public List<Producto> getProductos() {
			return productos;
		}

		public void setProductos(List<Producto> productos) {
			this.productos = productos;
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

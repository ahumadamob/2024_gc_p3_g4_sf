package com.progra3.proyecto.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Restaurante extends BaseEntity{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@NotBlank(message = "El nombre del restaurante es obligatorio")
		@Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
		
		private String nombre;
	    @NotBlank(message = "La dirección es obligatoria")
		private String direccion;
	    
	    @NotBlank(message = "El tipo de cocina es obligatorio")
		private String tipoCocina;
	    
	    @NotBlank(message = "El horario de atención es obligatorio")
		private String horarioAtencion;
	    @Min(value = 1, message = "La atención debe ser al menos 1")
	    @Max(value = 10, message = "La atención no puede ser mayor a 10")
		private int atencion;
		 @NotBlank(message = "El estado es obligatorio")
		private String estado;
		
		 @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
		    private List<Pedido> pedidos;
		 
		@OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Producto> productos;
		
		@OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Repartidor> repartidores;
		
		public void setRepartidores(List<Repartidor> repartidores) {
			this.repartidores = repartidores;
		}

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

		public List<Pedido> getPedidos() {
			return pedidos;
		}

		public void setPedidos(List<Pedido> pedidos) {
			this.pedidos = pedidos;
		}

		public List<Repartidor> getRepartidores() {
			return repartidores;
		}
	
		
}

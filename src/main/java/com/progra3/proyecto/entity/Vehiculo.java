package com.progra3.proyecto.entity;

import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotEmpty;

@Entity
public class Vehiculo extends BaseEntity{
	 
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)	
		private int id;
		
		@NotEmpty(message = "el TIPO NO puede ser NULO ni estar VACIO")
		private String tipo;
		
		@NotEmpty(message = "el TIPO NO puede ser NULO ni estar VACIO")
		private String nombre;		
		private String color;

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


		
		
		

}

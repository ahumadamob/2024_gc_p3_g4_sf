package com.progra3.proyecto.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

	@Entity
public class CategoriaProducto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String nombreProduct;
	public String descripcionProduct;
	
	 @OneToMany(mappedBy = "categoriaproducto", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Producto> productos;
	
		//GETTERS AND SETTERS//
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreProduct() {
		return nombreProduct;
	}
	public void setNombreProduct(String nombreProduct) {
		this.nombreProduct = nombreProduct;
	}
	public String getDescripcionProduct() {
		return descripcionProduct;
	}
	public void setDescripcionProduct(String descripcionProduct) {
		this.descripcionProduct = descripcionProduct;
	}

}

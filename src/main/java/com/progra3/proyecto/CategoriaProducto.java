package com.progra3.proyecto;

public class CategoriaProducto {
	public int idCatProducto;
	public String nombreProduct;
	public String descripcionProduct;
	
	
		//CONSTRUCTOR VACIO//
	
	public CategoriaProducto() {
		
	}


		//CONSTRUCTOR SOBRECARGADO//
	
	public CategoriaProducto(int idCatProducto, String nombreProduct, String descripcionProduct) {
		this.idCatProducto = idCatProducto;
		this.nombreProduct = nombreProduct;
		this.descripcionProduct = descripcionProduct;
	}


		//GETTERS AND SETTERS//
	
	public int getIdCatProducto() {
		return idCatProducto;
	}



	public void setIdCatProducto(int idCatProducto) {
		this.idCatProducto = idCatProducto;
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

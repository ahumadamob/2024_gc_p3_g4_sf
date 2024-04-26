package entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CategoriaProducto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idCatProducto;
	public String nombreProduct;
	public String descripcionProduct;
	
	
		//CONSTRUCTOR VACIO//
	
	public CategoriaProducto() {
		
	}


		//CONSTRUCTOR SOBRECARGADO//
	
	public CategoriaProducto(Long idCatProducto, String nombreProduct, String descripcionProduct) {
		this.idCatProducto = idCatProducto;
		this.nombreProduct = nombreProduct;
		this.descripcionProduct = descripcionProduct;
	}


		//GETTERS AND SETTERS//
	
	public Long getIdCatProducto() {
		return idCatProducto;
	}



	public void setIdCatProducto(Long idCatProducto) {
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

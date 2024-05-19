package com.progra3.proyecto.entity;
import jakarta.persistence.*;
@Entity
@Table
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idPedido;
	@Column
	private String productos;
	@Column
	private String estado;
	@Column
	private String direccionEntrega;
	@Column
	private String metodoPago;
	@Column
	private String cliente;
	@Column
	private String restaurante;
	
	//Constructors
	public Pedido() {
	}
	
	public Pedido(int idPedido, String productos, String estado, String direccionEntrega, String metodoPago,
			String cliente, String restaurante) {
		this.idPedido = idPedido;
		this.productos = productos;
		this.estado = estado;
		this.direccionEntrega = direccionEntrega;
		this.metodoPago = metodoPago;
		this.cliente = cliente;
		this.restaurante = restaurante;
	}
	
	//Getter and Setter
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public String getProductos() {
		return productos;
	}
	public void setProductos(String productos) {
		this.productos = productos;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDireccionEntrega() {
		return direccionEntrega;
	}
	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(String restaurante) {
		this.restaurante = restaurante;
	}
	
	
}

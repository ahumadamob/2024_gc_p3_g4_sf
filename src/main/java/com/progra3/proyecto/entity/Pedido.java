package com.progra3.proyecto.entity;
import jakarta.persistence.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;
    
    private String estado;
    private String direccionEntrega;
    private String metodoPago;
    private String cliente;
    private String restaurante;
	
	// atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPedido;
	private String productos;
	private String estado;
	private String direccionEntrega;
	private String metodoPago;
	
	//private String cliente;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;
	
	private String restaurante;

    @ManyToMany
    @JoinTable(
        name = "PedidoProducto",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private Set<Producto> productos;
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
	
	// getter & setter
	public int getIdPedido() {
		return idPedido;
	}


    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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

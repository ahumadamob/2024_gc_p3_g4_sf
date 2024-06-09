
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
	public String nombre;
	public String descripcion;
	
	 @OneToMany(mappedBy = "categoriaproducto", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Producto> productos;
	 
	//GETTERS AND SETTERS//

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}

package Entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table (name = "repartidor")
public class repartidor {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="telefono")
	private int telefono;
	
	@Column(name="vehiculoAsignado")
	private String vehiculoAsignado;
	
	@Column(name="estado")
	private String estado;

	public repartidor(Integer id, String nombre, int telefono, String vehiculoAsignado, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.vehiculoAsignado = vehiculoAsignado;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getVehiculoAsignado() {
		return vehiculoAsignado;
	}

	public void setVehiculoAsignado(String vehiculoAsignado) {
		this.vehiculoAsignado = vehiculoAsignado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}

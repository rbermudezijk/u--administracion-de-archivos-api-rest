package models;

//import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.ManyToMany;
//import javax.persistence.JoinTable;

@Entity
@Table(name="grupos")
public class Grupo {
	
	@Id
	@Column(name="id_grupo")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="id_llave")
	private int idLlave;
	@Column(name="nombre")
	private String nombre;
	@Column(name="fecha_insercion")
	private String fechaCreacion;
	@Column(name="fecha_actualizacion")
	private String fechaActualizacion;
	
	public Grupo() {}
	
	public Grupo(int id, int idLlave, String nombre, String fechaCreacion, String fechaActualizacion) {
		super();
		this.id = id;
		this.idLlave = idLlave;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdLlave() {
		return idLlave;
	}
	public void setIdLlave(int id_llave) {
		this.idLlave = id_llave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	
}

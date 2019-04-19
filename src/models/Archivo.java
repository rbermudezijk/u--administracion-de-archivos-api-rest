package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*https://stackoverflow.com/questions/4662582/make-hibernate-ignore-class-variables-that-are-not-mapped*/
import javax.persistence.Transient;

/* https://stackoverflow.com/questions/12505141/only-using-jsonignore-during-serialization-but-not-deserialization */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="archivos")
public class Archivo {
	
	/** Columns. */
	@Id
    @Column(name="id_archivo")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	@Column(name="id_llave")
	private int idLlave;
    @Column(name="nombre")
    private String nombre;
    @Column(name="extension")
    private String extension;
    @Column(name="tipo_mime")
    private String tipoMIME;
    
    @Transient
    @JsonProperty(access=Access.WRITE_ONLY)
    private String contenido;
    
    /** Constructors, getters and setters. */
    public Archivo() {}

    public Archivo(int id, int idLlave, String nombre, String extension, String tipoMIME) {
		this.id = id;
		this.idLlave = idLlave;
		this.nombre = nombre;
		this.extension = extension;
		this.tipoMIME = tipoMIME;
	}
    
    public Archivo(int id, int idLlave, String nombre, String extension, String tipoMIME, String contenido) {
		this(id, idLlave, nombre, extension, tipoMIME);
		this.contenido = contenido;
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

	public String getTipoMIME() {
		return tipoMIME;
	}

	public void setTipoMIME(String tipoMIME) {
		this.tipoMIME = tipoMIME;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getContenido() {
		return contenido;
	}
	
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	@JsonIgnore
	public String ruta(String contenedor) {
		return contenedor + this.id + "." + this.extension;
	}
	
	@Override
	public String toString() {
		return "ID = " + this.id + "\n" +
			   "Nombre = " + this.nombre + "\n" +
			   "Contenido = " + this.contenido + "\n";
	}
}

package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*https://stackoverflow.com/questions/4662582/make-hibernate-ignore-class-variables-that-are-not-mapped*/
import javax.persistence.Transient;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/* https://stackoverflow.com/questions/12505141/only-using-jsonignore-during-serialization-but-not-deserialization */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonIgnore;

import models.Carpeta;

@Entity
@Table(name="sa__archivos")
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
    @ManyToOne
    @JoinColumn(name="id_carpeta", nullable=false)
    private Carpeta carpeta;
    
    @Transient
    @JsonProperty(access = Access.WRITE_ONLY)
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

    public Carpeta getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(Carpeta carpeta) {
		this.carpeta = carpeta;
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
	
	public String ruta(String contenedor) {
		return contenedor + this.id + "." + this.extension;
	}
}

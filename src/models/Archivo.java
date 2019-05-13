package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*https://stackoverflow.com/questions/4662582/make-hibernate-ignore-class-variables-that-are-not-mapped*/
import javax.persistence.Transient;
/*import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;*/

/* https://stackoverflow.com/questions/12505141/only-using-jsonignore-during-serialization-but-not-deserialization */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonIgnore;

/*import models.Carpeta;
import models.Llave;*/

@Entity
@Table(name="sa__archivos")
public class Archivo {

	/** Columns. */
	@Id
    @Column(name="id_archivo")
    @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(access=Access.READ_ONLY)
    private int id;
	
	@JsonIgnore
	@Column(name="id_propietario")
	private int idPropietario;
	
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="extension")
    private String extension;
    
    @Column(name="tipo_mime")
    private String tipoMIME;

    @Column(name="comentario")
    private String comentario;
    
    public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/*@JsonProperty(access=Access.READ_ONLY)*/
    @Column(name="ubicacion")
    private String ubicacion = "/";
    
    @Column(name="__insercion", insertable = false)
    @JsonProperty(access=Access.READ_ONLY)
    private String fechaDeInsercion;

    /*@ManyToOne
    @JoinColumn(name="id_carpeta_padre", nullable=true)
    @JsonIgnore
    private Carpeta carpeta;*/

	/**
     * Se añade solo lectura del JSON porque el contenido se almacena
     * y proporciona de forma ajena a la base de datos. Sin embargo,
     * el contenido es parte del archivo. En otros terminos la base de
     * datos solo contiene la descripción del archivo, mientras que el
     * contenido se almacena en el sistema de archivos.
     */
    @Transient
    @JsonProperty(access = Access.WRITE_ONLY)
    private String contenido;
    

	public String getFechaDeInsercion() {
		return fechaDeInsercion;
	}

	@JsonIgnore
	public void setFechaDeInsercion(String fechaDeInsercion) {
		this.fechaDeInsercion = fechaDeInsercion;
	}

	/** Constructors, getters and setters. */
    public Archivo() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(int idPropietario) {
		this.idPropietario = idPropietario;
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
	

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * Transient methods.
	 */
	public String getContenido() {
		return contenido;
	}
	
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	/**
	 * Ignore methods
	 */
	public String generarRuta(String contenedor) {
		return contenedor + "/" + id + "." + extension;
	}
}

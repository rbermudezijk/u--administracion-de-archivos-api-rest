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

    /**
     * Representa la union con la tabla/objeto de carpetas/Carpeta.
     * Se agrega anotación JsonIgnore para evitar problemas de mapeo recursivo
     * de JacksonMapper.
     */
    @ManyToOne
    @JoinColumn(name="id_carpeta", nullable=false)
    @JsonIgnore
    private Carpeta carpeta;
    
    /* 
     * Se añade solo lectura del JSON porque el contenido se almacena
     * y proporciona de forma ajena a la base de datos. Sin embargo,
     * el contenido es parte del archivo. En otros terminos la base de
     * datos solo contiene la descripción del archivo, mientras que el
     * contenido se almacena en el sistema de archivos.
     */
    @Transient
    @JsonProperty(access = Access.WRITE_ONLY)
    private String contenido;
    

	/** Constructors, getters and setters. */
    public Archivo() {}

    /**
     * Cuando la consulta se hace por archivos, en el JSON de 
     * respuesta no se requiere el objeto con la descripción
     * completa de la carpeta que agrupa el archivo sino solo
     * el nombre de la carpeta/ubicación del archivo.
     * 
     * @return Ubicación del archivo
     * */
    public String getUbicacion() {
    	return carpeta.getNombre();
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
}

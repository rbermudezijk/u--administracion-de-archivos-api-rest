package models;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

import models.Archivo;

@Entity
@Table(name="sa__carpetas")
public class Carpeta {
	
	@Id
	@Column(name="id_carpeta")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="id_propietario")
	@JsonIgnore
	private int idPropietario;
	@Column(name="nombre")
	private String nombre;
	@OneToMany(mappedBy="carpeta")
	@JsonIgnore
	private Set<Archivo> archivos;

	public Carpeta() {}
	
	public Carpeta(int id, int idPropietario, String nombre, Set<Archivo> archivos) {
		this.id = id;
		this.idPropietario = idPropietario;
		this.nombre = nombre;
		this.archivos = archivos;
	}

	public Set<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(Set<Archivo> archivos) {
		this.archivos = archivos;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdPropietario() {
		return idPropietario;
	}
	
	public void setIdPropietario(int id_llave) {
		this.idPropietario = id_llave;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

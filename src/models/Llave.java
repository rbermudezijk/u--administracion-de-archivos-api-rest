package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="lca__llaves")
public class Llave {
	
	@Id
	@Column(name="id_llave")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int    idLlave;
	@Column(name="llave")
    private String llave;
	@Column(name="propietario")
    private String propietario;
	
    public Llave() {}
    
	public int getIdLlave() {
		return idLlave;
	}
	public void setIdLlave(int idLlave) {
		this.idLlave = idLlave;
	}
	public String getLlave() {
		return llave;
	}
	public void setLlave(String llave) {
		this.llave = llave;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
}

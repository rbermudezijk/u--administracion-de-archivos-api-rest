package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Archivo;
import models.Llave;

@Repository
public class ArchivoSQL {
    
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	public Archivo consultar(int id, int idPropietario) {
		Session ss = getSession();
		CriteriaBuilder cb = ss.getCriteriaBuilder();
		CriteriaQuery<Archivo> cq = cb.createQuery(Archivo.class);
		Root<Archivo> raiz = cq.from(Archivo.class);
		
		Predicate[] filtros = new Predicate[2];
		filtros[0] = cb.equal(raiz.get("idPropietario"), idPropietario);
		filtros[1] = cb.equal(raiz.get("id"), id);
		cq.select(raiz).where(filtros);
		
		return ss.createQuery(cq).getSingleResult();
	}
	
	public List<Archivo> consultar(String subespacio, int idPropietario) {
		Session ss = getSession();
		CriteriaBuilder cb = ss.getCriteriaBuilder();
		CriteriaQuery<Archivo> cq = cb.createQuery(Archivo.class);
		Root<Archivo> raiz = cq.from(Archivo.class);
		
		Predicate[] filtros = new Predicate[2];
		filtros[0] = cb.equal(raiz.get("idPropietario"), idPropietario);
		filtros[1] = cb.like(raiz.get("ubicacion"), subespacio + "%");
		cq.select(raiz).where(filtros);
		
		return (List<Archivo>) ss.createQuery(cq).getResultList();
	}
	
	public Archivo agregar(Archivo file, int idPropietario) {
		file.setIdPropietario(idPropietario);
		getSession().persist(file);
		return file;
	}
	
	public List<Archivo> agregar(List<Archivo> archivos, String ubicacion, int idPropietario) {
		Session sesion = getSession();
		
		for(Archivo archivo: archivos) {
			archivo.setIdPropietario(idPropietario);
			archivo.setUbicacion(ubicacion);
			sesion.persist(archivo);
		}
		
		return archivos;
	}
	
	public Archivo eliminar(int id, int idPropietario) {
		Archivo file = consultar(id, idPropietario);
		getSession().remove(file);
		return file;
	}
	
	public List<Archivo> eliminar(String ubicacion, int idPropietario) {
		List<Archivo> archivos = consultar(ubicacion, idPropietario);
		
		Session sesion = getSession();
		for(Archivo archivo: archivos) {
			sesion.remove(archivo);
		}
		
		return archivos;
	}
	
	public Archivo actualizar(int id, int idPropietario, Archivo archivoActualizado) {
		Archivo archivoPasado = consultar(id, idPropietario);
		
		archivoPasado.setNombre(archivoActualizado.getNombre());
		archivoPasado.setExtension(archivoActualizado.getExtension());
		archivoPasado.setTipoMIME(archivoActualizado.getTipoMIME());
		archivoPasado.setContenido(archivoActualizado.getContenido());
		
		return archivoActualizado;
	}
}

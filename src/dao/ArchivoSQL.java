package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Archivo;

@Repository
public class ArchivoSQL {
    
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	

	public List<Archivo> consultarTodo() {
		return (List<Archivo>) getSession().createCriteria(Archivo.class).list();
	}
	
	public Archivo consultar(int id) {
		return getSession().find(Archivo.class, id);
	}
	
	public Archivo agregar(Archivo file) {
		getSession().persist(file);
		return file;
	}
	
	public Archivo actualizar(int id, Archivo fileNew) {
		Archivo fileOld = getSession().find(Archivo.class, id);
		
		fileOld.setNombre(fileNew.getNombre());
		fileOld.setExtension(fileNew.getExtension());
		fileOld.setTipoMIME(fileNew.getTipoMIME());
		fileOld.setContenido(fileNew.getContenido());
		
		return fileOld;
	}
	
	public Archivo eliminar(int id) {
		Session session = getSession();
		Archivo file = session.find(Archivo.class, id);
		session.remove(file);
		return file;
	}
}

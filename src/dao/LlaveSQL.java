package dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Llave;

@Repository
public class LlaveSQL {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Llave consultarPermisos(String llave) {
		Session ss = sessionFactory.getCurrentSession();;
		CriteriaBuilder cb = ss.getCriteriaBuilder();
		CriteriaQuery<Llave> cq = cb.createQuery(Llave.class);
		Root<Llave> raiz = cq.from(Llave.class);
		
		cq.select(raiz).where(cb.equal(raiz.get("llave"), llave));
		
		return ss.createQuery(cq).getSingleResult(); 
	}
}

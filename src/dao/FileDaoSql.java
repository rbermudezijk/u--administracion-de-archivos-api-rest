package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.FileModel;

@Repository
public class FileDaoSql {
    
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	public List<FileModel> selectAll() {
		return (List<FileModel>) getSession().createCriteria(FileModel.class).list();
	}
	
	public FileModel select(int id) {
		return getSession().find(FileModel.class, id);
	}
	
	public FileModel insert(FileModel file) {
		getSession().persist(file);
		return file;
	}
	
	public FileModel update(int id, FileModel fileNew) {
		FileModel fileOld = getSession().find(FileModel.class, id);
		
		fileOld.setName(fileNew.getName());
		fileOld.setExtension(fileNew.getExtension());
		fileOld.setMimeType(fileNew.getMimeType());
		
		return fileOld;
	}
	
	public FileModel delete(int id) {
		Session session = getSession();
		FileModel file = session.find(FileModel.class, id);
		session.remove(file);
		return file;
	}
}

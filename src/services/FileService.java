package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.FileDaoSql;
import models.FileModel;

@Service
public class FileService {
	
	@Autowired
	private FileDaoSql daoFile;

	@Transactional(readOnly=true)
	public List<FileModel> selectAll()
	{
		return daoFile.selectAll();
	}
	
	@Transactional(readOnly=true)
	public FileModel select(int id)
	{
		return daoFile.select(id);
	}
	
	@Transactional
	public FileModel insert(FileModel file)
	{
		return daoFile.insert(file);
	}
	
	@Transactional
	public FileModel update(int id, FileModel file)
	{
		return daoFile.update(id, file);
	}
	
	@Transactional
	public FileModel delete(int id)
	{
		return daoFile.delete(id);
	}
}

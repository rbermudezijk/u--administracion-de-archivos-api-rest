package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ArchivoSQL;
import dao.ArchivoSistema;
import models.Archivo;

@Service
public class ServicioDeArchivos {
	
	@Autowired
	private ArchivoSQL archivoSQL;
	@Autowired
	private ArchivoSistema archivoSistema;

	@Transactional(readOnly = true)
	public List<Archivo> consultarTodo()
	{
		return archivoSQL.consultarTodo();
	}
	
	@Transactional(readOnly = true)
	public Archivo consultar(int id)
	{
		return archivoSQL.consultar(id);
	}
	
	@Transactional
	public Archivo agregar(Archivo archivo)
	{
		archivoSQL.agregar(archivo);
		archivoSistema.agregar(archivo);
		return archivo;
	}
	
	@Transactional
	public Archivo actualizar(int id, Archivo archivo)
	{
		Archivo archivoActualizado = archivoSQL.actualizar(id, archivo);
		archivoSistema.actualizar(archivoActualizado);
		return archivoActualizado;
	}
	
	@Transactional
	public Archivo eliminar(int id)
	{
		Archivo archivo = archivoSQL.eliminar(id);
		archivoSistema.eliminar(archivo);
		return archivo;
	}
}

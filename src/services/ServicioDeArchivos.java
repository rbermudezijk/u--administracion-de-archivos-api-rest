package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Utilidades.Cifrado;
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
	public Archivo consultar(int id, int idPropietario)
	{
		return archivoSQL.consultar(id, idPropietario);
	}
	
	@Transactional(readOnly = true)
	public List<Archivo> consultar(String subespacioB64, int idPropietario)
	{
		String subespacio = new Cifrado().decodificarB64(subespacioB64);
		return archivoSQL.consultar(subespacio, idPropietario);
	}
	
	@Transactional
	public Archivo agregar(Archivo archivo, int idPropietario)
	{
		archivoSQL.agregar(archivo, idPropietario);
		archivoSistema.agregar(archivo);
		return archivo;
	}

	@Transactional
	public List<Archivo> agregar(List<Archivo> archivos, String subespacioB64, int idPropietario)
	{
		String subespacio = new Cifrado().decodificarB64(subespacioB64);
		archivoSQL.agregar(archivos, subespacio, idPropietario);
		
		for(Archivo archivo: archivos) {
			archivoSistema.agregar(archivo);
		}
		
		return archivos;
	}
	
	@Transactional
	public Archivo eliminar(int id, int idPropietario)
	{
		Archivo archivo = archivoSQL.eliminar(id, idPropietario);
		archivoSistema.eliminar(archivo);
		return archivo;
	}
	
	@Transactional
	public List<Archivo> eliminar(String subespacioB64, int idPropietario)
	{
		String subespacio  = new Cifrado().decodificarB64(subespacioB64);
		List<Archivo> archivosEliminados = archivoSQL.eliminar(subespacio, idPropietario);
		
		for(Archivo archivo: archivosEliminados) {
			archivoSistema.eliminar(archivo);
		}
		
		return archivosEliminados;
	}
	
	@Transactional
	public Archivo actualizar(int id, int idPropietario, Archivo archivo)
	{
		Archivo archivoActualizado = archivoSQL.actualizar(id, idPropietario, archivo);
		archivoSistema.actualizar(archivoActualizado);
		return archivoActualizado;
	}
}

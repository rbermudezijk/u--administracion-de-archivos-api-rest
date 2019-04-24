package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Archivo;
import services.ServicioDeArchivos;
import services.ServicioDeSesion;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/archivos")
public class ControladorArchivos {

	@Autowired
	private ServicioDeArchivos servicioDeArchivos;
	@Autowired
	private ServicioDeSesion sesionDeAPI;
	
	@GetMapping("/id/{id}")
	public Archivo consultar(@PathVariable int id)
	{
	    return servicioDeArchivos.consultar(id, sesionDeAPI.idUsuarioAPI());
	}
	
	@GetMapping("/subespacio/{subespacioB64}")
	public List<Archivo> consultar(@PathVariable String subespacioB64)
	{
	    return servicioDeArchivos.consultar(subespacioB64, sesionDeAPI.idUsuarioAPI());
	}
	
	@DeleteMapping("/id/{id}")
	public Archivo eliminar(@PathVariable int id)
	{
		return this.servicioDeArchivos.eliminar(id, sesionDeAPI.idUsuarioAPI());
	}
	
	@DeleteMapping("/subespacio/{subespacioB64}")
	public List<Archivo> eliminar(@PathVariable String subespacioB64)
	{
	    return servicioDeArchivos.eliminar(subespacioB64, sesionDeAPI.idUsuarioAPI());
	}
	
	@PostMapping(consumes="application/json")
	public Archivo agregar(@RequestBody Archivo archivo)
	{
		return this.servicioDeArchivos.agregar(archivo, sesionDeAPI.idUsuarioAPI());
	}
	
	@PostMapping(path="/ubicacion/{ubicacionB64}",consumes="application/json")
	public List<Archivo> agregar(@RequestBody List<Archivo> archivos, @PathVariable String ubicacionB64)
	{
		return this.servicioDeArchivos.agregar(archivos, ubicacionB64, sesionDeAPI.idUsuarioAPI());
	}
	
	@PutMapping(path="/id/{id}", consumes="application/json")
	public Archivo actualizar(@PathVariable int id, @RequestBody Archivo archivo)
	{
		return this.servicioDeArchivos.actualizar(id, sesionDeAPI.idUsuarioAPI(), archivo);
	}
}

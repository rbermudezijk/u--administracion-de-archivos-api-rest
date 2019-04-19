package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import models.Archivo;
import services.ServicioDeArchivos;

@Controller
@RequestMapping("/archivos")
public class ControladorArchivos {

	@Autowired
	private ServicioDeArchivos servDeArchivos;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public ResponseEntity<List<Archivo>> consultarTodos()
	{
		return new ResponseEntity<List<Archivo>>(
		    servDeArchivos.consultarTodo(), HttpStatus.OK
		);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/id/{id}")
	public ResponseEntity<Archivo> consultar(@PathVariable int id)
	{
	    return new ResponseEntity<Archivo>(
	    	servDeArchivos.consultar(id), HttpStatus.OK
	    );
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Archivo> eliminar(@PathVariable int id)
	{
		return new ResponseEntity<Archivo>(
			this.servDeArchivos.eliminar(id), HttpStatus.OK
		);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(consumes="application/json")
	public ResponseEntity<Archivo> agregar(@RequestBody Archivo archivo)
	{
		return new ResponseEntity<Archivo>(
		    this.servDeArchivos.agregar(archivo),
		    HttpStatus.OK
		);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping(
		path     = "/id/{id}",
		consumes = "application/json"
	)
	public ResponseEntity<Archivo> actualizar(
		@PathVariable int id,
		@RequestBody Archivo archivo
	) {
		return new ResponseEntity<Archivo>(
			this.servDeArchivos.actualizar(id, archivo), HttpStatus.OK
		);
	}
}

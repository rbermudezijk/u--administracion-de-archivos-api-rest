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
import org.springframework.web.servlet.ModelAndView;

import models.FileModel;
import services.FileService;

@Controller
@RequestMapping("/admin-files")
public class AdminFileController {

	@Autowired
	private FileService servFile;
	
	@GetMapping("/index")
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("public/files/files");
		return mv;
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public ResponseEntity<List<FileModel>> getAll()
	{
		return new ResponseEntity<List<FileModel>>(
		    servFile.selectAll(), HttpStatus.OK
		);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/id/{id}")
	public ResponseEntity<FileModel> get(@PathVariable int id)
	{
	    return new ResponseEntity<FileModel>(
	    	servFile.select(id), HttpStatus.OK
	    );
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/id/{id}")
	public ResponseEntity<FileModel> delete(@PathVariable int id)
	{
		return new ResponseEntity<FileModel>(
			this.servFile.delete(id), HttpStatus.OK
		);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(consumes="application/json")
	public ResponseEntity<FileModel> post(@RequestBody FileModel file)
	{
		return new ResponseEntity<FileModel>(
		    this.servFile.insert(file),
		    HttpStatus.OK
		);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping(
		path     = "/id/{id}",
		consumes = "application/json"
	)
	public ResponseEntity<FileModel> put(
		@PathVariable int id,
		@RequestBody FileModel file
	) {
		return new ResponseEntity<FileModel>(
			this.servFile.update(id, file), HttpStatus.OK
		);
	}
}

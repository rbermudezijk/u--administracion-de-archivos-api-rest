package services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Utilidades.Cifrado;
import dao.LlaveSQL;
import models.Llave;

@Service
public class ServicioDeSesion {

	@Autowired
	private LlaveSQL llaveSQL;
	private Llave credenciales;
	
	@Transactional(readOnly = true)
	public void autorizarPeticion(String llave)
		throws NoSuchAlgorithmException {
		
		 String llaveGenerada = new Cifrado().generarLlave(llave.split(" ")[1]);
		 credenciales = llaveSQL.consultarPermisos(llaveGenerada);
	}
	
	public int idUsuarioAPI() {
		return credenciales.getIdLlave();
	}
}

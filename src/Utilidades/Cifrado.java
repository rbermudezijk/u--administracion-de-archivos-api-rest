package Utilidades;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Esta clase encapsula los métodos de cifrado y codificación utilizados en la
 * generación de llaves de acceso al microservicio del sistema de archivos.
 * 
 * @author Ricardo Bermúdez Bermúdez
 * @since  19 de abril del 2019
 */
public class Cifrado {

	/**
	 * Método utilizado para ocultar los datos de las llaves de acceso al
	 * sistema. Como método analogo en MySQL/MariaDB se puede utilizar el
	 * metodo <code>SHA2('texto a cifrar', 256)<code>.
	 * 
	 * @see    java.security.MessageDigest
	 * @param  cadena Texto a cifrar.
	 * @return Texto cifrado.
	 */
	public String sha256(String cadena)
	   throws NoSuchAlgorithmException {
		
		StringBuilder cifrado = new StringBuilder();
	    
		byte[] cifradoBytes = MessageDigest
	    .getInstance("SHA-256")
	    .digest(cadena.getBytes(StandardCharsets.UTF_8));
	    
	    for(byte caracter: cifradoBytes) {
	    	cifrado.append(String.format("%02x",caracter));
	    }
		
		return cifrado.toString();
	}
	
	/**
	 * Método utilizado para decodificar una cadena de texto cifrada en base64.
	 * 
	 * @see    java.util.Base64
	 * @param  cadenaB64 Cadena de texto en codificada como base 64.
	 * @return Cadena de texto decodificada.
	 */
	public String decodificarB64(String cadenaB64) {
	    return new String(Base64.getDecoder().decode(cadenaB64));		
	}
	
	/**
	 * Este método encapsula las operaciones de codificacion/decodificacion de
	 * las llaves de acceso a las operaciones del sistema de archivos.
	 * 
	 * Primero se decodifica el texto de la llave de acceso codificado en base
	 * 64, codificados así para evitar errores de transmisión del texto y
	 * segundo se codifica en SHA256 el contenido de la llave para ocultar los
	 * detalles de la misma a la base de datos.
	 * 
	 * @param base Llave codificada en base 64.
	 * @return Llave cifrada con SHA256. 
	 */
	public String generarLlave(String base)
		throws NoSuchAlgorithmException {
		 return sha256(decodificarB64(base));
	}
}

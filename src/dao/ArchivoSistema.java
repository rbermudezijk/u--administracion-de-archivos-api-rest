package dao;

import models.Archivo;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import org.springframework.stereotype.Repository;
import java.util.Base64;

@Repository
public class ArchivoSistema {
	
	public static String CONTENEDOR = "c:/Servidores-Desarrollos/apache-2.4.35/contenedor/"; 
	
	public void agregar(Archivo archivo) {
		try ( 
			BufferedOutputStream fichero = new BufferedOutputStream(
				new FileOutputStream(archivo.ruta(CONTENEDOR)) 
			)
		){
		    fichero.write(Base64.getDecoder().decode(archivo.getContenido()));
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void eliminar(Archivo archivo) {
		File fichero = new File(archivo.ruta(CONTENEDOR));
		try {
			if (fichero.exists()) {
				fichero.delete();
			}
		}
		catch (Exception e) {
		    System.out.println(e.toString());
		}
	}
	
    public void actualizar(Archivo archivo) {
         if (archivo.getContenido()!=null) {
        	 this.eliminar(archivo);
        	 this.agregar(archivo);
         }
    }
}
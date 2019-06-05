package dao;

import models.Archivo;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import java.util.Base64;

@Repository
@PropertySource(value="classpath:app.properties")
public class ArchivoSistema {
	
	@Value("${datasource.files_path}")
	public String CONTENEDOR;
	
	public void agregar(Archivo archivo) {
		try ( 
			BufferedOutputStream fichero = new BufferedOutputStream(
				new FileOutputStream(archivo.generarRuta(CONTENEDOR)) 
			)
		){
		    fichero.write(Base64.getDecoder().decode(archivo.getContenido()));
		    File file = new File(archivo.generarRuta(CONTENEDOR));
		    file.setReadable(true, false);
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void eliminar(Archivo archivo) {
		File fichero = new File(archivo.generarRuta(CONTENEDOR));
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

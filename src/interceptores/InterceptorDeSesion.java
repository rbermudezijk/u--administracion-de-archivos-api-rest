package interceptores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import services.ServicioDeSesion;



public class InterceptorDeSesion extends HandlerInterceptorAdapter {
	
	@Autowired
	ServicioDeSesion sesionDeAPI;
	
	@Override
	/**
	 * Tener en cuenta las siguientes excepciones:
	 * 
	 * java.lang.IllegalArgumentException: Illegal base64 character -f
     * java.lang.ArrayIndexOutOfBoundsException: 1 //cuando se envía peticion sin encabezado
     * javax.persistence.NoResultException: No entity found for query
     * cadena vacia (no genera excepción ).
	 */
    public boolean preHandle(
    	HttpServletRequest  solicitud,
    	HttpServletResponse respuesta,
    	Object handler
    ) throws Exception {
		try {
			respuesta.setHeader("Access-Control-Allow-Origin",  solicitud.getHeader("origin"));
			respuesta.setHeader("Access-Control-Allow-Methods", "PUT,GET,DELETE,POST,OPTIONS");
			respuesta.setHeader("Access-Control-Allow-Headers", "Content-Type,Accept,Authorization,*");
			
			if (solicitud.getMethod().equals("OPTIONS")) { return true; }
			
			sesionDeAPI.autorizarPeticion( solicitud.getHeader("Authorization") );
		    return true;
		} catch(Exception e) {
			respuesta.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
    }
}
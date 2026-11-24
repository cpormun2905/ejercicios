package examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.ControladorPrestamos;
import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.Mensaje;
import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.PrestamoParaCreacion;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/examen/api/prestamos/crear")
public class ServletPostCrearPrestamo extends HttpServlet{

	
    // Objeto encargado de convertir JSON en objetos Java.
    private ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Compruebo que exista sesión y que tenga el atributo
		HttpSession sesion = request.getSession(false);
		
		// Comprobamos que exista la sesión y un atributo con el nombre del usuario autenticado
		if (sesion == null || sesion.getAttribute("username") == null) {
			// El usuario no está autenticado
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		// Si llego aquí es porques existe sesión

        // Leemos el cuerpo de la petición HTTP.
        BufferedReader reader = request.getReader();

        String payload = "";
        String linea;

        while ((linea = reader.readLine()) != null) {
            payload += linea;
        }

        System.out.println("El payload recibido es: " + payload);
                
        // Convertimos el JSON recibido en un objeto Categoria.
        PrestamoParaCreacion p = objectMapper.readValue(payload, PrestamoParaCreacion.class);

        
        // Valido los datos recibidos
        if (p.getDescripcion() == null || p.getDescripcion().isBlank()) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	response.getWriter().print(objectMapper.writeValueAsString(
        			new Mensaje("La descripción no puede ser nula")));
        	return;
        }
        
        if (p.getMaterial() == null || p.getMaterial().isBlank()) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	response.getWriter().print(objectMapper.writeValueAsString(
        			new Mensaje("El material no puede ser nulo")));
        	return;
        }
        
        if (p.getFechaPrestamo() == null) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	response.getWriter().print(objectMapper.writeValueAsString(
        			new Mensaje("La fecha no puede ser nula")));
        	return;
        }
        
        
        
        

        
        
        
        
        // Llamo al método que inserta este préstamo
        try {
			int filasAfec = ControladorPrestamos.insertaPrestamo(p, 
					(String) sesion.getAttribute("username"));
			
			if (filasAfec == 1) {
				response.setStatus(HttpServletResponse.SC_CREATED);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}

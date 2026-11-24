package examen24112026;

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

@WebServlet("/examen24112026/api/incidencias/crear")
public class ServletPostCrearIncidencias extends HttpServlet{

	
    private ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion = request.getSession(false);
		

		if (sesion == null || sesion.getAttribute("username") == null) {

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
                
        // Convertimos el JSON recibido en un objeto Incidencia.
        IncidenciaParaCreacion i = objectMapper.readValue(payload, IncidenciaParaCreacion.class);

        
        // Valido los datos recibidos
        
        if (i.getTitulo() == null || i.getTitulo().isBlank() || i.getTitulo().chars().count() < 5 || i.getTitulo().isEmpty()) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	response.getWriter().print(objectMapper.writeValueAsString(
        			new Mensaje("El titulo no puede ser nulo o menor de 5 caracteres")));
        	return;
        }
        
        if (i.getDescripcion() == null || i.getDescripcion().isBlank() || i.getDescripcion().chars().count() < 10 || i.getDescripcion().isEmpty()) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	response.getWriter().print(objectMapper.writeValueAsString(
        			new Mensaje("La descripción no puede ser nula o menor de 10 caracteres")));
        	return;
        }
        
        if (i.getId_categoria() == 0 || i.getId_categoria() < 1 || i.getId_categoria() > 5 ) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	response.getWriter().print(objectMapper.writeValueAsString(
        			new Mensaje("El id de la categoria no puede ser nulo o no existir entre las categorias creadas")));
        	return;
        }
        
        
        
        
        // Llamo al método que inserta esta incidencia
        try {
			int filasAfec = ControladorIncidencias.insertaIncidencia(i, 
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

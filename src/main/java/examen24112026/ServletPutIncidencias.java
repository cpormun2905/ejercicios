package examen24112026;

import java.io.IOException;
import java.sql.SQLException;

import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.ControladorPrestamos;
import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.Mensaje;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/examen24112026/api/incidencias/modificar?id=IDENTIFICADOR")
public class ServletPutIncidencias extends HttpServlet {
	private static final long serialVersionUID = 1L;


    private ObjectMapper objectMapper = new ObjectMapper();

	
    /**
     * Default constructor. 
     */
    public ServletPutIncidencias() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sesion = request.getSession(false);
		

		if (sesion == null || sesion.getAttribute("username") == null) {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		String strId = request.getParameter("id");
		if (strId == null) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	response.getWriter().print(objectMapper.writeValueAsString(
        			new Mensaje("Se debe incluir un query param 'id'.")));
        	return;
			
		}
		
		int idAModificar = 0;
		try {
			idAModificar = Integer.parseInt(strId);
		}
		catch (NumberFormatException ex) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	response.getWriter().print(objectMapper.writeValueAsString(
        			new Mensaje("El query param 'id' debe ser numérico")));
        	return;
		}
		
		int filasAfec = 0;
		
		try {
			filasAfec = ControladorIncidencias.modificarIncidencia(idAModificar);
			
			if (filasAfec == 1) {
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			}
			else {
	        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        	response.getWriter().print(objectMapper.writeValueAsString(
	        			new Mensaje("El 'id' pedido no corresponde con una incidencia")));
	        	return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

package examen;

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

@WebServlet("/examen/api/prestamos/eliminar")
public class ServletDeletePrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // Objeto encargado de convertir JSON en objetos Java.
    private ObjectMapper objectMapper = new ObjectMapper();

	
    /**
     * Default constructor. 
     */
    public ServletDeletePrestamo() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Compruebo que exista sesión y que tenga el atributo
		HttpSession sesion = request.getSession(false);
		
		// Comprobamos que exista la sesión y un atributo con el nombre del usuario autenticado
		if (sesion == null || sesion.getAttribute("username") == null) {
			// El usuario no está autenticado
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		// Compruebo query param del id del préstamo a eliminar
		String strId = request.getParameter("id");
		if (strId == null) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	response.getWriter().print(objectMapper.writeValueAsString(
        			new Mensaje("Se debe incluir un query param 'id'.")));
        	return;
			
		}
		
		int idAEliminar = 0;
		try {
			idAEliminar = Integer.parseInt(strId);
		}
		catch (NumberFormatException ex) {
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	response.getWriter().print(objectMapper.writeValueAsString(
        			new Mensaje("El query param 'id' debe ser numérico")));
        	return;
		}
		
		// Si llego hasta aquí el query param id tiene un valor numérico
		int filasAfec = 0;
		
		try {
			filasAfec = ControladorPrestamos.eliminaPrestamo(idAEliminar);
			
			if (filasAfec == 1) {
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			}
			else {
	        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        	response.getWriter().print(objectMapper.writeValueAsString(
	        			new Mensaje("El 'id' pedido no corresponde con un préstamo activo")));
	        	return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

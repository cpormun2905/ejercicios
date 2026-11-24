package examen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.ControladorPrestamos;
import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.Prestamo;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/examen/api/prestamos")
public class ServletGetPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletGetPrestamos() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Compruebo que exista sesión y que tenga el atributo
		HttpSession sesion = request.getSession(false);
		
		// Comprobamos que exista la sesión y un atributo con el nombre del usuario autenticado
		if (sesion == null || sesion.getAttribute("username") == null) {
			// El usuario no está autenticado
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		// Sí estoy autorizado
		String userNameAutenticado = (String) sesion.getAttribute("username");
		List<Prestamo> l = new ArrayList<Prestamo>();
		
		try {
			l = ControladorPrestamos.findPrestamos(userNameAutenticado,
					request.getParameter("descripcion"),
					request.getParameter("material"),
					request.getParameter("fechaPrestamoDesde"),
					request.getParameter("fechaPrestamoHasta"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObjectMapper om = new ObjectMapper();
		response.getWriter().append(om.writeValueAsString(l));
		response.setStatus(HttpServletResponse.SC_OK);
		
	}

}

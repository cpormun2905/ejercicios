package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * GET /ej02_teclados        -> array JSON con todos los teclados
 * GET /ej02_teclados?id=3   -> el teclado con ese id (404 si no existe)
 */
@WebServlet("/ej02_teclados")
public class ServletGetTeclados extends SuperServletTeclados {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Si no recibo "id" devuelvo un array JSON con todos los teclados
		if (request.getParameter("id") == null) {
			sendJson(response, HttpServletResponse.SC_OK, ControladorTeclado.getLista());
			return;
		}

		// Si recibo "id" sólo devuelvo el teclado correspondiente
		Integer idTeclado = getIntParameterWithCheck("id", request, response);
		if (idTeclado == null) {
			return;
		}

		Teclado t = ControladorTeclado.getTeclado(idTeclado);
		if (t != null) {
			sendJson(response, HttpServletResponse.SC_OK, t);
		}
		else {
			sendError(response, HttpServletResponse.SC_NOT_FOUND,
					"Teclado con id " + idTeclado + " no encontrado");
		}
	}

}

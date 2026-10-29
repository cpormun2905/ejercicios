package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.io.IOException;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * GET /ej02_categorias        -> array JSON con todas las categorías
 * GET /ej02_categorias?id=3   -> la categoría con ese id (404 si no existe)
 */
@WebServlet("/ej02_categorias")
public class ServletGetCategorias extends SuperServletCategorias {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Si no recibo "id" devuelvo un array JSON con todas las categorías
		if (request.getParameter("id") == null) {
			sendJson(response, HttpServletResponse.SC_OK, ControladorCategoria.getLista());
			return;
		}

		// Si recibo "id" sólo devuelvo la categoría correspondiente
		Integer idCategoria = getIntParameterWithCheck("id", request, response);
		if (idCategoria == null) {
			return;
		}

		Categoria c = ControladorCategoria.getCategoria(idCategoria);
		if (c != null) {
			sendJson(response, HttpServletResponse.SC_OK, c);
		}
		else {
			sendError(response, HttpServletResponse.SC_NOT_FOUND,
					"Categoría con id " + idCategoria + " no encontrada");
		}
	}

}

package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.io.IOException;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * POST /ej02_categorias/crear
 * Body JSON: {"descripcion":"..."}
 * Devuelve la categoría creada (con su id) en JSON
 */
@WebServlet("/ej02_categorias/crear")
public class ServletPostCategorias extends SuperServletCategorias {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PostCategoriasRequestBody requestBody = getJsonBodyWithCheck(
				PostCategoriasRequestBody.class, request, response);
		if (requestBody == null) {
			return;
		}

		// Si no me dan una descripción, error 400 Bad Request
		if (requestBody.getDescripcion() == null || requestBody.getDescripcion().isBlank()) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST,
					"El JSON debe incluir el campo 'descripcion'");
			return;
		}

		// Si llego hasta aquí debo crear una nueva categoría
		Categoria c = ControladorCategoria.createCategoria(requestBody.getDescripcion().trim());
		sendJson(response, HttpServletResponse.SC_CREATED, c);
	}

}

package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.io.IOException;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * PUT /ej02_categorias/modificar
 * Body JSON: {"id":3,"descripcion":"..."}
 * Devuelve la categoría modificada en JSON (404 si el id no existe)
 */
@WebServlet("/ej02_categorias/modificar")
public class ServletPutCategorias extends SuperServletCategorias {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Comprobar que recibo los datos necesarios
		PutCategoriasRequestBody requestBody = getJsonBodyWithCheck(
				PutCategoriasRequestBody.class, request, response);
		if (requestBody == null) {
			return;
		}

		if (requestBody.getId() == null) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST, "El JSON debe incluir el campo 'id'");
			return;
		}

		if (requestBody.getDescripcion() == null || requestBody.getDescripcion().isBlank()) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST,
					"El JSON debe incluir el campo 'descripcion'");
			return;
		}

		Categoria c = ControladorCategoria.getCategoria(requestBody.getId());
		if (c == null) {
			sendError(response, HttpServletResponse.SC_NOT_FOUND,
					"El valor de 'id' no corresponde con alguna categoría existente");
			return;
		}

		// Si llego hasta aquí significa que puedo sobrescribir los datos de la categoría
		c.setDescripcion(requestBody.getDescripcion().trim());
		sendJson(response, HttpServletResponse.SC_OK, c);
	}

}

package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * DELETE /ej02_categorias/eliminar?id=3
 * Devuelve la categoría eliminada en JSON (404 si el id no existe)
 */
@WebServlet("/ej02_categorias/eliminar")
public class ServletDeleteCategorias extends SuperServletCategorias {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Comprobar que recibo los datos necesarios
		Integer id = getIntParameterWithCheck("id", request, response);
		if (id == null) {
			return;
		}

		Categoria c = ControladorCategoria.getCategoria(id);
		if (c == null) {
			sendError(response, HttpServletResponse.SC_NOT_FOUND,
					"El valor de 'id' no corresponde con alguna categoría existente");
			return;
		}

		// Si llego hasta aquí significa que puedo eliminar la categoría
		ControladorCategoria.getLista().remove(c);
		sendJson(response, HttpServletResponse.SC_OK, c);
	}

}

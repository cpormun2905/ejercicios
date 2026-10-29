package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.io.IOException;



import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet padre de los servlets de Teclado. Hereda los métodos comunes
 * (sendJson, sendError, getIntParameterWithCheck, getJsonBodyWithCheck)
 * de SuperServletCategorias y añade la validación de los campos del teclado.
 */
public class SuperServletTeclados extends SuperServletCategorias {
	private static final long serialVersionUID = 1L;

	/**
	 * Comprueba color, idioma e idCategoria. Si algo falla, envía el error 400 y devuelve false
	 */
	protected boolean checkTecladoFields (String color, String idioma, Integer idCategoria,
			HttpServletResponse response) throws IOException {

		if (color == null || color.isBlank()) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST, "El JSON debe incluir el campo 'color'");
			return false;
		}

		if (idioma == null || idioma.isBlank()) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST, "El JSON debe incluir el campo 'idioma'");
			return false;
		}

		if (idCategoria == null) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST, "El JSON debe incluir el campo 'idCategoria'");
			return false;
		}

		if (ControladorCategoria.getCategoria(idCategoria) == null) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST,
					"El valor de 'idCategoria' no corresponde con alguna categoría existente");
			return false;
		}

		return true;
	}

}

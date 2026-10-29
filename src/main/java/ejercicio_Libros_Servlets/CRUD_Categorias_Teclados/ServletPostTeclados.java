package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * POST /ej02_teclados/crear
 * Body JSON: {"color":"...","idioma":"...","idCategoria":1}
 * Devuelve el teclado creado (con su id) en JSON
 */
@WebServlet("/ej02_teclados/crear")
public class ServletPostTeclados extends SuperServletTeclados {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PostTecladosRequestBody requestBody = getJsonBodyWithCheck(
				PostTecladosRequestBody.class, request, response);
		if ((requestBody == null) || !checkTecladoFields(requestBody.getColor(), requestBody.getIdioma(),
				requestBody.getIdCategoria(), response)) {
			return;
		}

		// Si llego hasta aquí debo crear un nuevo teclado
		Teclado t = ControladorTeclado.createTeclado(requestBody.getColor().trim(),
				requestBody.getIdioma().trim(), requestBody.getIdCategoria());
		sendJson(response, HttpServletResponse.SC_CREATED, t);
	}

}

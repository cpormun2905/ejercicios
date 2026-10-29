package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * PUT /ej02_teclados/modificar
 * Body JSON: {"id":3,"color":"...","idioma":"...","idCategoria":1}
 * Devuelve el teclado modificado en JSON (404 si el id no existe)
 */
@WebServlet("/ej02_teclados/modificar")
public class ServletPutTeclados extends SuperServletTeclados {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Comprobar que recibo los datos necesarios
		PutTecladosRequestBody requestBody = getJsonBodyWithCheck(
				PutTecladosRequestBody.class, request, response);
		if (requestBody == null) {
			return;
		}

		if (requestBody.getId() == null) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST, "El JSON debe incluir el campo 'id'");
			return;
		}

		if (!checkTecladoFields(requestBody.getColor(), requestBody.getIdioma(),
				requestBody.getIdCategoria(), response)) {
			return;
		}

		Teclado t = ControladorTeclado.getTeclado(requestBody.getId());
		if (t == null) {
			sendError(response, HttpServletResponse.SC_NOT_FOUND,
					"El valor de 'id' no corresponde con algún teclado existente");
			return;
		}

		// Si llego hasta aquí significa que puedo sobrescribir los datos del teclado
		t.setColor(requestBody.getColor().trim());
		t.setIdioma(requestBody.getIdioma().trim());
		t.setIdCategoria(requestBody.getIdCategoria());
		sendJson(response, HttpServletResponse.SC_OK, t);
	}

}

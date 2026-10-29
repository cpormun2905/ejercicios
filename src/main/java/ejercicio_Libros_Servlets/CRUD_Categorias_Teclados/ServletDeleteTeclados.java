package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * DELETE /ej02_teclados/eliminar?id=3
 * Devuelve el teclado eliminado en JSON (404 si el id no existe)
 */
@WebServlet("/ej02_teclados/eliminar")
public class ServletDeleteTeclados extends SuperServletTeclados {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Comprobar que recibo los datos necesarios
		Integer id = getIntParameterWithCheck("id", request, response);
		if (id == null) {
			return;
		}

		Teclado t = ControladorTeclado.getTeclado(id);
		if (t == null) {
			sendError(response, HttpServletResponse.SC_NOT_FOUND,
					"El valor de 'id' no corresponde con algún teclado existente");
			return;
		}

		// Si llego hasta aquí significa que puedo eliminar el teclado
		ControladorTeclado.getLista().remove(t);
		sendJson(response, HttpServletResponse.SC_OK, t);
	}

}

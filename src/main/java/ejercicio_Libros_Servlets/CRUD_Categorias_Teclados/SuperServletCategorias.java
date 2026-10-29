package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SuperServletCategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Envía cualquier objeto (o lista) como JSON con el código de estado indicado
	 */
	 protected void sendJson (HttpServletResponse response, int status, Object o) throws IOException {
		response.setStatus(status);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(JSONUtils.toJSON(o));
	}


	/**
	 * Envía un error en JSON: {"mensaje":"..."}
	 * OJO: usa la clase Error de este paquete, no java.lang.Error
	 */
	protected void sendError (HttpServletResponse response, int status, String mensaje) throws IOException {
		sendJson(response, status, new ejercicio_Libros_Servlets.CRUD_Categorias_Teclados.Error(mensaje));
	}


	/**
	 * Lee un parámetro entero de la URL. Si falta o no es un entero, envía el error 400 y devuelve null
	 */
	protected Integer getIntParameterWithCheck (String paramName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String strParamValue = request.getParameter(paramName);
		if (strParamValue == null) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST,
					"Parámetro no incluido: '" + paramName + "'");
			return null;
		}

		try {
			return Integer.parseInt(strParamValue);
		}
		catch (NumberFormatException ex) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST,
					"Parámetro '" + paramName + "': '" + strParamValue + "' no es un valor entero");
		}

		return null;
	}


	/**
	 * Lee el cuerpo de la petición (JSON) y lo convierte a la clase indicada.
	 * Si está vacío o no es un JSON válido, envía el error 400 (con el motivo) y devuelve null
	 */
	protected <T> T getJsonBodyWithCheck (Class<T> clase, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");

		BufferedReader lector = request.getReader();
		StringBuilder sb = new StringBuilder();
		String linea;
		while ((linea = lector.readLine()) != null) {
			sb.append(linea);
		}

		String textoRecibido = sb.toString();
		if (textoRecibido.isBlank()) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST,
					"El cuerpo de la petición está vacío: se esperaba un JSON");
			return null;
		}

		try {
			T body = JSONUtils.fromJSON(textoRecibido, clase);
			if (body != null) {
				return body;
			}
		}
		catch (JsonProcessingException ex) {
			sendError(response, HttpServletResponse.SC_BAD_REQUEST,
					"El cuerpo de la petición debe ser un JSON válido: " + ex.getOriginalMessage());
			return null;
		}

		sendError(response, HttpServletResponse.SC_BAD_REQUEST,
				"El cuerpo de la petición debe ser un JSON válido");
		return null;
	}

}

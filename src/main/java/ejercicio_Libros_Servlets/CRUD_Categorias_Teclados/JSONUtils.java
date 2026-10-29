package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

	private static ObjectMapper jacksonMapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


	/**
	 * Convierte un objeto Java (o una lista) a texto JSON
	 */
	public static String toJSON (Object o) throws JsonProcessingException {
		return jacksonMapper.writeValueAsString(o);
	}


	/**
	 * Convierte un texto JSON al tipo de objeto indicado
	 */
	public static <T> T fromJSON (String json, Class<T> clase) throws JsonProcessingException {
		return jacksonMapper.readValue(json, clase);
	}

}

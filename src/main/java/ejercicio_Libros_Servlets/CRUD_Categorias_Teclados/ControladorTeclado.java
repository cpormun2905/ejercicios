package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.util.ArrayList;
import java.util.List;

public class ControladorTeclado {

	private static List<Teclado> lista = new ArrayList<>();


	static {
		// Los idCategoria deben existir en ControladorCategoria
		lista.add(new Teclado(1, "Negro", "Español", 1));
		lista.add(new Teclado(2, "Blanco", "Inglés", 2));
		lista.add(new Teclado(3, "Negro", "Español", 3));
	}

	public static List<Teclado> getLista() {
		return lista;
	}


	private static int getMaxId () {
		int mayorId = 0;
		for (Teclado t : lista) {
			if (t.getId() > mayorId) {
				mayorId = t.getId();
			}
		}
		return mayorId;
	}


	public static Teclado createTeclado (String color, String idioma, int idCategoria) {
		Teclado t = new Teclado(getMaxId() + 1, color, idioma, idCategoria);
		lista.add(t);
		return t;
	}


	public static Teclado getTeclado (int id) {
		for (Teclado t : lista) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}
}

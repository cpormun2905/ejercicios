package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

import java.util.ArrayList;
import java.util.List;

public class ControladorCategoria {

	private static List<Categoria> lista = new ArrayList<>();


	static {
		lista.add(new Categoria(1, "Mecánicos"));
		lista.add(new Categoria(2, "Inalámbricos"));
		lista.add(new Categoria(3, "Membrana"));
	}


	public static List<Categoria> getLista() {
		return lista;
	}


	/**
	 * Devuelve el mayor id existente, o 0 si la lista está vacía
	 */
	private static int getMaxId () {
		int mayorId = 0;
		for (Categoria c : lista) {
			if (c.getId() > mayorId) {
				mayorId = c.getId();
			}
		}
		return mayorId;
	}


	/**
	 * Crea una categoría nueva con el siguiente id disponible y la añade a la lista
	 */
	public static Categoria createCategoria (String descripcion) {
		Categoria c = new Categoria(getMaxId() + 1, descripcion);
		lista.add(c);
		return c;
	}


	/**
	 * Devuelve la categoría con ese id, o null si no existe
	 */
	public static Categoria getCategoria (int id) {
		for (Categoria c : lista) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

}

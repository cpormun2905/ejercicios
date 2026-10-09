package ejercicio_Libros_Servlets.CRUD_Libros;

import java.util.ArrayList;
import java.util.List;

public class ControladorLibro {

    // Lista compartida por todos los Servlets.
    private static final List<Libro> libros = new ArrayList<>();

    // Próximo identificador que se asignará.
    private static int siguienteId = 4;

    static {
        libros.add(new Libro(1, "Don Quijote de la Mancha", 1605));
        libros.add(new Libro(2, "Cien años de soledad", 1967));
        libros.add(new Libro(3, "La sombra del viento", 2001));
    }

    public static List<Libro> listar() {
        return libros;
    }

    public static Libro buscarPorId(int id) {
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    public static Libro crear(String titulo, int anioPublicacion) {
        Libro libro = new Libro(siguienteId++, titulo, anioPublicacion);
        libros.add(libro);
        return libro;
    }

    public static boolean eliminar(int id) {
        Libro libro = buscarPorId(id);
        if (libro == null) {
            return false;
        }
        libros.remove(libro);
        return true;
    }
}
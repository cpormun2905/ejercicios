package ejercicio_Libros_Servlets.CRUD_Libros;

import java.io.IOException;
import java.io.PrintWriter;

import ejercicio_Libros_Servlets.XmlUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ej02_libros")
public class LibroGetServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String idParam = request.getParameter("id");

        // GET /ej02_libros  -> todos los libros
        if (idParam == null) {

            response.setStatus(HttpServletResponse.SC_OK);

            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset=\"UTF-8\"><title>Libros</title></head><body>");
            out.println("<h1>Lista de libros</h1>");
            out.println("<ul>");

            for (Libro libro : ControladorLibro.listar()) {
                out.println("<li>ID: " + libro.getId()
                        + " | Título: " + XmlUtil.escapar(libro.getTitulo())
                        + " | Año: " + libro.getAnioPublicacion() + "</li>");
            }

            out.println("</ul>");
            out.println("</body></html>");
            return;
        }

        // GET /ej02_libros?id=2  -> un solo libro
        try {
            int id = Integer.parseInt(idParam);
            Libro libro = ControladorLibro.buscarPorId(id);

            if (libro == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("<html><body><p>Libro no encontrado.</p></body></html>");
                return;
            }

            response.setStatus(HttpServletResponse.SC_OK);

            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset=\"UTF-8\"><title>Libro</title></head><body>");
            out.println("<h1>Libro</h1>");
            out.println("<ul>");
            out.println("<li>ID: " + libro.getId() + "</li>");
            out.println("<li>Título: " + XmlUtil.escapar(libro.getTitulo()) + "</li>");
            out.println("<li>Año de publicación: " + libro.getAnioPublicacion() + "</li>");
            out.println("</ul>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<html><body><p>El parámetro id debe ser un número entero.</p></body></html>");
        }
    }
}
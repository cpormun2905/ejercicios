package ejercicio_Libros_Servlets.CRUD_Libros;

import java.io.IOException;

import ejercicio_Libros_Servlets.XmlUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ej02_libros/modificar")
public class LibroPutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/xml;charset=UTF-8");

        String idParam = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String anioParam = request.getParameter("anioPublicacion");

        if (idParam == null || titulo == null || anioParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(XmlUtil.error("Faltan los parámetros id, titulo y anioPublicacion."));
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            int anio = Integer.parseInt(anioParam);

            Libro libro = ControladorLibro.buscarPorId(id);

            if (libro == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
                response.getWriter().print(XmlUtil.error("Libro no encontrado."));
                return;
            }

            libro.setTitulo(titulo);
            libro.setAnioPublicacion(anio);

            response.setStatus(HttpServletResponse.SC_OK); // 200

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(XmlUtil.error("id y anioPublicacion deben ser números enteros."));
        }
    }
}
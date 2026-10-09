package ejercicio_Libros_Servlets.CRUD_Libros;

import java.io.IOException;

import ejercicio_Libros_Servlets.XmlUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ej02_libros/crear")
public class LibroPostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/xml;charset=UTF-8");

        String titulo = request.getParameter("titulo");
        String anioParam = request.getParameter("anioPublicacion");

        if (titulo == null || anioParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(XmlUtil.error("Faltan los parámetros titulo y anioPublicacion."));
            return;
        }

        try {
            int anio = Integer.parseInt(anioParam);

            ControladorLibro.crear(titulo, anio);

            response.setStatus(HttpServletResponse.SC_CREATED); // 201

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(XmlUtil.error("anioPublicacion debe ser un número entero."));
        }
    }
}
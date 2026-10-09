package ejercicio_Libros_Servlets.CRUD_Libros;

import java.io.IOException;

import ejercicio_Libros_Servlets.XmlUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ej02_libros/eliminar")
public class LibroDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/xml;charset=UTF-8");

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(XmlUtil.error("Debe indicar el parámetro id."));
            return;
        }

        try {
            int id = Integer.parseInt(idParam);

            if (ControladorLibro.eliminar(id)) {
                response.setStatus(HttpServletResponse.SC_OK); // 200
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
                response.getWriter().print(XmlUtil.error("Libro no encontrado."));
            }

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(XmlUtil.error("El parámetro id debe ser un número entero."));
        }
    }
}
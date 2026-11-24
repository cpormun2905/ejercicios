package examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.ControladorUsuario;
import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.Usuario;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/examen/api/login")
public class ServletPostAutenticacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    // Objeto encargado de convertir JSON en objetos Java.
    private ObjectMapper objectMapper = new ObjectMapper();

	
    /**
     * Default constructor. 
     */
    public ServletPostAutenticacion() {
        // TODO Auto-generated constructor stub
    }  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Leemos el cuerpo de la petición HTTP.
        BufferedReader reader = request.getReader();

        String payload = "";
        String linea;

        while ((linea = reader.readLine()) != null) {
            payload += linea;
        }

        System.out.println("El payload recibido es: " + payload);
        
        // Convertimos el JSON recibido en un objeto Categoria.
        Usuario u = objectMapper.readValue(payload, Usuario.class);

        
        
        try {
			if (ControladorUsuario.findUsuarioByNombreAndPassword
					(u.getUsername(), u.getPassword()) == true) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("username", u.getUsername());
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else { // No tenemos usuario autenticado
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			
			response.getWriter().append("Hola autenticación");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.Usuario;

public class ControladorUsuario {

	
	 private static final String DB_URL =
	            "jdbc:mysql://localhost:3306/examen";

	    private static final String DB_USER = "root";

	    private static final String DB_PASSWORD = "1234";
	    
	    
	    public static boolean findUsuarioByNombreAndPassword(String username, 
	    		String password) throws SQLException {
	    	
	        Usuario u = null;
	        Connection conexion = null;
	        PreparedStatement sentencia = null;
	        ResultSet resultado = null;
	        

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

		        conexion = DriverManager.getConnection(
		                DB_URL, DB_USER, DB_PASSWORD);

		        String sql = "SELECT * FROM gestion_prestamos.USUARIOS u  "
		        		+ "where u.username  = ? AND "
		        		+ "u.password  = ? limit 1";

		        sentencia = conexion.prepareStatement(sql);
		        
		        sentencia.setString(1, username);
		        sentencia.setString(2, password);
		        
		        resultado = sentencia.executeQuery();

		        // Recorremos los registros obtenidos.
		        if (resultado.next()) {
		        	return true;
		        }
			
			} catch (Exception e) {
				System.out.println("Error de driver");
				e.printStackTrace();
			} finally {
		        // Cerramos los recursos JDBC.
		        resultado.close();
		        sentencia.close();
		        conexion.close();
			}
		
	        return false;
	    }
	
}

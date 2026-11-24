package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.Prestamo;
import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.PrestamoParaCreacion;

public class ControladorPrestamo {

	
	 private static final String DB_URL =
	            "jdbc:mysql://localhost:3306/examen";

	    private static final String DB_USER = "root";

	    private static final String DB_PASSWORD = "1234";
	    
	    /**
	     * Obtiene todas las categorías de la base de datos.
	     * @throws SQLException 
	     */
	    public static List<Prestamo> findPrestamos(String username, 
	    		String descripcion, String material,
	    		String fechaDesde, String fechaHasta) throws SQLException {
	    	
	    	List<Prestamo> l = new ArrayList<Prestamo>();
	        Connection conexion = null;
	        PreparedStatement sentencia = null;
	        ResultSet resultado = null;
	        

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

		        conexion = DriverManager.getConnection(
		                DB_URL, DB_USER, DB_PASSWORD);

		        String sql = "SELECT * FROM gestion_prestamos.PRESTAMOS "
		        		+ " where username = ? ";
		        
		        if (descripcion != null) {
		        	sql += " and descripcion like ?";
		        }
		        
		        if (material != null) {
		        	sql += " and material like ? ";
		        }
		        
		        if (fechaDesde != null) {
		        	sql += " and fecha_prestamo >= ? ";
		        }
		        
		        if (fechaHasta != null) {
		        	sql += " and fecha_prestamo <= ? ";
		        }	        

		        sentencia = conexion.prepareStatement(sql);
		        sentencia.setString(1, username);
		        
		        int countParametros = 2;
		        
		        if (descripcion != null) {
		        	sentencia.setString(countParametros, "%" + descripcion + "%");
		        	countParametros++;
		        }
		        
		        if (material != null) {
		        	sentencia.setString(countParametros, "%" + material + "%");
		        	countParametros++;
		        }
		        
		        if (fechaDesde != null) {
		        	sentencia.setDate(countParametros, getDateFromString(fechaDesde));
		        	countParametros++;
		        }
		        
		        if (fechaHasta != null) {
		        	sentencia.setDate(countParametros, getDateFromString(fechaHasta));
		        	countParametros++;
		        }
		        
		        
		        
		        resultado = sentencia.executeQuery();

		        // Recorremos los registros obtenidos.
		        while (resultado.next()) {
		        	Prestamo p = new Prestamo();
		        	p.setId(resultado.getInt("id"));
		        	p.setMaterial(resultado.getString("material"));
		        	p.setDescripcion(resultado.getString("descripcion"));
		        	p.setFecha(resultado.getDate("fecha_prestamo"));
		        	p.setUsername(resultado.getString("username"));
		        	l.add(p);  
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
		
	        return l;
	    }

	 
	    
	    
	    
	    
	    public static int insertaPrestamo(PrestamoParaCreacion prestamoData,
	    		String username) throws SQLException {
	    	
	    	int filasAfectadas = 0;
	        Connection conexion = null;
	        PreparedStatement sentencia = null;
	        

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

		        conexion = DriverManager.getConnection(
		                DB_URL, DB_USER, DB_PASSWORD);

		        String sql = "INSERT INTO PRESTAMOS (material, descripcion, fecha_prestamo, username) "
		        		+ " VALUES (?, ?, ?, ?)";
		        

		        sentencia = conexion.prepareStatement(sql);
		        sentencia.setString(1, prestamoData.getMaterial());
		        sentencia.setString(2, prestamoData.getDescripcion());
		        sentencia.setDate(3, prestamoData.getFechaPrestamo());
		        sentencia.setString(4, username);

		        filasAfectadas = sentencia.executeUpdate();
			
			} catch (Exception e) {
				System.out.println("Error de driver");
				e.printStackTrace();
			} finally {
		        // Cerramos los recursos JDBC.
		        sentencia.close();
		        conexion.close();
			}
		
	        return filasAfectadas;
	    }


	    public static int eliminaPrestamo(int id) throws SQLException {
	    	
	    	int filasAfectadas = 0;
	        Connection conexion = null;
	        PreparedStatement sentencia = null;
	        

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

		        conexion = DriverManager.getConnection(
		                DB_URL, DB_USER, DB_PASSWORD);

		        String sql = "DELETE FROM PRESTAMOS WHERE id = ?";
		        

		        sentencia = conexion.prepareStatement(sql);
		        sentencia.setInt(1, id);

		        filasAfectadas = sentencia.executeUpdate();
			
			} catch (Exception e) {
				System.out.println("Error de driver");
				e.printStackTrace();
			} finally {
		        // Cerramos los recursos JDBC.
		        sentencia.close();
		        conexion.close();
			}
		
	        return filasAfectadas;
	    }

	    
	    
	    
	    /**
	     * 
	     * @param strDate
	     * @return
	     */
	    private static java.sql.Date getDateFromString (String strDate) {
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	
	    	java.util.Date utilDate = null;
			try {
				utilDate = sdf.parse(strDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return new java.sql.Date(utilDate.getTime());
	    	
	    }
}

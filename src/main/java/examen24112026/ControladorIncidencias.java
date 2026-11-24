package examen24112026;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.rmrsoft.tutorialJ2eeSpringBoot.examen20261110.PrestamoParaCreacion;





public class ControladorIncidencias {
	private static final String DB_URL =
            "jdbc:mysql://localhost:3306/gestion_incidencias";

    private static final String DB_USER = "root";

    private static final String DB_PASSWORD = "1234";
    
    public static List<Incidencias> findIncidencias(String username, String titulo, String descripcion, String fecha_desde, String fecha_hasta, String estado) throws SQLException {
    	List<Incidencias> l = new ArrayList<Incidencias>();
    	Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

	        conexion = DriverManager.getConnection(
	                DB_URL, DB_USER, DB_PASSWORD);
	        
	        String sql = "SELECT * FROM gestion_incidencias.INCIDENCIAS " 
	        		+ " INNER JOIN gestion_incidencias.CATEGORIAS using id_categoria"
	        		+ " where username = ? "
	        		+ " ORDER BY fecha_creacion ";
	        
	        if (titulo != null) {
	        	sql += " and titulo like ?";
	        }
	        
	        if (descripcion != null) {
	        	sql += " and descripcion like ? ";
	        }
	        
	        if (fecha_desde != null) {
	        	sql += " and fecha_creacion >= ? ";
	        }
	        
	        if (fecha_hasta != null) {
	        	sql += " and fecha_creacion <= ? ";
	        }
	        
	        if (estado != null) {
	        	sql += " and estado like ? ";
	        }
	        

	        sentencia = conexion.prepareStatement(sql);
	        sentencia.setString(1, username);
	        
	        int countParametros = 2;
	        
	        if (titulo != null) {
	        	sentencia.setString(countParametros, "%" + titulo + "%");
	        	countParametros++;
	        }
	        
	        if (descripcion != null) {
	        	sentencia.setString(countParametros, "%" + descripcion + "%");
	        	countParametros++;
	        }
	        
	        if (fecha_desde != null) {
	        	sentencia.setDate(countParametros, getDateFromString(fecha_desde));
	        	countParametros++;
	        }
	        
	        if (fecha_hasta != null) {
	        	sentencia.setDate(countParametros, getDateFromString(fecha_hasta));
	        	countParametros++;
	        }
	        
	        if (estado != null) {
	        	sentencia.setString(countParametros, "%" + estado + "%");
	        	countParametros++;
	        }
	        
	        resultado = sentencia.executeQuery();
	        
	        while (resultado.next()) {
	        	Incidencias i = new Incidencias();
	        	i.setId(resultado.getInt("id"));
	        	i.setTitulo(resultado.getString("titulo"));
	        	i.setDescripcion(resultado.getString("descripcion"));
	        	i.setFecha_creacion(resultado.getDate("fecha_creacion"));
	        	i.setEstado(resultado.getString("estado"));
	        	i.setUsername(resultado.getString("username"));
	        	l.add(i);  
	        }
		

        }catch(Exception e){
        	System.out.println("Error de driver");
			e.printStackTrace();
        }finally {
	        // Cerramos los recursos JDBC.
	        resultado.close();
	        sentencia.close();
	        conexion.close();
		}
        return l;
    }
    
    public static int insertaIncidencia(IncidenciaParaCreacion incidenciaData,
    		String username) throws SQLException {
    	
    	int filasAfectadas = 0;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

	        conexion = DriverManager.getConnection(
	                DB_URL, DB_USER, DB_PASSWORD);

	        String sql = "INSERT INTO INCIDENCIAS (titulo, descripcion, estado, id_categoria, username) "
	        		+ " VALUES (?, ?, ?, ?, ?)";
	        

	        sentencia = conexion.prepareStatement(sql);
	        sentencia.setString(1, incidenciaData.getTitulo());
	        sentencia.setString(2, incidenciaData.getDescripcion());
	        sentencia.setString(3, "ABIERTA");
	        sentencia.setInt(4, incidenciaData.getId_categoria());
	        sentencia.setString(5, username);

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
    
    
    
    public static int modificarIncidencia(int id) throws SQLException {
    	
    	int filasAfectadas = 0;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

	        conexion = DriverManager.getConnection(
	                DB_URL, DB_USER, DB_PASSWORD);

	        String sql = "";
	        

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

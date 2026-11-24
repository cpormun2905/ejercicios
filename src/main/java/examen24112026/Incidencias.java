package examen24112026;

import java.sql.Date;

public class Incidencias {
	protected int id;
	protected String titulo;
	protected String descripcion;
	protected Date fecha_creacion;
	protected String estado;
	protected String username;
	protected int id_categoria;
	
	
	public Incidencias() {
		super();
	}


	public Incidencias(int id, String titulo, String descripcion, Date fecha_creacion, String estado, String username,
			int id_categoria) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha_creacion = fecha_creacion;
		this.estado = estado;
		this.username = username;
		this.id_categoria = id_categoria;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getFecha_creacion() {
		return fecha_creacion;
	}


	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getId_categoria() {
		return id_categoria;
	}


	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	

}

package examen;

import java.sql.Date;

public class Prestamo {
	
	protected int id;
	protected String material;
	protected String descripcion;
	protected Date fecha_pretamo;
	protected String username;
	
	
	public Prestamo() {
		super();
	}


	public Prestamo(int id, String material, String descripcion, Date fecha_pretamo, String username) {
		super();
		this.id = id;
		this.material = material;
		this.descripcion = descripcion;
		this.fecha_pretamo = fecha_pretamo;
		this.username = username;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getFecha_pretamo() {
		return fecha_pretamo;
	}


	public void setFecha_pretamo(Date fecha_pretamo) {
		this.fecha_pretamo = fecha_pretamo;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	

}

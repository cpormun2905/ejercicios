package examen;

import java.sql.Date;

public class PrestamoParaCreacion {
	protected String material;
	protected String descripcion;
	protected Date fecha;
	
	
	public PrestamoParaCreacion() {
		super();
	}


	public PrestamoParaCreacion(String material, String descripcion, Date fecha) {
		super();
		this.material = material;
		this.descripcion = descripcion;
		this.fecha = fecha;
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


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}

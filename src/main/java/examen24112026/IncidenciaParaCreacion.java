package examen24112026;

public class IncidenciaParaCreacion {
	protected String titulo;
	protected String descripcion;
	protected int id_categoria;
	
	
	public IncidenciaParaCreacion() {
		super();
	}


	public IncidenciaParaCreacion(String titulo, String descripcion, int id_categoria) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.id_categoria = id_categoria;
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


	public int getId_categoria() {
		return id_categoria;
	}


	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

}

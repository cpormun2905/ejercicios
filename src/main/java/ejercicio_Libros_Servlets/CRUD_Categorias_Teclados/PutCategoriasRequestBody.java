package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

public class PutCategoriasRequestBody {

	// Integer (y no int) para poder detectar si el JSON no incluye el campo "id"
	private Integer id;
	private String descripcion;

	public PutCategoriasRequestBody() {
		super();
	}

	public PutCategoriasRequestBody(Integer id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

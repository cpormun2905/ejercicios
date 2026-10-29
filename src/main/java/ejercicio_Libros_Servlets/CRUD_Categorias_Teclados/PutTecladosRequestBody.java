package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

public class PutTecladosRequestBody {

	private Integer id;
	private String color;
	private String idioma;
	private Integer idCategoria;

	public PutTecladosRequestBody() {
		super();
	}

	public PutTecladosRequestBody(Integer id, String color, String idioma, Integer idCategoria) {
		super();
		this.id = id;
		this.color = color;
		this.idioma = idioma;
		this.idCategoria = idCategoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

}

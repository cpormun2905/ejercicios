package ejercicio_Libros_Servlets.CRUD_Categorias_Teclados;

public class PostTecladosRequestBody {

	private String color;
	private String idioma;
	// Integer (y no int) para poder detectar si el JSON no incluye el campo
	private Integer idCategoria;

	public PostTecladosRequestBody() {
		super();
	}

	public PostTecladosRequestBody(String color, String idioma, Integer idCategoria) {
		super();
		this.color = color;
		this.idioma = idioma;
		this.idCategoria = idCategoria;
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

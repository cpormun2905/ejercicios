package examen24112026;

public class Categorias {
	
	protected int id;
	protected String nombre;
	
	
	public Categorias() {
		super();
	}


	public Categorias(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}

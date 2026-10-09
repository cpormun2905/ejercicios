package ejercicio_Libros_Servlets.CRUD_Libros;

public class Libro {

    private int id;
    private String titulo;
    private int anioPublicacion;

    public Libro(int id, String titulo, int anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getAnioPublicacion() { return anioPublicacion; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }
}
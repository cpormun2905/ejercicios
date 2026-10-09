package ejercicio_Libros_Servlets;

/**
 * Representa una persona que vamos a gestionar mediante los Servlets.
 */
public class Persona {

    // Identificador único de la persona.
    private int id;

    // Nombre de la persona.
    private String nombre;

    // Edad de la persona.
    private int edad;

    /**
     * Crea una persona con los datos indicados.
     */
    public Persona(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
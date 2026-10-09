package ejercicio_Libros_Servlets;


public class XmlUtil {

    private XmlUtil() {
    }

    public static String escapar(String texto) {

        if (texto == null) {
            return "";
        }

        return texto
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }


    public static String persona(Persona persona) {

        return """
                <persona>
                    <id>%d</id>
                    <nombre>%s</nombre>
                    <edad>%d</edad>
                </persona>
                """.formatted(
                persona.getId(),
                escapar(persona.getNombre()),
                persona.getEdad()
        );
    }


    public static String error(String mensaje) {

        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <error>
                    <mensaje>%s</mensaje>
                </error>
                """.formatted(escapar(mensaje));
    }
}
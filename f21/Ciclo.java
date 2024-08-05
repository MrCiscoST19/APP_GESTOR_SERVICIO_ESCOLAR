package f21;

// Clase Ciclo que representa un ciclo en el sistema
public class Ciclo {

    // Atributo que almacena el ID del ciclo
    private int id;
    // Atributo que almacena el nombre del ciclo
    private String nombre;

    // Constructor que inicializa un ciclo con un ID y un nombre
    public Ciclo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Método para obtener el ID del ciclo
    public int getId() {
        return id;
    }

    // Método para obtener el nombre del ciclo
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el ID del ciclo
    public void setId(int id) {
        this.id = id;
    }

    // Método para establecer el nombre del ciclo
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

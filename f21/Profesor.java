package f21; // Declaración del paquete

// Clase Profesor que representa un profesor en el sistema
public class Profesor {

    // Atributo que almacena el ID del profesor
    private int id;
    // Atributo que almacena el nombre del profesor
    private String nombre;

    // Constructor que inicializa un profesor con un ID y un nombre
    public Profesor(int id, String nombre) {
        this.id = id; // Inicializa el ID del profesor
        this.nombre = nombre; // Inicializa el nombre del profesor
    }

    // Método para obtener el ID del profesor
    public int getId() {
        return id; // Devuelve el ID del profesor
    }

    // Método para obtener el nombre del profesor
    public String getNombre() {
        return nombre; // Devuelve el nombre del profesor
    }

    // Método para establecer el ID del profesor
    public void setId(int id) {
        this.id = id; // Establece el ID del profesor
    }

    // Método para establecer el nombre del profesor
    public void setNombre(String nombre) {
        this.nombre = nombre; // Establece el nombre del profesor
    }
}

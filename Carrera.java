package f21;

// Clase Carrera que representa una carrera en el sistema
public class Carrera {

    // Atributo que almacena el ID de la carrera
    private int id;
    // Atributo que almacena el nombre de la carrera
    private String nombre;

    // Constructor que inicializa una carrera con un ID y un nombre
    public Carrera(int id, String nombre) {
        this.id = id; // Asigna el ID de la carrera
        this.nombre = nombre; // Asigna el nombre de la carrera
    }

    // Método para obtener el ID de la carrera
    public int getId() {
        return id; // Devuelve el ID de la carrera
    }

    // Método para obtener el nombre de la carrera
    public String getNombre() {
        return nombre; // Devuelve el nombre de la carrera
    }

    // Método para establecer el ID de la carrera
    public void setId(int id) {
        this.id = id; // Actualiza el ID de la carrera
    }

    // Método para establecer el nombre de la carrera
    public void setNombre(String nombre) {
        this.nombre = nombre; // Actualiza el nombre de la carrera
    }
}

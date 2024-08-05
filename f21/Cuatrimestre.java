package f21;

// Clase Cuatrimestre que representa un cuatrimestre en el sistema
public class Cuatrimestre {

    // Atributo que almacena el ID del cuatrimestre
    private int id;
    // Atributo que almacena el nombre del cuatrimestre
    private String nombre;

    // Constructor que inicializa un cuatrimestre con un ID y un nombre
    public Cuatrimestre(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Método para obtener el ID del cuatrimestre
    public int getId() {
        return id;
    }

    // Método para obtener el nombre del cuatrimestre
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el ID del cuatrimestre
    public void setId(int id) {
        this.id = id;
    }

    // Método para establecer el nombre del cuatrimestre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

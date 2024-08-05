package f21; // Declaración del paquete

// Clase Materia que representa una materia en el sistema
public class Materia {
    // Atributo que almacena el ID de la materia
    private int id;
    // Atributo que almacena el nombre de la materia
    private String nombre;
    // Atributo que almacena la descripción de la materia
    private String descripcion;
    // Atributo que almacena la unidad de la materia
    private String unidad;

    // Constructor para inicializar una materia con ID, nombre, descripción y unidad
    public Materia(int id, String nombre, String descripcion, String unidad) {
        this.id = id; // Inicializa el ID de la materia
        this.nombre = nombre; // Inicializa el nombre de la materia
        this.descripcion = descripcion; // Inicializa la descripción de la materia
        this.unidad = unidad; // Inicializa la unidad de la materia
    }

    // Método para obtener el ID de la materia
    public int getId() {
        return id; // Devuelve el ID de la materia
    }

    // Método para establecer el ID de la materia
    public void setId(int id) {
        this.id = id; // Establece el ID de la materia
    }

    // Método para obtener el nombre de la materia
    public String getNombre() {
        return nombre; // Devuelve el nombre de la materia
    }

    // Método para establecer el nombre de la materia
    public void setNombre(String nombre) {
        this.nombre = nombre; // Establece el nombre de la materia
    }

    // Método para obtener la descripción de la materia
    public String getDescripcion() {
        return descripcion; // Devuelve la descripción de la materia
    }

    // Método para establecer la descripción de la materia
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; // Establece la descripción de la materia
    }

    // Método para obtener la unidad de la materia
    public String getUnidad() {
        return unidad; // Devuelve la unidad de la materia
    }

    // Método para establecer la unidad de la materia
    public void setUnidad(String unidad) {
        this.unidad = unidad; // Establece la unidad de la materia
    }
}

package f21;

// Clase Estado que representa un estatus en el sistema
public class Estado {

    // Atributo que almacena el ID del estatus
    private int id;
    // Atributo que almacena el nombre del estatus
    private String nombre;

    // Constructor que inicializa un estatus con un ID y un nombre
    public Estado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Método para obtener el ID del estatus
    public int getId() {
        return id;
    }

    // Método para obtener el nombre del estatus
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el ID del estatus
    public void setId(int id) {
        this.id = id;
    }

    // Método para establecer el nombre del estatus
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

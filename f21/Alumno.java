package f21;  // Paquete que contiene la clase Alumno

public class Alumno {  // Definición de la clase Alumno

    private int id;  // Atributo para almacenar el ID del alumno
    private String nombre;  // Atributo para almacenar el nombre del alumno
    private Grupo grupo;  // Atributo para almacenar el grupo al que pertenece el alumno
    private Estado estado;  // Atributo para almacenar el estado del alumno

    // Constructor de la clase Alumno
    public Alumno(int id, String nombre, Grupo grupo, Estado estado) {
        this.id = id;  // Inicializa el ID del alumno
        this.nombre = nombre;  // Inicializa el nombre del alumno
        this.grupo = grupo;  // Inicializa el grupo del alumno
        this.estado = estado;  // Inicializa el estado del alumno
    }

    // Métodos getter y setter para acceder y modificar los atributos de la clase
    // Getter para el ID del alumno
    public int getId() {
        return id;
    }

    // Setter para el ID del alumno
    public void setId(int id) {
        this.id = id;
    }

    // Getter para el nombre del alumno
    public String getNombre() {
        return nombre;
    }

    // Setter para el nombre del alumno
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para el grupo del alumno
    public Grupo getGrupo() {
        return grupo;
    }

    // Setter para el grupo del alumno
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    // Getter para el estado del alumno
    public Estado getEstado() {
        return estado;
    }

    // Setter para el estado del alumno
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}

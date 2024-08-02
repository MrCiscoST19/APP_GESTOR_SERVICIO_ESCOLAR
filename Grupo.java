package f21;

// Clase que representa un grupo en el sistema
public class Grupo {

    // Atributos de la clase
    private int id; // Identificador único del grupo
    private String nombre; // Nombre del grupo
    private CC cicloCuatri; // Objeto que representa el ciclo y cuatrimestre del grupo
    private Carrera carrera; // Objeto que representa la carrera del grupo

    // Constructor de la clase Grupo
    public Grupo(int id, String nombre, CC cicloCuatri, Carrera carrera) {
        this.id = id; // Inicializar el ID del grupo
        this.nombre = nombre; // Inicializar el nombre del grupo
        this.cicloCuatri = cicloCuatri; // Inicializar el ciclo y cuatrimestre del grupo
        this.carrera = carrera; // Inicializar la carrera del grupo
    }

    // Método para obtener el ID del grupo
    public int getId() {
        return id;
    }

    // Método para establecer el ID del grupo
    public void setId(int id) {
        this.id = id;
    }

    // Método para obtener el nombre del grupo
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el nombre del grupo
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para obtener el ciclo y cuatrimestre del grupo
    public CC getCicloCuatri() {
        return cicloCuatri;
    }

    // Método para establecer el ciclo y cuatrimestre del grupo
    public void setCicloCuatri(CC cicloCuatri) {
        this.cicloCuatri = cicloCuatri;
    }

    // Método para obtener la carrera del grupo
    public Carrera getCarrera() {
        return carrera;
    }

    // Método para establecer la carrera del grupo
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}

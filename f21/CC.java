package f21;

// Clase CC que representa una relación entre un Ciclo y un Cuatrimestre con un identificador
public class CC {
    
    // Atributo que almacena el ID de la relación
    private int id;
    // Atributo que almacena el objeto Ciclo asociado a esta relación
    private Ciclo ciclo;
    // Atributo que almacena el objeto Cuatrimestre asociado a esta relación
    private Cuatrimestre cuatri;

    // Constructor que inicializa una instancia de CC con el ID, el Ciclo y el Cuatrimestre proporcionados
    public CC(int id, Ciclo ciclo, Cuatrimestre cuatri) {
        this.id = id;
        this.ciclo = ciclo;
        this.cuatri = cuatri;
    }

    // Método para obtener el ID de la relación
    public int getId() {
        return id;
    }

    // Método para establecer el ID de la relación
    public void setId(int id) {
        this.id = id;
    }

    // Método para obtener el Ciclo asociado a esta relación
    public Ciclo getCiclo() {
        return ciclo;
    }

    // Método para establecer el Ciclo asociado a esta relación
    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    // Método para obtener el Cuatrimestre asociado a esta relación
    public Cuatrimestre getCuatrimestre() {
        return cuatri;
    }

    // Método para establecer el Cuatrimestre asociado a esta relación
    public void setCuatrimestre(Cuatrimestre cuatri) {
        this.cuatri = cuatri;
    }

    // Método que devuelve una representación en cadena del objeto CC
   
}

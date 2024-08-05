package f21;  // Declara el paquete al que pertenece la clase.

public class Asignacion {  // Define la clase pública Asignacion.

    private int id;  // Atributo privado que almacena el identificador de la asignación.
    private Grupo grupo;  // Atributo privado que almacena el objeto Grupo asociado a la asignación.
    private Carrera carrera;  // Atributo privado que almacena el objeto Carrera asociado a la asignación.
    private Ciclo ciclo;  // Atributo privado que almacena el objeto Ciclo asociado a la asignación.
    private Cuatrimestre cuatrimestre;  // Atributo privado que almacena el objeto Cuatrimestre asociado a la asignación.
    private Profesor profesor;  // Atributo privado que almacena el objeto Profesor asociado a la asignación.
    private Materia materia;  // Atributo privado que almacena el objeto Materia asociado a la asignación.

    // Constructor de la clase Asignacion que inicializa todos los atributos.
    public Asignacion(int id, Grupo grupo, Carrera carrera, Ciclo ciclo, Cuatrimestre cuatrimestre, Profesor profesor, Materia materia) {
        this.id = id;  // Inicializa el atributo id con el valor pasado al constructor.
        this.grupo = grupo;  // Inicializa el atributo grupo con el objeto Grupo pasado al constructor.
        this.carrera = carrera;  // Inicializa el atributo carrera con el objeto Carrera pasado al constructor.
        this.ciclo = ciclo;  // Inicializa el atributo ciclo con el objeto Ciclo pasado al constructor.
        this.cuatrimestre = cuatrimestre;  // Inicializa el atributo cuatrimestre con el objeto Cuatrimestre pasado al constructor.
        this.profesor = profesor;  // Inicializa el atributo profesor con el objeto Profesor pasado al constructor.
        this.materia = materia;  // Inicializa el atributo materia con el objeto Materia pasado al constructor.
    }

    // Método getter para obtener el valor del atributo id.
    public int getId() {
        return id;  // Devuelve el valor del atributo id.
    }

    // Método setter para establecer el valor del atributo id.
    public void setId(int id) {
        this.id = id;  // Establece el valor del atributo id.
    }

    // Método getter para obtener el valor del atributo grupo.
    public Grupo getGrupo() {
        return grupo;  // Devuelve el objeto Grupo asociado a la asignación.
    }

    // Método setter para establecer el valor del atributo grupo.
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;  // Establece el objeto Grupo asociado a la asignación.
    }

    // Método getter para obtener el valor del atributo carrera.
    public Carrera getCarrera() {
        return carrera;  // Devuelve el objeto Carrera asociado a la asignación.
    }

    // Método setter para establecer el valor del atributo carrera.
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;  // Establece el objeto Carrera asociado a la asignación.
    }

    // Método getter para obtener el valor del atributo ciclo.
    public Ciclo getCiclo() {
        return ciclo;  // Devuelve el objeto Ciclo asociado a la asignación.
    }

    // Método setter para establecer el valor del atributo ciclo.
    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;  // Establece el objeto Ciclo asociado a la asignación.
    }

    // Método getter para obtener el valor del atributo cuatrimestre.
    public Cuatrimestre getCuatrimestre() {
        return cuatrimestre;  // Devuelve el objeto Cuatrimestre asociado a la asignación.
    }

    // Método setter para establecer el valor del atributo cuatrimestre.
    public void setCuatrimestre(Cuatrimestre cuatrimestre) {
        this.cuatrimestre = cuatrimestre;  // Establece el objeto Cuatrimestre asociado a la asignación.
    }

    // Método getter para obtener el valor del atributo profesor.
    public Profesor getProfesor() {
        return profesor;  // Devuelve el objeto Profesor asociado a la asignación.
    }

    // Método setter para establecer el valor del atributo profesor.
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;  // Establece el objeto Profesor asociado a la asignación.
    }

    // Método getter para obtener el valor del atributo materia.
    public Materia getMateria() {
        return materia;  // Devuelve el objeto Materia asociado a la asignación.
    }

    // Método setter para establecer el valor del atributo materia.
    public void setMateria(Materia materia) {
        this.materia = materia;  // Establece el objeto Materia asociado a la asignación.
    }
}

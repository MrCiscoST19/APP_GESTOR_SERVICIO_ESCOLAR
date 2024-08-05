package f21;  // Declara el paquete al que pertenece esta clase.

public class Calificacion {  // Define la clase Calificacion.

    private int id;  // Atributo privado para almacenar el identificador de la calificación.
    private String alumno;  // Atributo privado para almacenar el nombre del alumno.
    private String estado;  // Atributo privado para almacenar el estado del alumno.
    private String grupo;  // Atributo privado para almacenar el nombre del grupo.
    private String ciclo;  // Atributo privado para almacenar el nombre del ciclo.
    private String cuatrimestre;  // Atributo privado para almacenar el nombre del cuatrimestre.
    private String carrera;  // Atributo privado para almacenar el nombre de la carrera.
    private String materia;  // Atributo privado para almacenar el nombre de la materia.
    private String profesor;  // Atributo privado para almacenar el nombre del profesor.
    private String unidad;  // Atributo privado para almacenar el nombre de la unidad.
    private String calificacion;  // Atributo privado para almacenar la calificación del alumno.
    private int idAlumno;  // Atributo privado para almacenar el ID del alumno.
    private int idAsigna;  // Atributo privado para almacenar el ID de la asignación.

    // Constructor de la clase Calificacion.
    public Calificacion(int id, String alumno, String estado, String grupo, String ciclo, String cuatrimestre, 
                        String carrera, String materia, String profesor, String unidad, String calificacion,
                        int idAlumno, int idAsigna) {  // Constructor modificado para incluir nuevos atributos.
        this.id = id;  // Inicializa el atributo id con el valor proporcionado.
        this.alumno = alumno;  // Inicializa el atributo alumno con el valor proporcionado.
        this.estado = estado;  // Inicializa el atributo estado con el valor proporcionado.
        this.grupo = grupo;  // Inicializa el atributo grupo con el valor proporcionado.
        this.ciclo = ciclo;  // Inicializa el atributo ciclo con el valor proporcionado.
        this.cuatrimestre = cuatrimestre;  // Inicializa el atributo cuatrimestre con el valor proporcionado.
        this.carrera = carrera;  // Inicializa el atributo carrera con el valor proporcionado.
        this.materia = materia;  // Inicializa el atributo materia con el valor proporcionado.
        this.profesor = profesor;  // Inicializa el atributo profesor con el valor proporcionado.
        this.unidad = unidad;  // Inicializa el atributo unidad con el valor proporcionado.
        this.calificacion = calificacion;  // Inicializa el atributo calificacion con el valor proporcionado.
        this.idAlumno = idAlumno;  // Inicializa el atributo idAlumno con el valor proporcionado.
        this.idAsigna = idAsigna;  // Inicializa el atributo idAsigna con el valor proporcionado.
    }

    // Métodos Getter y Setter para el atributo id.
    public int getId() {
        return id;  // Retorna el valor del atributo id.
    }

    public void setId(int id) {
        this.id = id;  // Asigna un nuevo valor al atributo id.
    }

    // Métodos Getter y Setter para el atributo alumno.
    public String getAlumno() {
        return alumno;  // Retorna el valor del atributo alumno.
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;  // Asigna un nuevo valor al atributo alumno.
    }

    // Métodos Getter y Setter para el atributo estado.
    public String getEstado() {
        return estado;  // Retorna el valor del atributo estado.
    }

    public void setEstado(String estado) {
        this.estado = estado;  // Asigna un nuevo valor al atributo estado.
    }

    // Métodos Getter y Setter para el atributo grupo.
    public String getGrupo() {
        return grupo;  // Retorna el valor del atributo grupo.
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;  // Asigna un nuevo valor al atributo grupo.
    }

    // Métodos Getter y Setter para el atributo ciclo.
    public String getCiclo() {
        return ciclo;  // Retorna el valor del atributo ciclo.
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;  // Asigna un nuevo valor al atributo ciclo.
    }

    // Métodos Getter y Setter para el atributo cuatrimestre.
    public String getCuatrimestre() {
        return cuatrimestre;  // Retorna el valor del atributo cuatrimestre.
    }

    public void setCuatrimestre(String cuatrimestre) {
        this.cuatrimestre = cuatrimestre;  // Asigna un nuevo valor al atributo cuatrimestre.
    }

    // Métodos Getter y Setter para el atributo carrera.
    public String getCarrera() {
        return carrera;  // Retorna el valor del atributo carrera.
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;  // Asigna un nuevo valor al atributo carrera.
    }

    // Métodos Getter y Setter para el atributo materia.
    public String getMateria() {
        return materia;  // Retorna el valor del atributo materia.
    }

    public void setMateria(String materia) {
        this.materia = materia;  // Asigna un nuevo valor al atributo materia.
    }

    // Métodos Getter y Setter para el atributo profesor.
    public String getProfesor() {
        return profesor;  // Retorna el valor del atributo profesor.
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;  // Asigna un nuevo valor al atributo profesor.
    }

    // Métodos Getter y Setter para el atributo unidad.
    public String getUnidad() {
        return unidad;  // Retorna el valor del atributo unidad.
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;  // Asigna un nuevo valor al atributo unidad.
    }

    // Métodos Getter y Setter para el atributo calificacion.
    public String getCalificacion() {
        return calificacion;  // Retorna el valor del atributo calificacion.
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;  // Asigna un nuevo valor al atributo calificacion.
    }

    // Métodos Getter y Setter para el atributo idAlumno.
    public int getIdAlumno() {
        return idAlumno;  // Retorna el valor del atributo idAlumno.
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;  // Asigna un nuevo valor al atributo idAlumno.
    }

    // Métodos Getter y Setter para el atributo idAsigna.
    public int getIdAsigna() {
        return idAsigna;  // Retorna el valor del atributo idAsigna.
    }

    public void setIdAsigna(int idAsigna) {
        this.idAsigna = idAsigna;  // Asigna un nuevo valor al atributo idAsigna.
    }
}

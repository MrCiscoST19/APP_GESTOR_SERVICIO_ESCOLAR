package f21;

public class Asignacion {
    
    private int id;
    private Grupo grupo;
    private Carrera carrera;
    private Ciclo ciclo;
    private Cuatrimestre cuatrimestre;
    private Profesor profesor;
    private Materia materia;

    public Asignacion(int id, Grupo grupo, Carrera carrera, Ciclo ciclo, Cuatrimestre cuatrimestre, Profesor profesor, Materia materia) {
        this.id = id;
        this.grupo = grupo;
        this.carrera = carrera;
        this.ciclo = ciclo;
        this.cuatrimestre = cuatrimestre;
        this.profesor = profesor;
        this.materia = materia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Cuatrimestre getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(Cuatrimestre cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}

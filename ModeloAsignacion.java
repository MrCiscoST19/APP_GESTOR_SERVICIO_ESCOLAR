package f21;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloAsignacion extends Conexion {

    private ArrayList<Asignacion> asignaciones = new ArrayList<>();

    // Listar todas las asignaciones
    public void listarAsignaciones() {
        asignaciones.clear();
        try {
            conectar();
            String sql = "SELECT asignaciones.id AS id, grupos.nombre AS grupo, carreras.nombre AS carrera, "
                    + "ciclos.nombre AS ciclo, cuatrimestres.nombre AS cuatrimestre, profesores.nombre AS profesor, "
                    + "materias.nombre AS materia, materias.descripcion AS descripcion, materias.unidad AS unidad "
                    + "FROM asignaciones "
                    + "JOIN grupos ON asignaciones.id_grupo = grupos.id "
                    + "JOIN carreras ON grupos.id_carrera = carreras.id "
                    + "JOIN profesores ON asignaciones.id_profesor = profesores.id "
                    + "JOIN materias ON asignaciones.id_materia = materias.id "
                    + "JOIN ciclo_cuatri ON grupos.id_ciclo_cuatri = ciclo_cuatri.id "
                    + "JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id "
                    + "JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id "
                    + "ORDER BY asignaciones.id;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String grupoNombre = rs.getString("grupo");
                String carreraNombre = rs.getString("carrera");
                String cicloNombre = rs.getString("ciclo");
                String cuatriNombre = rs.getString("cuatrimestre");
                String profesorNombre = rs.getString("profesor");
                String materiaNombre = rs.getString("materia");
                String descripcion = rs.getString("descripcion");
                String unidad = rs.getString("unidad");

                // Asegúrate de que los constructores correspondan con los datos que tienes
                Grupo grupo = new Grupo(-1, grupoNombre, null, null); // Cambiar -1 por el id real si se tiene
                Carrera carrera = new Carrera(-1, carreraNombre);
                Ciclo ciclo = new Ciclo(-1, cicloNombre);
                Cuatrimestre cuatrimestre = new Cuatrimestre(-1, cuatriNombre);
                Profesor profesor = new Profesor(-1, profesorNombre);
                Materia materia = new Materia(-1, materiaNombre, descripcion, unidad);

                Asignacion asignacion = new Asignacion(id, grupo, carrera, ciclo, cuatrimestre, profesor, materia);
                asignaciones.add(asignacion);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar asignaciones: " + ex.getMessage());
        } finally {
            desconectar();
        }
    }

    // Obtener la lista de asignaciones
    public ArrayList<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    // Crear una nueva asignación
    public void Crear(int idGrupo, int idProfesor, int idMateria) {
        try {
            conectar();
            String sql = "INSERT INTO asignaciones (id_grupo, id_profesor, id_materia) VALUES (?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, idGrupo);
            ps.setInt(2, idProfesor);
            ps.setInt(3, idMateria);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al crear asignación: " + ex.getMessage());
        } finally {
            desconectar();
        }
    }

    // Leer una asignación por su ID
    public Asignacion Leer(int id) {
        Asignacion asignacion = null;
        try {
            conectar();
            String sql = "SELECT asignaciones.id AS id, grupos.nombre AS grupo, carreras.nombre AS carrera, "
                    + "ciclos.nombre AS ciclo, cuatrimestres.nombre AS cuatrimestre, profesores.nombre AS profesor, "
                    + "materias.nombre AS materia, materias.descripcion AS descripcion, materias.unidad AS unidad "
                    + "FROM asignaciones "
                    + "JOIN grupos ON asignaciones.id_grupo = grupos.id "
                    + "JOIN carreras ON grupos.id_carrera = carreras.id "
                    + "JOIN profesores ON asignaciones.id_profesor = profesores.id "
                    + "JOIN materias ON asignaciones.id_materia = materias.id "
                    + "JOIN ciclo_cuatri ON grupos.id_ciclo_cuatri = ciclo_cuatri.id "
                    + "JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id "
                    + "JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id "
                    + "WHERE asignaciones.id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int asignacionId = rs.getInt("id");
                String grupoNombre = rs.getString("grupo");
                String carreraNombre = rs.getString("carrera");
                String cicloNombre = rs.getString("ciclo");
                String cuatriNombre = rs.getString("cuatrimestre");
                String profesorNombre = rs.getString("profesor");
                String materiaNombre = rs.getString("materia");
                String descripcion = rs.getString("descripcion");
                String unidad = rs.getString("unidad");

                Grupo grupo = new Grupo(-1, grupoNombre, null, null); // Cambiar -1 por el id real si se tiene
                Carrera carrera = new Carrera(-1, carreraNombre);
                Ciclo ciclo = new Ciclo(-1, cicloNombre);
                Cuatrimestre cuatrimestre = new Cuatrimestre(-1, cuatriNombre);
                Profesor profesor = new Profesor(-1, profesorNombre);
                Materia materia = new Materia(-1, materiaNombre, descripcion, unidad);

                asignacion = new Asignacion(asignacionId, grupo, carrera, ciclo, cuatrimestre, profesor, materia);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer asignación: " + ex.getMessage());
        } finally {
            desconectar();
        }
        return asignacion;
    }

    // Actualizar una asignación existente
    public void Actualizar(int idActual, int idNuevo, int nuevoIdGrupo, int nuevoIdProfesor, int nuevoIdMateria) {
        try {
            conectar();

            // Verificar si el nuevo ID ya existe (si es diferente del ID actual)
            if (idActual != idNuevo) {
                String sqlCheck = "SELECT COUNT(*) FROM asignaciones WHERE id = ?";
                try (PreparedStatement psCheck = cnx.prepareStatement(sqlCheck)) {
                    psCheck.setInt(1, idNuevo);
                    try (ResultSet rsCheck = psCheck.executeQuery()) {
                        rsCheck.next();
                        if (rsCheck.getInt(1) > 0) {
                            System.out.println("Error: El nuevo ID ya existe.");
                            return;
                        }
                    }
                }
            }

            // Verificar existencia de nuevo ID de grupo
            String sqlCheckGrupo = "SELECT COUNT(*) FROM grupos WHERE id = ?";
            try (PreparedStatement psCheckGrupo = cnx.prepareStatement(sqlCheckGrupo)) {
                psCheckGrupo.setInt(1, nuevoIdGrupo);
                try (ResultSet rsCheckGrupo = psCheckGrupo.executeQuery()) {
                    rsCheckGrupo.next();
                    if (rsCheckGrupo.getInt(1) == 0) {
                        System.out.println("Error: El ID del Grupo no existe.");
                        return;
                    }
                }
            }

            // Verificar existencia de nuevo ID de profesor
            String sqlCheckProfesor = "SELECT COUNT(*) FROM profesores WHERE id = ?";
            try (PreparedStatement psCheckProfesor = cnx.prepareStatement(sqlCheckProfesor)) {
                psCheckProfesor.setInt(1, nuevoIdProfesor);
                try (ResultSet rsCheckProfesor = psCheckProfesor.executeQuery()) {
                    rsCheckProfesor.next();
                    if (rsCheckProfesor.getInt(1) == 0) {
                        System.out.println("Error: El ID del Profesor no existe.");
                        return;
                    }
                }
            }

            // Verificar existencia de nuevo ID de materia
            String sqlCheckMateria = "SELECT COUNT(*) FROM materias WHERE id = ?";
            try (PreparedStatement psCheckMateria = cnx.prepareStatement(sqlCheckMateria)) {
                psCheckMateria.setInt(1, nuevoIdMateria);
                try (ResultSet rsCheckMateria = psCheckMateria.executeQuery()) {
                    rsCheckMateria.next();
                    if (rsCheckMateria.getInt(1) == 0) {
                        System.out.println("Error: El ID de la Materia no existe.");
                        return;
                    }
                }
            }

            // Consulta SQL para actualizar la asignación
            String sqlUpdate = "UPDATE asignaciones SET id = ?, id_grupo = ?, id_profesor = ?, id_materia = ? WHERE id = ?";
            try (PreparedStatement psUpdate = cnx.prepareStatement(sqlUpdate)) {
                psUpdate.setInt(1, idNuevo);
                psUpdate.setInt(2, nuevoIdGrupo);
                psUpdate.setInt(3, nuevoIdProfesor);
                psUpdate.setInt(4, nuevoIdMateria);
                psUpdate.setInt(5, idActual);
                psUpdate.executeUpdate();
                System.out.println("Asignación actualizada exitosamente.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al actualizar asignación: " + ex.getMessage());
        } finally {
            desconectar();
        }
    }

    // Eliminar una asignación por su ID
    public void Eliminar(int id) {
        try {
            conectar();
            String sql = "DELETE FROM asignaciones WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar asignación: " + ex.getMessage());
        } finally {
            desconectar();
        }
    }
}

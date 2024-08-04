package f21;  // Declara el paquete al que pertenece la clase.

import java.sql.PreparedStatement;  // Importa la clase PreparedStatement para la ejecución de consultas SQL.
import java.sql.ResultSet;  // Importa la clase ResultSet para manejar los resultados de una consulta SQL.
import java.sql.SQLException;  // Importa la clase SQLException para manejar excepciones de SQL.
import java.util.ArrayList;  // Importa la clase ArrayList para manejar una lista de objetos Asignacion.

public class ModeloAsignacion extends Conexion {  // Define la clase pública ModeloAsignacion que extiende de Conexion.

    private ArrayList<Asignacion> asignaciones = new ArrayList<>();  // Lista para almacenar objetos Asignacion.

    // Método para listar todas las asignaciones desde la base de datos.
    public void listarAsignaciones() {
        asignaciones.clear();  // Limpia la lista de asignaciones antes de cargar nuevos datos.
        try {
            conectar();  // Llama al método conectar para establecer la conexión con la base de datos.
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
                    + "ORDER BY asignaciones.id;";  // Consulta SQL para seleccionar las asignaciones con sus detalles.
            PreparedStatement ps = cnx.prepareStatement(sql);  // Prepara la consulta SQL.
            ResultSet rs = ps.executeQuery();  // Ejecuta la consulta y obtiene los resultados.
            while (rs.next()) {  // Itera sobre cada fila del ResultSet.
                int id = rs.getInt("id");  // Obtiene el id de la asignación.
                String grupoNombre = rs.getString("grupo");  // Obtiene el nombre del grupo.
                String carreraNombre = rs.getString("carrera");  // Obtiene el nombre de la carrera.
                String cicloNombre = rs.getString("ciclo");  // Obtiene el nombre del ciclo.
                String cuatriNombre = rs.getString("cuatrimestre");  // Obtiene el nombre del cuatrimestre.
                String profesorNombre = rs.getString("profesor");  // Obtiene el nombre del profesor.
                String materiaNombre = rs.getString("materia");  // Obtiene el nombre de la materia.
                String descripcion = rs.getString("descripcion");  // Obtiene la descripción de la materia.
                String unidad = rs.getString("unidad");  // Obtiene la unidad de la materia.

                // Crea nuevos objetos con los datos obtenidos.
                Grupo grupo = new Grupo(-1, grupoNombre, null, null);  // Crea un objeto Grupo. Usa -1 para id, ya que no está disponible.
                Carrera carrera = new Carrera(-1, carreraNombre);  // Crea un objeto Carrera.
                Ciclo ciclo = new Ciclo(-1, cicloNombre);  // Crea un objeto Ciclo.
                Cuatrimestre cuatrimestre = new Cuatrimestre(-1, cuatriNombre);  // Crea un objeto Cuatrimestre.
                Profesor profesor = new Profesor(-1, profesorNombre);  // Crea un objeto Profesor.
                Materia materia = new Materia(-1, materiaNombre, descripcion, unidad);  // Crea un objeto Materia.

                // Crea una nueva asignación con los objetos creados y la id.
                Asignacion asignacion = new Asignacion(id, grupo, carrera, ciclo, cuatrimestre, profesor, materia);
                asignaciones.add(asignacion);  // Agrega la asignación a la lista.
            }
        } catch (SQLException ex) {  // Captura excepciones SQL.
            System.out.println("Error al listar asignaciones: " + ex.getMessage());  // Muestra el mensaje de error.
        } finally {
            desconectar();  // Llama al método desconectar para cerrar la conexión con la base de datos.
        }
    }

    // Método para obtener la lista de asignaciones.
    public ArrayList<Asignacion> getAsignaciones() {
        return asignaciones;  // Devuelve la lista de asignaciones.
    }

    // Método para crear una nueva asignación en la base de datos.
    public void Crear(int idGrupo, int idProfesor, int idMateria) {
        try {
            conectar();  // Llama al método conectar para establecer la conexión con la base de datos.
            String sql = "INSERT INTO asignaciones (id_grupo, id_profesor, id_materia) VALUES (?, ?, ?)";  // Consulta SQL para insertar una nueva asignación.
            PreparedStatement ps = cnx.prepareStatement(sql);  // Prepara la consulta SQL.
            ps.setInt(1, idGrupo);  // Establece el valor del id_grupo.
            ps.setInt(2, idProfesor);  // Establece el valor del id_profesor.
            ps.setInt(3, idMateria);  // Establece el valor del id_materia.
            ps.executeUpdate();  // Ejecuta la consulta para insertar la nueva asignación.
        } catch (SQLException ex) {  // Captura excepciones SQL.
            System.out.println("Error al crear asignacion: " + ex.getMessage());  // Muestra el mensaje de error.
        } finally {
            desconectar();  // Llama al método desconectar para cerrar la conexión con la base de datos.
        }
    }

    // Método para leer una asignación por su ID.
    public Asignacion Leer(int id) {
        Asignacion asignacion = null;  // Inicializa una variable Asignacion en null.
        try {
            conectar();  // Llama al método conectar para establecer la conexión con la base de datos.
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
                    + "WHERE asignaciones.id = ?";  // Consulta SQL para seleccionar una asignación por ID.
            PreparedStatement ps = cnx.prepareStatement(sql);  // Prepara la consulta SQL.
            ps.setInt(1, id);  // Establece el valor del ID de la asignación.
            ResultSet rs = ps.executeQuery();  // Ejecuta la consulta y obtiene los resultados.
            if (rs.next()) {  // Verifica si hay resultados en el ResultSet.
                int asignacionId = rs.getInt("id");  // Obtiene el id de la asignación.
                String grupoNombre = rs.getString("grupo");  // Obtiene el nombre del grupo.
                String carreraNombre = rs.getString("carrera");  // Obtiene el nombre de la carrera.
                String cicloNombre = rs.getString("ciclo");  // Obtiene el nombre del ciclo.
                String cuatriNombre = rs.getString("cuatrimestre");  // Obtiene el nombre del cuatrimestre.
                String profesorNombre = rs.getString("profesor");  // Obtiene el nombre del profesor.
                String materiaNombre = rs.getString("materia");  // Obtiene el nombre de la materia.
                String descripcion = rs.getString("descripcion");  // Obtiene la descripción de la materia.
                String unidad = rs.getString("unidad");  // Obtiene la unidad de la materia.

                // Crea nuevos objetos con los datos obtenidos.
                Grupo grupo = new Grupo(-1, grupoNombre, null, null);  // Crea un objeto Grupo. Usa -1 para id, ya que no está disponible.
                Carrera carrera = new Carrera(-1, carreraNombre);  // Crea un objeto Carrera.
                Ciclo ciclo = new Ciclo(-1, cicloNombre);  // Crea un objeto Ciclo.
                Cuatrimestre cuatrimestre = new Cuatrimestre(-1, cuatriNombre);  // Crea un objeto Cuatrimestre.
                Profesor profesor = new Profesor(-1, profesorNombre);  // Crea un objeto Profesor.
                Materia materia = new Materia(-1, materiaNombre, descripcion, unidad);  // Crea un objeto Materia.

                // Crea una nueva asignación con los objetos creados y la id.
                asignacion = new Asignacion(asignacionId, grupo, carrera, ciclo, cuatrimestre, profesor, materia);
            }
        } catch (SQLException ex) {  // Captura excepciones SQL.
            System.out.println("Error al leer asignacion: " + ex.getMessage());  // Muestra el mensaje de error.
        } finally {
            desconectar();  // Llama al método desconectar para cerrar la conexión con la base de datos.
        }
        return asignacion;  // Devuelve la asignación obtenida.
    }

    // Método para actualizar una asignación existente.
    public void Actualizar(int idActual, int idNuevo, int nuevoIdGrupo, int nuevoIdProfesor, int nuevoIdMateria) {
        try {
            conectar();  // Llama al método conectar para establecer la conexión con la base de datos.

            // Verificar si el nuevo ID ya existe (si es diferente del ID actual).
            if (idActual != idNuevo) {
                String sqlCheck = "SELECT COUNT(*) FROM asignaciones WHERE id = ?";
                try (PreparedStatement psCheck = cnx.prepareStatement(sqlCheck)) {
                    psCheck.setInt(1, idNuevo);  // Establece el valor del nuevo ID.
                    try (ResultSet rsCheck = psCheck.executeQuery()) {
                        rsCheck.next();
                        if (rsCheck.getInt(1) > 0) {  // Verifica si el nuevo ID ya existe.
                            System.out.println("Error: El nuevo ID ya existe.");  // Muestra el mensaje de error.
                            return;  // Sale del método si el ID ya existe.
                        }
                    }
                }
            }

            // Verificar existencia de nuevo ID de grupo.
            String sqlCheckGrupo = "SELECT COUNT(*) FROM grupos WHERE id = ?";
            try (PreparedStatement psCheckGrupo = cnx.prepareStatement(sqlCheckGrupo)) {
                psCheckGrupo.setInt(1, nuevoIdGrupo);  // Establece el valor del nuevo ID de grupo.
                try (ResultSet rsCheckGrupo = psCheckGrupo.executeQuery()) {
                    rsCheckGrupo.next();
                    if (rsCheckGrupo.getInt(1) == 0) {  // Verifica si el ID de grupo existe.
                        System.out.println("Error: El ID del Grupo no existe.");  // Muestra el mensaje de error.
                        return;  // Sale del método si el ID de grupo no existe.
                    }
                }
            }

            // Verificar existencia de nuevo ID de profesor.
            String sqlCheckProfesor = "SELECT COUNT(*) FROM profesores WHERE id = ?";
            try (PreparedStatement psCheckProfesor = cnx.prepareStatement(sqlCheckProfesor)) {
                psCheckProfesor.setInt(1, nuevoIdProfesor);  // Establece el valor del nuevo ID de profesor.
                try (ResultSet rsCheckProfesor = psCheckProfesor.executeQuery()) {
                    rsCheckProfesor.next();
                    if (rsCheckProfesor.getInt(1) == 0) {  // Verifica si el ID de profesor existe.
                        System.out.println("Error: El ID del Profesor no existe.");  // Muestra el mensaje de error.
                        return;  // Sale del método si el ID de profesor no existe.
                    }
                }
            }

            // Verificar existencia de nuevo ID de materia.
            String sqlCheckMateria = "SELECT COUNT(*) FROM materias WHERE id = ?";
            try (PreparedStatement psCheckMateria = cnx.prepareStatement(sqlCheckMateria)) {
                psCheckMateria.setInt(1, nuevoIdMateria);  // Establece el valor del nuevo ID de materia.
                try (ResultSet rsCheckMateria = psCheckMateria.executeQuery()) {
                    rsCheckMateria.next();
                    if (rsCheckMateria.getInt(1) == 0) {  // Verifica si el ID de materia existe.
                        System.out.println("Error: El ID de la Materia no existe.");  // Muestra el mensaje de error.
                        return;  // Sale del método si el ID de materia no existe.
                    }
                }
            }

            // Consulta SQL para actualizar la asignación.
            String sqlUpdate = "UPDATE asignaciones SET id = ?, id_grupo = ?, id_profesor = ?, id_materia = ? WHERE id = ?";
            try (PreparedStatement psUpdate = cnx.prepareStatement(sqlUpdate)) {
                psUpdate.setInt(1, idNuevo);  // Establece el nuevo ID.
                psUpdate.setInt(2, nuevoIdGrupo);  // Establece el nuevo ID de grupo.
                psUpdate.setInt(3, nuevoIdProfesor);  // Establece el nuevo ID de profesor.
                psUpdate.setInt(4, nuevoIdMateria);  // Establece el nuevo ID de materia.
                psUpdate.setInt(5, idActual);  // Establece el ID actual (el que se actualizará).
                psUpdate.executeUpdate();  // Ejecuta la consulta para actualizar la asignación.
                System.out.println("Asignacion actualizada exitosamente.");  // Muestra mensaje de éxito.
            }
        } catch (SQLException ex) {  // Captura excepciones SQL.
            System.out.println("Error al actualizar asignacion: " + ex.getMessage());  // Muestra el mensaje de error.
        } finally {
            desconectar();  // Llama al método desconectar para cerrar la conexión con la base de datos.
        }
    }

    // Método para eliminar una asignación por su ID.
    public void Eliminar(int id) {
        try {
            conectar();  // Llama al método conectar para establecer la conexión con la base de datos.
            String sql = "DELETE FROM asignaciones WHERE id = ?";  // Consulta SQL para eliminar una asignación por ID.
            PreparedStatement ps = cnx.prepareStatement(sql);  // Prepara la consulta SQL.
            ps.setInt(1, id);  // Establece el valor del ID de la asignación.
            ps.executeUpdate();  // Ejecuta la consulta para eliminar la asignación.
        } catch (SQLException ex) {  // Captura excepciones SQL.
            System.out.println("Error al eliminar asignacion: " + ex.getMessage());  // Muestra el mensaje de error.
        } finally {
            desconectar();  // Llama al método desconectar para cerrar la conexión con la base de datos.
        }
    }
}

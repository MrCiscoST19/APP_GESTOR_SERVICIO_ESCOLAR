package f21;  // Declara el paquete al que pertenece esta clase.

import java.sql.PreparedStatement;  // Importa la clase PreparedStatement para ejecutar consultas SQL.
import java.sql.ResultSet;  // Importa la clase ResultSet para manejar resultados de consultas SQL.
import java.sql.SQLException;  // Importa la clase SQLException para manejar errores SQL.
import java.util.ArrayList;  // Importa la clase ArrayList para manejar listas de objetos.

public class ModeloCalificacion extends Conexion {  // Define la clase ModeloCalificacion que extiende la clase Conexion.

    private ArrayList<Calificacion> carteraCalificaciones = new ArrayList<>();  // Atributo para almacenar una lista de objetos Calificacion.

    // Método para listar todas las calificaciones de la base de datos.
    public void listarCalificaciones() {
        carteraCalificaciones.clear();  // Limpia la lista de calificaciones antes de llenarla con nuevos datos.
        try {
            conectar();  // Llama al método conectar para establecer una conexión con la base de datos.
            // Consulta SQL para seleccionar todos los datos necesarios de la tabla calificaciones y tablas relacionadas.
            String sql = "SELECT calificaciones.id, "
                    + "alumnos.nombre AS alumno, "
                    + "estatus.nombre AS Estado, "
                    + "grupos.nombre AS Grupo, "
                    + "ciclos.nombre AS Ciclo, "
                    + "cuatrimestres.nombre AS Cuatrimestre, "
                    + "carreras.nombre AS Carrera, "
                    + "materias.nombre AS Materia, "
                    + "profesores.nombre AS Profesor, "
                    + "calificaciones.unidad AS Unidad, "
                    + "calificaciones.calificacion AS Calificacion, "
                    + "calificaciones.id_alumno AS idAlumno, "
                    + "calificaciones.id_asigna AS idAsigna "
                    + "FROM calificaciones "
                    + "INNER JOIN alumnos ON calificaciones.id_alumno = alumnos.id "
                    + "INNER JOIN asignaciones ON calificaciones.id_asigna = asignaciones.id "
                    + "INNER JOIN grupos ON asignaciones.id_grupo = grupos.id "
                    + "INNER JOIN materias ON asignaciones.id_materia = materias.id "
                    + "INNER JOIN profesores ON asignaciones.id_profesor = profesores.id "
                    + "INNER JOIN ciclo_cuatri ON grupos.id_ciclo_cuatri = ciclo_cuatri.id "
                    + "INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id "
                    + "INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id "
                    + "INNER JOIN carreras ON grupos.id_carrera = carreras.id "
                    + "INNER JOIN estatus ON alumnos.id_estado = estatus.id "
                    + "ORDER BY calificaciones.id;";  // Define la consulta SQL para obtener los datos de las calificaciones.
            PreparedStatement ps = cnx.prepareStatement(sql);  // Prepara la consulta SQL.
            ResultSet rs = ps.executeQuery();  // Ejecuta la consulta y obtiene el ResultSet.
            // Itera a través de los resultados del ResultSet.
            while (rs.next()) {
                int id = rs.getInt("id");  // Obtiene el valor del campo id.
                String alumno = rs.getString("alumno");  // Obtiene el valor del campo alumno.
                String estado = rs.getString("Estado");  // Obtiene el valor del campo estado.
                String grupo = rs.getString("Grupo");  // Obtiene el valor del campo grupo.
                String ciclo = rs.getString("Ciclo");  // Obtiene el valor del campo ciclo.
                String cuatrimestre = rs.getString("Cuatrimestre");  // Obtiene el valor del campo cuatrimestre.
                String carrera = rs.getString("Carrera");  // Obtiene el valor del campo carrera.
                String materia = rs.getString("Materia");  // Obtiene el valor del campo materia.
                String profesor = rs.getString("Profesor");  // Obtiene el valor del campo profesor.
                String unidad = rs.getString("Unidad");  // Obtiene el valor del campo unidad.
                String calificacion = rs.getString("Calificacion");  // Obtiene el valor del campo calificacion.
                int idAlumno = rs.getInt("idAlumno");  // Obtiene el valor del campo idAlumno.
                int idAsigna = rs.getInt("idAsigna");  // Obtiene el valor del campo idAsigna.

                // Crea una instancia de Calificacion usando los valores recuperados.
                Calificacion calificacionObj = new Calificacion(id, alumno, estado, grupo, ciclo, cuatrimestre, carrera, materia, profesor, unidad, calificacion, idAlumno, idAsigna);
                carteraCalificaciones.add(calificacionObj);  // Agrega la instancia a la lista carteraCalificaciones.
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar calificaciones: " + ex.getMessage());  // Maneja posibles errores SQL.
        } finally {
            desconectar();  // Asegura la desconexión de la base de datos en el bloque finally.
        }
    }

    // Método para obtener la lista de calificaciones.
    public ArrayList<Calificacion> getCarteraCalificaciones() {
        return carteraCalificaciones;  // Retorna la lista de calificaciones.
    }

    // Método para leer una calificación específica por su ID.
    public Calificacion Leer(int id) {
        Calificacion calificacion = null;  // Inicializa una variable Calificacion a null.
        try {
            conectar();  // Llama al método conectar para establecer una conexión con la base de datos.
            // Consulta SQL para seleccionar una calificación específica por su ID.
            String sql = "SELECT calificaciones.id, "
                    + "alumnos.nombre AS alumno, "
                    + "estatus.nombre AS Estado, "
                    + "grupos.nombre AS Grupo, "
                    + "ciclos.nombre AS Ciclo, "
                    + "cuatrimestres.nombre AS Cuatrimestre, "
                    + "carreras.nombre AS Carrera, "
                    + "materias.nombre AS Materia, "
                    + "profesores.nombre AS Profesor, "
                    + "calificaciones.unidad AS Unidad, "
                    + "calificaciones.calificacion AS Calificacion, "
                    + "calificaciones.id_alumno AS idAlumno, "
                    + "calificaciones.id_asigna AS idAsigna "
                    + "FROM calificaciones "
                    + "INNER JOIN alumnos ON calificaciones.id_alumno = alumnos.id "
                    + "INNER JOIN asignaciones ON calificaciones.id_asigna = asignaciones.id "
                    + "INNER JOIN grupos ON asignaciones.id_grupo = grupos.id "
                    + "INNER JOIN materias ON asignaciones.id_materia = materias.id "
                    + "INNER JOIN profesores ON asignaciones.id_profesor = profesores.id "
                    + "INNER JOIN ciclo_cuatri ON grupos.id_ciclo_cuatri = ciclo_cuatri.id "
                    + "INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id "
                    + "INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id "
                    + "INNER JOIN carreras ON grupos.id_carrera = carreras.id "
                    + "INNER JOIN estatus ON alumnos.id_estado = estatus.id "
                    + "WHERE calificaciones.id = ?;";  // Define la consulta SQL para obtener los datos de una calificación específica por su ID.
            PreparedStatement ps = cnx.prepareStatement(sql);  // Prepara la consulta SQL.
            ps.setInt(1, id);  // Establece el valor del parámetro ID en la consulta.
            ResultSet rs = ps.executeQuery();  // Ejecuta la consulta y obtiene el ResultSet.
            if (rs.next()) {  // Verifica si hay resultados.
                String alumno = rs.getString("alumno");  // Obtiene el valor del campo alumno.
                String estado = rs.getString("Estado");  // Obtiene el valor del campo estado.
                String grupo = rs.getString("Grupo");  // Obtiene el valor del campo grupo.
                String ciclo = rs.getString("Ciclo");  // Obtiene el valor del campo ciclo.
                String cuatrimestre = rs.getString("Cuatrimestre");  // Obtiene el valor del campo cuatrimestre.
                String carrera = rs.getString("Carrera");  // Obtiene el valor del campo carrera.
                String materia = rs.getString("Materia");  // Obtiene el valor del campo materia.
                String profesor = rs.getString("Profesor");  // Obtiene el valor del campo profesor.
                String unidad = rs.getString("Unidad");  // Obtiene el valor del campo unidad.
                String calificacionStr = rs.getString("Calificacion");  // Obtiene el valor del campo calificacion.
                int idAlumno = rs.getInt("idAlumno");  // Obtiene el valor del campo idAlumno.
                int idAsigna = rs.getInt("idAsigna");  // Obtiene el valor del campo idAsigna.

                // Crea una instancia de Calificacion usando los valores recuperados.
                calificacion = new Calificacion(id, alumno, estado, grupo, ciclo, cuatrimestre, carrera, materia, profesor, unidad, calificacionStr, idAlumno, idAsigna);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer calificacion: " + ex.getMessage());  // Maneja posibles errores SQL.
        } finally {
            desconectar();  // Asegura la desconexión de la base de datos en el bloque finally.
        }
        return calificacion;  // Retorna la calificación recuperada.
    }

    // Método para crear una nueva calificación.
    public void Crear(String unidad, String calificacion, int idAlumno, int idAsigna) {
        try {
            conectar();  // Llama al método conectar para establecer una conexión con la base de datos.
            // Consulta SQL para insertar una nueva calificación en la base de datos.
            String sql = "INSERT INTO calificaciones (unidad, calificacion, id_alumno, id_asigna) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(sql);  // Prepara la consulta SQL.
            ps.setString(1, unidad);  // Establece el valor del parámetro unidad en la consulta.
            ps.setString(2, calificacion);  // Establece el valor del parámetro calificacion en la consulta.
            ps.setInt(3, idAlumno);  // Establece el valor del parámetro idAlumno en la consulta.
            ps.setInt(4, idAsigna);  // Establece el valor del parámetro idAsigna en la consulta.
            ps.executeUpdate();  // Ejecuta la consulta de inserción.
        } catch (SQLException ex) {
            System.out.println("Error al crear calificacion: " + ex.getMessage());  // Maneja posibles errores SQL.
        } finally {
            desconectar();  // Asegura la desconexión de la base de datos en el bloque finally.
        }
    }

    // Método para actualizar una calificación existente.
    public void Actualizar(int idActual, int idNuevo, String nuevaUnidad, String nuevaCalificacion, int nuevoIdAlumno, int nuevoIdAsigna) {
        try {
            conectar();  // Llama al método conectar para establecer una conexión con la base de datos.

            // Verificar si el nuevo ID ya existe, si es diferente del ID actual.
            if (idActual != idNuevo) {
                String sqlCheck = "SELECT COUNT(*) FROM calificaciones WHERE id = ?";
                PreparedStatement psCheck = cnx.prepareStatement(sqlCheck);  // Prepara la consulta para verificar si el nuevo ID ya existe.
                psCheck.setInt(1, idNuevo);  // Establece el valor del parámetro idNuevo en la consulta.
                ResultSet rsCheck = psCheck.executeQuery();  // Ejecuta la consulta y obtiene el ResultSet.
                rsCheck.next();
                if (rsCheck.getInt(1) > 0) {  // Verifica si el nuevo ID ya existe.
                    System.out.println("Error: El nuevo ID ya existe.");  // Muestra un mensaje de error si el ID ya existe.
                    return;  // Sale del método si el nuevo ID ya existe.
                }

                // Verificar si el ID actual existe antes de intentar cambiarlo.
                String sqlCheckActual = "SELECT COUNT(*) FROM calificaciones WHERE id = ?";
                PreparedStatement psCheckActual = cnx.prepareStatement(sqlCheckActual);  // Prepara la consulta para verificar si el ID actual existe.
                psCheckActual.setInt(1, idActual);  // Establece el valor del parámetro idActual en la consulta.
                ResultSet rsCheckActual = psCheckActual.executeQuery();  // Ejecuta la consulta y obtiene el ResultSet.
                rsCheckActual.next();
                if (rsCheckActual.getInt(1) == 0) {  // Verifica si el ID actual no existe.
                    System.out.println("Error: El ID actual no existe.");  // Muestra un mensaje de error si el ID actual no existe.
                    return;  // Sale del método si el ID actual no existe.
                }
            }

            // Verificar si el nuevo ID del alumno ya existe.
            String sqlCheckAlumno = "SELECT COUNT(*) FROM alumnos WHERE id = ?";
            PreparedStatement psCheckAlumno = cnx.prepareStatement(sqlCheckAlumno);  // Prepara la consulta para verificar si el ID del alumno existe.
            psCheckAlumno.setInt(1, nuevoIdAlumno);  // Establece el valor del parámetro nuevoIdAlumno en la consulta.
            ResultSet rsCheckAlumno = psCheckAlumno.executeQuery();  // Ejecuta la consulta y obtiene el ResultSet.
            rsCheckAlumno.next();
            if (rsCheckAlumno.getInt(1) == 0) {  // Verifica si el ID del alumno no existe.
                System.out.println("Error: El ID del alumno no existe.");  // Muestra un mensaje de error si el ID del alumno no existe.
                return;  // Sale del método si el ID del alumno no existe.
            }

            // Verificar si el nuevo ID de la asignación ya existe.
            String sqlCheckAsignacion = "SELECT COUNT(*) FROM asignaciones WHERE id = ?";
            PreparedStatement psCheckAsignacion = cnx.prepareStatement(sqlCheckAsignacion);  // Prepara la consulta para verificar si el ID de la asignación existe.
            psCheckAsignacion.setInt(1, nuevoIdAsigna);  // Establece el valor del parámetro nuevoIdAsigna en la consulta.
            ResultSet rsCheckAsignacion = psCheckAsignacion.executeQuery();  // Ejecuta la consulta y obtiene el ResultSet.
            rsCheckAsignacion.next();
            if (rsCheckAsignacion.getInt(1) == 0) {  // Verifica si el ID de la asignación no existe.
                System.out.println("Error: El ID de la asignacion no existe.");  // Muestra un mensaje de error si el ID de la asignación no existe.
                return;  // Sale del método si el ID de la asignación no existe.
            }

            // Consulta SQL para actualizar la calificación.
            // Nota: Es posible que necesites actualizar el ID en otras tablas también si hay referencias a este ID.
            String sql = "UPDATE calificaciones SET unidad = ?, calificacion = ?, id_alumno = ?, id_asigna = ? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);  // Prepara la consulta SQL para la actualización.
            ps.setString(1, nuevaUnidad);  // Establece el valor del parámetro nuevaUnidad en la consulta.
            ps.setString(2, nuevaCalificacion);  // Establece el valor del parámetro nuevaCalificacion en la consulta.
            ps.setInt(3, nuevoIdAlumno);  // Establece el valor del parámetro nuevoIdAlumno en la consulta.
            ps.setInt(4, nuevoIdAsigna);  // Establece el valor del parámetro nuevoIdAsigna en la consulta.
            ps.setInt(5, idActual);  // Establece el valor del parámetro idActual en la consulta.
            ps.executeUpdate();  // Ejecuta la consulta de actualización.

            // Actualizar el ID de la calificación (esto debe ser manejado con cuidado).
            if (idActual != idNuevo) {  // Verifica si el ID actual es diferente del nuevo ID.
                String sqlUpdateId = "UPDATE calificaciones SET id = ? WHERE id = ?";
                PreparedStatement psUpdateId = cnx.prepareStatement(sqlUpdateId);  // Prepara la consulta para actualizar el ID.
                psUpdateId.setInt(1, idNuevo);  // Establece el valor del parámetro idNuevo en la consulta.
                psUpdateId.setInt(2, idActual);  // Establece el valor del parámetro idActual en la consulta.
                psUpdateId.executeUpdate();  // Ejecuta la consulta de actualización del ID.
            }
        } catch (SQLException ex) {
            System.out.println("Error al actualizar calificacion: " + ex.getMessage());  // Maneja posibles errores SQL.
        } finally {
            desconectar();  // Asegura la desconexión de la base de datos en el bloque finally.
        }
    }

    // Método para eliminar una calificación por su ID.
    public void Eliminar(int id) {
        try {
            conectar();  // Llama al método conectar para establecer una conexión con la base de datos.
            // Consulta SQL para eliminar una calificación específica por su ID.
            String sql = "DELETE FROM calificaciones WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);  // Prepara la consulta SQL.
            ps.setInt(1, id);  // Establece el valor del parámetro ID en la consulta.
            ps.executeUpdate();  // Ejecuta la consulta de eliminación.
        } catch (SQLException ex) {
            System.out.println("Error al eliminar calificacion: " + ex.getMessage());  // Maneja posibles errores SQL.
        } finally {
            desconectar();  // Asegura la desconexión de la base de datos en el bloque finally.
        }
    }
}

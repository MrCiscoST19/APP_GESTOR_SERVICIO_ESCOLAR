package f21;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase ModeloCC que extiende la clase Conexion para manejar operaciones sobre la tabla ciclo_cuatri
public class ModeloCC extends Conexion {

    // Lista para almacenar los objetos CC (relación entre Ciclo y Cuatrimestre)
    private ArrayList<CC> carteraCC = new ArrayList<>();

    // Método para listar todos los registros de Ciclo_Cuatri desde la base de datos
    public void listarCiclo_Cuatri() {
        carteraCC.clear(); // Limpiar la lista antes de cargar nuevos datos
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para obtener todos los registros de la tabla ciclo_cuatri
            String sql = "SELECT ciclo_cuatri.id, ciclos.id AS ciclo_id, ciclos.nombre AS ciclo_nombre,"
                    + " cuatrimestres.id AS cuatri_id, cuatrimestres.nombre AS cuatri_nombre "
                    + "FROM ciclo_cuatri "
                    + "INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id "
                    + "INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id "
                    + "ORDER BY ciclo_cuatri.id;";
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ResultSet rs = ps.executeQuery(); // Ejecutar la consulta y obtener los resultados
            // Iterar sobre los resultados y agregar cada registro a la lista
            while (rs.next()) {
                int id = rs.getInt("id"); // Obtener el ID del registro
                int cicloId = rs.getInt("ciclo_id"); // Obtener el ID del Ciclo
                String cicloNombre = rs.getString("ciclo_nombre"); // Obtener el nombre del Ciclo
                Ciclo ciclo = new Ciclo(cicloId, cicloNombre); // Crear un objeto Ciclo
                int cuatriId = rs.getInt("cuatri_id"); // Obtener el ID del Cuatrimestre
                String cuatriNombre = rs.getString("cuatri_nombre"); // Obtener el nombre del Cuatrimestre
                Cuatrimestre cuatrimestre = new Cuatrimestre(cuatriId, cuatriNombre); // Crear un objeto Cuatrimestre
                CC cc = new CC(id, ciclo, cuatrimestre); // Crear un objeto CC
                carteraCC.add(cc); // Agregar el objeto CC a la lista
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar ciclo_cuatri: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para obtener la lista de todos los registros de Ciclo_Cuatri
    public ArrayList<CC> getCarteraCiclo_Cuatri() {
        return carteraCC; // Retornar la lista de registros
    }

    // Método para crear un nuevo registro en la tabla ciclo_cuatri
    public void Crear(int idCiclo, int idCuatri) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para insertar un nuevo registro en la tabla ciclo_cuatri
            String sql = "INSERT INTO ciclo_cuatri (id_ciclo, id_cuatri) VALUES (?, ?)";
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ps.setInt(1, idCiclo); // Establecer el ID del Ciclo
            ps.setInt(2, idCuatri); // Establecer el ID del Cuatrimestre
            ps.executeUpdate(); // Ejecutar la actualización
        } catch (SQLException ex) {
            System.out.println("Error al crear ciclo_cuatri: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para leer un registro de ciclo_cuatri por su ID
    public CC Leer(int id) {
        CC cc = null; // Inicializar el objeto CC como nulo
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para obtener un registro de ciclo_cuatri por su ID
            String sql = "SELECT ciclo_cuatri.id, ciclos.id AS ciclo_id, ciclos.nombre AS ciclo_nombre, cuatrimestres.id AS cuatri_id, cuatrimestres.nombre AS cuatri_nombre "
                    + "FROM ciclo_cuatri "
                    + "INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id "
                    + "INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id "
                    + "WHERE ciclo_cuatri.id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ps.setInt(1, id); // Establecer el ID del registro a buscar
            ResultSet rs = ps.executeQuery(); // Ejecutar la consulta y obtener el resultado
            if (rs.next()) {
                int cicloId = rs.getInt("ciclo_id"); // Obtener el ID del Ciclo
                String cicloNombre = rs.getString("ciclo_nombre"); // Obtener el nombre del Ciclo
                Ciclo ciclo = new Ciclo(cicloId, cicloNombre); // Crear un objeto Ciclo
                int cuatriId = rs.getInt("cuatri_id"); // Obtener el ID del Cuatrimestre
                String cuatriNombre = rs.getString("cuatri_nombre"); // Obtener el nombre del Cuatrimestre
                Cuatrimestre cuatrimestre = new Cuatrimestre(cuatriId, cuatriNombre); // Crear un objeto Cuatrimestre
                cc = new CC(id, ciclo, cuatrimestre); // Crear un objeto CC
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer ciclo_cuatri: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
        return cc; // Retornar el registro encontrado o nulo si no se encuentra
    }

    // Método para actualizar un registro existente en la tabla ciclo_cuatri
    public void Actualizar(int idActual, int nuevoId, int idCiclo, int idCuatri) {
        try {
            conectar(); // Conectar a la base de datos

            // Verificar si el nuevo ID ya existe
            String sqlCheck = "SELECT COUNT(*) FROM ciclo_cuatri WHERE id = ?";
            PreparedStatement psCheck = cnx.prepareStatement(sqlCheck); // Preparar la consulta SQL para verificar existencia
            psCheck.setInt(1, nuevoId); // Establecer el nuevo ID a verificar
            ResultSet rsCheck = psCheck.executeQuery(); // Ejecutar la consulta y obtener el resultado
            rsCheck.next();
            if (rsCheck.getInt(1) > 0 && idActual != nuevoId) {
                System.out.println("Error: El nuevo ID ya existe."); // Mensaje de error si el nuevo ID ya existe
                return; // Salir del método
            }

            // Actualizar el registro
            String sql = "UPDATE ciclo_cuatri SET id = ?, id_ciclo = ?, id_cuatri = ? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL para actualización
            ps.setInt(1, nuevoId); // Establecer el nuevo ID
            ps.setInt(2, idCiclo); // Establecer el ID del Ciclo
            ps.setInt(3, idCuatri); // Establecer el ID del Cuatrimestre
            ps.setInt(4, idActual); // Establecer el ID actual del registro
            ps.executeUpdate(); // Ejecutar la actualización

            // Si el ID es cambiado, se puede necesitar eliminar el registro antiguo y crear uno nuevo
            if (idActual != nuevoId) {
                Eliminar(idActual); // Eliminar el registro con el ID antiguo
            }
        } catch (SQLException ex) {
            System.out.println("Error al actualizar ciclo_cuatri: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para eliminar un registro de ciclo_cuatri por su ID
    public void Eliminar(int id) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para eliminar un registro de la tabla ciclo_cuatri
            String sql = "DELETE FROM ciclo_cuatri WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL para eliminación
            ps.setInt(1, id); // Establecer el ID del registro a eliminar
            ps.executeUpdate(); // Ejecutar la eliminación
        } catch (SQLException ex) {
            System.out.println("Error al eliminar ciclo_cuatri: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }
}

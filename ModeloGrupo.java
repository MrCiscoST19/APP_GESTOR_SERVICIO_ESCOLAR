package f21;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// La clase ModeloGrupo extiende la clase Conexion para gestionar la conexión a la base de datos
public class ModeloGrupo extends Conexion {

    // Lista para almacenar los grupos
    private ArrayList<Grupo> carteraGrupos = new ArrayList<>();

    // Método para listar todos los grupos de la base de datos
    public void listarGrupos() {
        // Limpiar la lista de grupos antes de llenarla
        carteraGrupos.clear();
        try {
            // Conectar a la base de datos
            conectar();
            // Consulta SQL para obtener los detalles de los grupos y sus relaciones
            String sql = "SELECT grupos.id, grupos.nombre, ciclo_cuatri.id AS ciclo_cuatri_id, ciclos.id AS ciclo_id, ciclos.nombre AS ciclo_nombre, "
                    + "cuatrimestres.id AS cuatri_id, cuatrimestres.nombre AS cuatri_nombre, carreras.id AS carrera_id, carreras.nombre AS carrera_nombre "
                    + "FROM grupos "
                    + "INNER JOIN ciclo_cuatri ON grupos.id_ciclo_cuatri = ciclo_cuatri.id "
                    + "INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id "
                    + "INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id "
                    + "INNER JOIN carreras ON grupos.id_carrera = carreras.id "
                    + "ORDER BY grupos.id;";
            // Preparar y ejecutar la consulta
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            // Recorrer los resultados de la consulta
            while (rs.next()) {
                // Obtener los datos del grupo y sus relaciones
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int cicloCuatriId = rs.getInt("ciclo_cuatri_id");
                int cicloId = rs.getInt("ciclo_id");
                String cicloNombre = rs.getString("ciclo_nombre");
                int cuatriId = rs.getInt("cuatri_id");
                String cuatriNombre = rs.getString("cuatri_nombre");
                int carreraId = rs.getInt("carrera_id");
                String carreraNombre = rs.getString("carrera_nombre");

                // Crear objetos para las relaciones
                Ciclo ciclo = new Ciclo(cicloId, cicloNombre);
                Cuatrimestre cuatrimestre = new Cuatrimestre(cuatriId, cuatriNombre);
                CC cicloCuatri = new CC(cicloCuatriId, ciclo, cuatrimestre);
                Carrera carrera = new Carrera(carreraId, carreraNombre);
                Grupo grupo = new Grupo(id, nombre, cicloCuatri, carrera);

                // Agregar el grupo a la lista
                carteraGrupos.add(grupo);
            }
        } catch (SQLException ex) {
            // Manejar cualquier error que ocurra durante la consulta
            System.out.println("Error al listar grupos: " + ex.getMessage());
        } finally {
            // Desconectar de la base de datos
            desconectar();
        }
    }

    // Método para obtener la lista de grupos
    public ArrayList<Grupo> getCarteraGrupos() {
        return carteraGrupos;
    }

    // Método para leer un grupo específico por su ID
    public Grupo Leer(int id) {
        Grupo grupo = null;
        try {
            // Conectar a la base de datos
            conectar();
            // Consulta SQL para obtener los detalles del grupo por su ID
            String sql = "SELECT grupos.id, grupos.nombre, ciclo_cuatri.id AS ciclo_cuatri_id, ciclos.id AS ciclo_id, ciclos.nombre AS ciclo_nombre, "
                    + "cuatrimestres.id AS cuatri_id, cuatrimestres.nombre AS cuatri_nombre, carreras.id AS carrera_id, carreras.nombre AS carrera_nombre "
                    + "FROM grupos "
                    + "INNER JOIN ciclo_cuatri ON grupos.id_ciclo_cuatri = ciclo_cuatri.id "
                    + "INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id "
                    + "INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id "
                    + "INNER JOIN carreras ON grupos.id_carrera = carreras.id "
                    + "WHERE grupos.id = ?;";
            // Preparar y ejecutar la consulta con el ID proporcionado
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            // Si se encuentra el grupo, obtener los datos y crear el objeto
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int cicloCuatriId = rs.getInt("ciclo_cuatri_id");
                int cicloId = rs.getInt("ciclo_id");
                String cicloNombre = rs.getString("ciclo_nombre");
                int cuatriId = rs.getInt("cuatri_id");
                String cuatriNombre = rs.getString("cuatri_nombre");
                int carreraId = rs.getInt("carrera_id");
                String carreraNombre = rs.getString("carrera_nombre");

                // Crear objetos para las relaciones
                Ciclo ciclo = new Ciclo(cicloId, cicloNombre);
                Cuatrimestre cuatrimestre = new Cuatrimestre(cuatriId, cuatriNombre);
                CC cicloCuatri = new CC(cicloCuatriId, ciclo, cuatrimestre);
                Carrera carrera = new Carrera(carreraId, carreraNombre);
                grupo = new Grupo(id, nombre, cicloCuatri, carrera);
            }
        } catch (SQLException ex) {
            // Manejar cualquier error que ocurra durante la consulta
            System.out.println("Error al leer grupo: " + ex.getMessage());
        } finally {
            // Desconectar de la base de datos
            desconectar();
        }
        return grupo;
    }

    // Método para crear un nuevo grupo
    public void Crear(String nombre, int idCicloCuatri, int idCarrera) {
        try {
            // Conectar a la base de datos
            conectar();
            // Consulta SQL para insertar un nuevo grupo
            String sql = "INSERT INTO grupos (nombre, id_ciclo_cuatri, id_carrera) VALUES (?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, idCicloCuatri);
            ps.setInt(3, idCarrera);
            ps.executeUpdate();
        } catch (SQLException ex) {
            // Manejar cualquier error que ocurra durante la inserción
            System.out.println("Error al crear grupo: " + ex.getMessage());
        } finally {
            // Desconectar de la base de datos
            desconectar();
        }
    }

    // Método para actualizar un grupo existente
    public void Actualizar(int idActual, int idNuevo, String nuevoNombre, int nuevoIdCicloCuatri, int nuevoIdCarrera) {
        try {
            // Conectar a la base de datos
            conectar();

            // Verificar si el nuevo ID ya existe, si es diferente del ID actual
            if (idActual != idNuevo) {
                String sqlCheck = "SELECT COUNT(*) FROM grupos WHERE id = ?";
                PreparedStatement psCheck = cnx.prepareStatement(sqlCheck);
                psCheck.setInt(1, idNuevo);
                ResultSet rsCheck = psCheck.executeQuery();
                rsCheck.next();
                if (rsCheck.getInt(1) > 0) {
                    System.out.println("Error: El nuevo ID ya existe.");
                    return;
                }
            }

            // Verificar si el nuevo ID de ciclo_cuatri ya existe
            String sqlCheckCicloCuatri = "SELECT COUNT(*) FROM ciclo_cuatri WHERE id = ?";
            PreparedStatement psCheckCicloCuatri = cnx.prepareStatement(sqlCheckCicloCuatri);
            psCheckCicloCuatri.setInt(1, nuevoIdCicloCuatri);
            ResultSet rsCheckCicloCuatri = psCheckCicloCuatri.executeQuery();
            rsCheckCicloCuatri.next();
            if (rsCheckCicloCuatri.getInt(1) == 0) {
                System.out.println("Error: El ID del ciclo_cuatri no existe.");
                return;
            }

            // Verificar si el nuevo ID de la carrera ya existe
            String sqlCheckCarrera = "SELECT COUNT(*) FROM carreras WHERE id = ?";
            PreparedStatement psCheckCarrera = cnx.prepareStatement(sqlCheckCarrera);
            psCheckCarrera.setInt(1, nuevoIdCarrera);
            ResultSet rsCheckCarrera = psCheckCarrera.executeQuery();
            rsCheckCarrera.next();
            if (rsCheckCarrera.getInt(1) == 0) {
                System.out.println("Error: El ID de la carrera no existe.");
                return;
            }

            // Consulta SQL para actualizar el grupo
            String sql = "UPDATE grupos SET id = ?, nombre = ?, id_ciclo_cuatri = ?, id_carrera = ? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, idNuevo);
            ps.setString(2, nuevoNombre);
            ps.setInt(3, nuevoIdCicloCuatri);
            ps.setInt(4, nuevoIdCarrera);
            ps.setInt(5, idActual);
            ps.executeUpdate();
        } catch (SQLException ex) {
            // Manejar cualquier error que ocurra durante la actualización
            System.out.println("Error al actualizar grupo: " + ex.getMessage());
        } finally {
            // Desconectar de la base de datos
            desconectar();
        }
    }

    // Método para eliminar un grupo por su ID
    public void Eliminar(int id) {
        try {
            // Conectar a la base de datos
            conectar();
            // Consulta SQL para eliminar el grupo
            String sql = "DELETE FROM grupos WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            // Manejar cualquier error que ocurra durante la eliminación
            System.out.println("Error al eliminar grupo: " + ex.getMessage());
        } finally {
            // Desconectar de la base de datos
            desconectar();
        }
    }
}
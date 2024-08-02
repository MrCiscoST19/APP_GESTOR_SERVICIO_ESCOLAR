package f21;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase ModeloCuatri extiende de la clase Conexion para utilizar sus métodos de conexión
public class ModeloCuatri extends Conexion {

    // Lista para almacenar los objetos Cuatrimestre
    private ArrayList<Cuatrimestre> carteraCuatrimestre = new ArrayList<>();

    // Método para listar todos los cuatrimestres desde la base de datos
    public void listarCuatrimestres() {
        // Limpiar la lista antes de cargar nuevos datos
        this.carteraCuatrimestre.clear();

        // Conectar a la base de datos
        try {
            conectar();
            // Consulta SQL para obtener todos los registros de la tabla cuatrimestres
            String sql = "SELECT * FROM cuatrimestres ORDER BY id;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Iterar sobre los resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                // Crear un objeto Cuatrimestre y agregarlo a la lista carteraCuatrimestre
                Cuatrimestre cuatrimestre = new Cuatrimestre(id, nombre);
                this.carteraCuatrimestre.add(cuatrimestre);
            }
        } catch (SQLException ex) {
            // Manejar errores de SQL durante la consulta
            System.out.println("Error al listar cuatrimestres: " + ex.getMessage());
        } finally {
            // Asegurarse de desconectar en el bloque finally para liberar recursos
            desconectar();
        }
    }

    // Método para obtener la lista de cuatrimestres
    public ArrayList<Cuatrimestre> getCarteraCuatrimestre() {
        return carteraCuatrimestre;
    }

    // Método para crear un nuevo cuatrimestre
    public void Crear(String nombre) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para insertar un nuevo registro en la tabla cuatrimestres
            String sql = "INSERT INTO cuatrimestres (nombre) VALUES (?)";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, nombre); // Establecer el nombre del cuatrimestre
            ps.executeUpdate(); // Ejecutar la actualización
        } catch (SQLException ex) {
            // Manejar errores de SQL durante la inserción
            System.out.println("Error al crear cuatrimestre: " + ex.getMessage());
        } finally {
            // Asegurarse de desconectar en el bloque finally para liberar recursos
            desconectar();
        }
    }

    // Método para leer un cuatrimestre por su ID
    public Cuatrimestre Leer(int id) {
        Cuatrimestre cuatrimestre = null;
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para obtener un registro específico de la tabla cuatrimestres
            String sql = "SELECT * FROM cuatrimestres WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id); // Establecer el ID del cuatrimestre
            ResultSet rs = ps.executeQuery();

            // Si se encuentra el cuatrimestre, crear el objeto Cuatrimestre
            if (rs.next()) {
                int idEncontrado = rs.getInt("id");
                String nombre = rs.getString("nombre");
                cuatrimestre = new Cuatrimestre(idEncontrado, nombre);
            }
        } catch (SQLException ex) {
            // Manejar errores de SQL durante la consulta
            System.out.println("Error al leer cuatrimestre: " + ex.getMessage());
        } finally {
            // Asegurarse de desconectar en el bloque finally para liberar recursos
            desconectar();
        }
        return cuatrimestre;
    }

    // Método para actualizar un cuatrimestre existente
    public void Actualizar(int idActual, int idNuevo, String nombre) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para actualizar un registro en la tabla cuatrimestres
            String updateSql = "UPDATE cuatrimestres SET id = ?, nombre = ? WHERE id = ?";
            PreparedStatement updatePs = cnx.prepareStatement(updateSql);
            updatePs.setInt(1, idNuevo); // Establecer el nuevo ID
            updatePs.setString(2, nombre); // Establecer el nuevo nombre
            updatePs.setInt(3, idActual); // Establecer el ID del cuatrimestre a actualizar
            updatePs.executeUpdate(); // Ejecutar la actualización
        } catch (SQLException ex) {
            // Manejar errores de SQL durante la actualización
            System.out.println("Error al actualizar cuatrimestre: " + ex.getMessage());
        } finally {
            // Asegurarse de desconectar en el bloque finally para liberar recursos
            desconectar();
        }
    }

    // Método para eliminar un cuatrimestre
    public void Eliminar(int id) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para eliminar un registro de la tabla cuatrimestres
            String sql = "DELETE FROM cuatrimestres WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id); // Establecer el ID del cuatrimestre a eliminar
            ps.executeUpdate(); // Ejecutar la eliminación
        } catch (SQLException ex) {
            // Manejar errores de SQL durante la eliminación
            System.out.println("Error al eliminar cuatrimestre: " + ex.getMessage());
        } finally {
            // Asegurarse de desconectar en el bloque finally para liberar recursos
            desconectar();
        }
    }

    public void Consultar() {
        listarCuatrimestres(); // Llamar al método para listar ciclos
        // Imprimir en formato de tabla
        System.out.println("+----+--------------+");
        System.out.println("|    CUATRIMESTRES  |");
        System.out.println("+----+--------------+");
        System.out.println("| ID | Cuatrimestre |");
        System.out.println("+----+--------------+");
        for (Cuatrimestre cuatrimestre : carteraCuatrimestre) {
            // Mostrar los detalles del ciclo en formato de tabla
            System.out.printf("| %-2d | %-12s |\n", cuatrimestre.getId(), cuatrimestre.getNombre());
        }
        System.out.println("+----+--------------+");
    }
}

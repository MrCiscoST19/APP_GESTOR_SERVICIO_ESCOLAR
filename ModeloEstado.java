package f21;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase ModeloEstado extiende de la clase Conexion para utilizar sus métodos de conexión
public class ModeloEstado extends Conexion {

    // Lista para almacenar los objetos Estado
    private ArrayList<Estado> carteraEstado = new ArrayList<>();

    // Método para listar todos los estatus desde la base de datos
    public void listarEstatus() {
        // Limpiar la lista antes de cargar nuevos datos
        this.carteraEstado.clear();

        // Conectar a la base de datos
        try {
            conectar();
            // Consulta SQL para obtener todos los registros de la tabla estatus
            String sql = "SELECT * FROM estatus ORDER BY id;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Iterar sobre los resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                // Crear un objeto Estado y agregarlo a la lista carteraEstado
                Estado estado = new Estado(id, nombre);
                this.carteraEstado.add(estado);
            }
        } catch (SQLException ex) {
            // Manejar la excepción en caso de error
            System.out.println("Error al listar estatus: " + ex.getMessage());
        } finally {
            // Asegurarse de desconectar en el bloque finally
            desconectar();
        }
    }

    // Método para obtener la lista de estatus
    public ArrayList<Estado> getCarteraEstado() {
        return carteraEstado;
    }

    // Método para crear un nuevo estatus
    public void Crear(String nombre) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para insertar un nuevo registro en la tabla estatus
            String sql = "INSERT INTO estatus (nombre) VALUES (?)";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, nombre); // Establecer el nombre del estatus
            ps.executeUpdate(); // Ejecutar la actualización
        } catch (SQLException ex) {
            // Manejar la excepción en caso de error
            System.out.println("Error al crear estatus: " + ex.getMessage());
        } finally {
            // Asegurarse de desconectar en el bloque finally
            desconectar();
        }
    }

    // Método para leer un estatus por su ID
    public Estado Leer(int id) {
        Estado estado = null;
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para obtener un registro de la tabla estatus por su ID
            String sql = "SELECT * FROM estatus WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id); // Establecer el ID del estatus
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre"); // Obtener el nombre del estatus
                // Crear un objeto Estado con los datos obtenidos
                estado = new Estado(id, nombre);
            }
        } catch (SQLException ex) {
            // Manejar la excepción en caso de error
            System.out.println("Error al leer estatus: " + ex.getMessage());
        } finally {
            // Asegurarse de desconectar en el bloque finally
            desconectar();
        }
        return estado; // Devolver el estatus leído
    }

    // Método para actualizar un estatus existente
    public void Actualizar(int idActual, int nuevoId, String nombre) {
        try {
            conectar(); // Conectar a la base de datos

            // Verificar si el nuevo ID ya está en uso si es diferente del ID actual
            if (nuevoId != idActual) {
                String checkSql = "SELECT COUNT(*) FROM estatus WHERE id = ?";
                PreparedStatement checkPs = cnx.prepareStatement(checkSql);
                checkPs.setInt(1, nuevoId); // Establecer el nuevo ID para verificación
                ResultSet checkRs = checkPs.executeQuery();
                checkRs.next();
                if (checkRs.getInt(1) > 0) {
                    // Mensaje si el nuevo ID ya está en uso
                    System.out.println("El nuevo ID ya esta en uso.");
                    return; // Salir del método
                }
            }

            // Actualizar el ID y el nombre del estatus
            String updateSql = "UPDATE estatus SET id = ?, nombre = ? WHERE id = ?";
            PreparedStatement updatePs = cnx.prepareStatement(updateSql);
            updatePs.setInt(1, nuevoId); // Establecer el nuevo ID
            updatePs.setString(2, nombre); // Establecer el nuevo nombre
            updatePs.setInt(3, idActual); // Establecer el ID actual
            updatePs.executeUpdate(); // Ejecutar la actualización
        } catch (SQLException ex) {
            // Manejar la excepción en caso de error
            System.out.println("Error al actualizar estatus: " + ex.getMessage());
        } finally {
            // Asegurarse de desconectar en el bloque finally
            desconectar();
        }
    }

    // Método para eliminar un estatus
    public void Eliminar(int id) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para eliminar un registro de la tabla estatus por su ID
            String sql = "DELETE FROM estatus WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id); // Establecer el ID del estatus
            ps.executeUpdate(); // Ejecutar la eliminación
        } catch (SQLException ex) {
            // Manejar la excepción en caso de error
            System.out.println("Error al eliminar estatus: " + ex.getMessage());
        } finally {
            // Asegurarse de desconectar en el bloque finally
            desconectar();
        }
    }

    // Método para listar los estatus e imprimir sus detalles en formato de tabla
    public void Consulta() {
        listarEstatus(); // Llamar al método para listar estatus
        // Imprimir en formato de tabla
        System.out.println("+----+-----------+");
        System.out.println("|     ESTATUS    |");
        System.out.println("+----+-----------+");
        System.out.println("| ID |  Estado   |");
        System.out.println("+----+-----------+");
        for (Estado estado : carteraEstado) {
            // Mostrar los detalles del estatus en formato de tabla
            System.out.printf("| %-2d | %-9s |\n", estado.getId(), estado.getNombre());
        }
        System.out.println("+----+-----------+");
    }
}

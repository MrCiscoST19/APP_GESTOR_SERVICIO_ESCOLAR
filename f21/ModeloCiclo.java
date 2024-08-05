package f21;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase ModeloCiclo extiende de la clase Conexion para utilizar sus métodos de conexión
public class ModeloCiclo extends Conexion {

    // Lista para almacenar los objetos Ciclo
    private ArrayList<Ciclo> carteraCiclo = new ArrayList<>();

    // Método para listar todos los ciclos desde la base de datos
    public void listarCiclos() {
        // Limpiar la lista antes de cargar nuevos datos
        this.carteraCiclo.clear();

        // Conectar a la base de datos
        try {
            conectar();
            // Consulta SQL para obtener todos los registros de la tabla ciclos
            String sql = "SELECT * FROM ciclos ORDER BY id;";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Iterar sobre los resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                // Crear un objeto Ciclo y agregarlo a la lista carteraCiclo
                Ciclo ciclo = new Ciclo(id, nombre);
                this.carteraCiclo.add(ciclo);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar ciclos: " + ex.getMessage());
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para obtener la lista de ciclos
    public ArrayList<Ciclo> getCarteraCiclo() {
        return carteraCiclo;
    }

    // Método para crear un nuevo ciclo
    public void Crear(String nombre) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para insertar un nuevo registro en la tabla ciclos
            String sql = "INSERT INTO ciclos (nombre) VALUES (?)";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, nombre); // Establecer el nombre del ciclo
            ps.executeUpdate(); // Ejecutar la actualización
        } catch (SQLException ex) {
            System.out.println("Error al crear ciclo: " + ex.getMessage());
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para leer un ciclo por su ID
    public Ciclo Leer(int id) {
        Ciclo ciclo = null;
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para obtener un registro de la tabla ciclos por su ID
            String sql = "SELECT * FROM ciclos WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id); // Establecer el ID del ciclo
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre"); // Obtener el nombre del ciclo
                // Crear un objeto Ciclo con los datos obtenidos
                ciclo = new Ciclo(id, nombre);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer ciclo: " + ex.getMessage());
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
        return ciclo; // Devolver el ciclo leído
    }

    // Método para actualizar un ciclo existente
    public void Actualizar(int idActual, int nuevoId, String nombre) {
        try {
            conectar(); // Conectar a la base de datos

            // Verificar si el nuevo ID ya está en uso si es diferente del ID actual
            if (nuevoId != idActual) {
                String checkSql = "SELECT COUNT(*) FROM ciclos WHERE id = ?";
                PreparedStatement checkPs = cnx.prepareStatement(checkSql);
                checkPs.setInt(1, nuevoId);
                ResultSet checkRs = checkPs.executeQuery();
                checkRs.next();
                if (checkRs.getInt(1) > 0) {
                    System.out.println("El nuevo ID ya está en uso."); // Mensaje si el nuevo ID ya está en uso
                    return; // Salir del método
                }
            }

            // Actualizar el ID y el nombre del ciclo
            String updateSql = "UPDATE ciclos SET id = ?, nombre = ? WHERE id = ?";
            PreparedStatement updatePs = cnx.prepareStatement(updateSql);
            updatePs.setInt(1, nuevoId); // Establecer el nuevo ID
            updatePs.setString(2, nombre); // Establecer el nuevo nombre
            updatePs.setInt(3, idActual); // Establecer el ID actual
            updatePs.executeUpdate(); // Ejecutar la actualización
        } catch (SQLException ex) {
            System.out.println("Error al actualizar ciclo: " + ex.getMessage());
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para eliminar un ciclo
    public void Eliminar(int id) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para eliminar un registro de la tabla ciclos por su ID
            String sql = "DELETE FROM ciclos WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id); // Establecer el ID del ciclo
            ps.executeUpdate(); // Ejecutar la eliminación
        } catch (SQLException ex) {
            System.out.println("Error al eliminar ciclo: " + ex.getMessage());
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para listar los ciclos e imprimir sus detalles en formato de tabla
    public void Consulta() {
        listarCiclos(); // Llamar al método para listar ciclos
        // Imprimir en formato de tabla
        System.out.println("+----+--------+");
        System.out.println("|    CICLOS   |");
        System.out.println("+----+--------+");
        System.out.println("| ID | Ciclo  |");
        System.out.println("+----+--------+");
        for (Ciclo ciclo : carteraCiclo) {
            // Mostrar los detalles del ciclo en formato de tabla
            System.out.printf("| %-2d | %-6s |\n", ciclo.getId(), ciclo.getNombre());
        }
        System.out.println("+----+--------+");
    }
}

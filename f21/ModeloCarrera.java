package f21;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase ModeloCarrera que extiende la clase Conexion
public class ModeloCarrera extends Conexion {

    // Lista para almacenar las carreras
    private ArrayList<Carrera> carteraCarrera = new ArrayList<>();

    // Método para listar todas las carreras y almacenarlas en carteraCarrera
    public void listarCarreras() {
        this.carteraCarrera.clear();  // Limpiar la lista antes de llenarla

        try {
            conectar();  // Conectar a la base de datos
            String sql = "SELECT * FROM carreras ORDER BY id;";  // Consulta SQL para obtener todas las carreras
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Iterar sobre el resultado de la consulta y agregar las carreras a la lista
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Carrera carrera = new Carrera(id, nombre);
                this.carteraCarrera.add(carrera);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar carreras: " + ex.getMessage());
        } finally {
            desconectar();  // Desconectar de la base de datos
        }
    }

    // Método para obtener la lista de carreras
    public ArrayList<Carrera> getCarteraCarrera() {
        return carteraCarrera;
    }

    // Método para crear una nueva carrera en la base de datos
    public void Crear(String nombre) {
        try {
            conectar();  // Conectar a la base de datos
            String sql = "INSERT INTO carreras (nombre) VALUES (?)";  // Consulta SQL para insertar una nueva carrera
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.executeUpdate();  // Ejecutar la consulta
        } catch (SQLException ex) {
            System.out.println("Error al crear carrera: " + ex.getMessage());
        } finally {
            desconectar();  // Desconectar de la base de datos
        }
    }

    // Método para leer una carrera específica de la base de datos por su ID
    public Carrera Leer(int id) {
        Carrera carrera = null;
        try {
            conectar();  // Conectar a la base de datos
            String sql = "SELECT * FROM carreras WHERE id = ?";  // Consulta SQL para obtener una carrera por ID
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                carrera = new Carrera(id, nombre);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer carrera: " + ex.getMessage());
        } finally {
            desconectar();  // Desconectar de la base de datos
        }
        return carrera;  // Devolver la carrera encontrada o null si no existe
    }

    // Método para actualizar una carrera existente en la base de datos
    public void Actualizar(int idActual, int nuevoId, String nombre) {
        try {
            conectar();  // Conectar a la base de datos

            // Si el nuevo ID es diferente al ID actual, verificar que no esté en uso
            if (nuevoId != idActual) {
                String checkSql = "SELECT COUNT(*) FROM carreras WHERE id = ?";
                PreparedStatement checkPs = cnx.prepareStatement(checkSql);
                checkPs.setInt(1, nuevoId);
                ResultSet checkRs = checkPs.executeQuery();
                checkRs.next();
                if (checkRs.getInt(1) > 0) {
                    System.out.println("El nuevo ID ya esta en uso.");
                    return;  // Si el nuevo ID está en uso, terminar el método
                }
            }

            // Consulta SQL para actualizar una carrera
            String updateSql = "UPDATE carreras SET id = ?, nombre = ? WHERE id = ?";
            PreparedStatement updatePs = cnx.prepareStatement(updateSql);
            updatePs.setInt(1, nuevoId);
            updatePs.setString(2, nombre);
            updatePs.setInt(3, idActual);
            updatePs.executeUpdate();  // Ejecutar la consulta de actualización
        } catch (SQLException ex) {
            System.out.println("Error al actualizar carrera: " + ex.getMessage());
        } finally {
            desconectar();  // Desconectar de la base de datos
        }
    }

    // Método para eliminar una carrera de la base de datos
    public void Eliminar(int id) {
        try {
            conectar();  // Conectar a la base de datos
            String sql = "DELETE FROM carreras WHERE id = ?";  // Consulta SQL para eliminar una carrera por ID
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();  // Ejecutar la consulta de eliminación
        } catch (SQLException ex) {
            System.out.println("Error al eliminar carrera: " + ex.getMessage());
        } finally {
            desconectar();  // Desconectar de la base de datos
        }
    }

    // Método para listar las carreras y mostrarlas en formato de tabla en la consola
    public void Consulta() {
        listarCarreras();  // Llamar al método listarCarreras para llenar la lista de carreras
        System.out.println("+----+---------+");
        System.out.println("|   CARRERAS   |");
        System.out.println("+----+---------+");
        System.out.println("| ID | Carrera |");
        System.out.println("+----+---------+");
        for (Carrera carrera : carteraCarrera) {
            System.out.printf("| %-2d | %-7s |\n", carrera.getId(), carrera.getNombre());
        }
        System.out.println("+----+---------+");
    }
}

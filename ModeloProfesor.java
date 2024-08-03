package f21; // Declaración del paquete

import java.sql.PreparedStatement; // Importación de la clase PreparedStatement para consultas SQL
import java.sql.ResultSet; // Importación de la clase ResultSet para manejar resultados de consultas SQL
import java.sql.SQLException; // Importación de la clase SQLException para manejar excepciones SQL
import java.util.ArrayList; // Importación de la clase ArrayList para listas dinámicas

// Clase ModeloProfesor extiende de la clase Conexion para utilizar sus métodos de conexión
public class ModeloProfesor extends Conexion {

    // Lista para almacenar los objetos Profesor
    private ArrayList<Profesor> carteraProfesor = new ArrayList<>();

    // Método para listar todos los profesores desde la base de datos
    public void listarProfesores() {
        // Limpiar la lista antes de cargar nuevos datos
        this.carteraProfesor.clear();

        // Conectar a la base de datos
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para obtener todos los registros de la tabla profesores
            String sql = "SELECT * FROM profesores ORDER BY id;";
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ResultSet rs = ps.executeQuery(); // Ejecutar la consulta y obtener resultados

            // Iterar sobre los resultados
            while (rs.next()) {
                int id = rs.getInt("id"); // Obtener el ID del profesor
                String nombre = rs.getString("nombre"); // Obtener el nombre del profesor
                // Crear un objeto Profesor y agregarlo a la lista carteraProfesor
                Profesor profesor = new Profesor(id, nombre);
                this.carteraProfesor.add(profesor); // Agregar el profesor a la lista
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar profesores: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para obtener la lista de profesores
    public ArrayList<Profesor> getCarteraProfesor() {
        return carteraProfesor; // Devolver la lista de profesores
    }

    // Método para crear un nuevo profesor
    public void Crear(String nombre) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para insertar un nuevo registro en la tabla profesores
            String sql = "INSERT INTO profesores (nombre) VALUES (?)";
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ps.setString(1, nombre); // Establecer el nombre del profesor
            ps.executeUpdate(); // Ejecutar la actualización
        } catch (SQLException ex) {
            System.out.println("Error al crear profesor: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para leer un profesor por su ID
    public Profesor Leer(int id) {
        Profesor profesor = null; // Inicializar el objeto profesor
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para obtener un registro de la tabla profesores por su ID
            String sql = "SELECT * FROM profesores WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ps.setInt(1, id); // Establecer el ID del profesor
            ResultSet rs = ps.executeQuery(); // Ejecutar la consulta y obtener resultados
            if (rs.next()) {
                String nombre = rs.getString("nombre"); // Obtener el nombre del profesor
                // Crear un objeto Profesor con los datos obtenidos
                profesor = new Profesor(id, nombre);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer profesor: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
        return profesor; // Devolver el profesor leído
    }

    // Método para actualizar un profesor existente
    public void Actualizar(int idActual, int nuevoId, String nombre) {
        try {
            conectar(); // Conectar a la base de datos

            // Verificar si el nuevo ID ya está en uso si es diferente del ID actual
            if (nuevoId != idActual) {
                String checkSql = "SELECT COUNT(*) FROM profesores WHERE id = ?";
                PreparedStatement checkPs = cnx.prepareStatement(checkSql); // Preparar la consulta SQL
                checkPs.setInt(1, nuevoId); // Establecer el nuevo ID
                ResultSet checkRs = checkPs.executeQuery(); // Ejecutar la consulta y obtener resultados
                checkRs.next();
                if (checkRs.getInt(1) > 0) {
                    System.out.println("El nuevo ID ya está en uso."); // Mensaje si el nuevo ID ya está en uso
                    return; // Salir del método
                }
            }

            // Actualizar el ID y el nombre del profesor
            String updateSql = "UPDATE profesores SET id = ?, nombre = ? WHERE id = ?";
            PreparedStatement updatePs = cnx.prepareStatement(updateSql); // Preparar la consulta SQL
            updatePs.setInt(1, nuevoId); // Establecer el nuevo ID
            updatePs.setString(2, nombre); // Establecer el nuevo nombre
            updatePs.setInt(3, idActual); // Establecer el ID actual
            updatePs.executeUpdate(); // Ejecutar la actualización
        } catch (SQLException ex) {
            System.out.println("Error al actualizar profesor: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para eliminar un profesor
    public void Eliminar(int id) {
        try {
            conectar(); // Conectar a la base de datos
            // Consulta SQL para eliminar un registro de la tabla profesores por su ID
            String sql = "DELETE FROM profesores WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ps.setInt(1, id); // Establecer el ID del profesor
            ps.executeUpdate(); // Ejecutar la eliminación
        } catch (SQLException ex) {
            System.out.println("Error al eliminar profesor: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para listar los profesores e imprimir sus detalles en formato de tabla
    public void Consulta() {
        listarProfesores(); // Llamar al método para listar profesores
        // Imprimir en formato de tabla
        System.out.println("+----+-------------------+");
        System.out.println("|       PROFESORES       |");
        System.out.println("+----+-------------------+");
        System.out.println("| ID |     Profesor      |");
        System.out.println("+----+-------------------+");
        for (Profesor profesor : carteraProfesor) {
            // Mostrar los detalles del profesor en formato de tabla
            System.out.printf("| %-2d | %-17s |\n", profesor.getId(), profesor.getNombre());
        }
        System.out.println("+----+-------------------+");
    }
}

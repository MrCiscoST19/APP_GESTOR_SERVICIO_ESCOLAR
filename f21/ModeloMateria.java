package f21; // Declaración del paquete

import java.sql.PreparedStatement; // Importa la clase PreparedStatement para manejar consultas SQL
import java.sql.ResultSet; // Importa la clase ResultSet para manejar los resultados de las consultas SQL
import java.sql.SQLException; // Importa la clase SQLException para manejar excepciones SQL
import java.util.ArrayList; // Importa la clase ArrayList para manejar listas dinámicas

// Clase ModeloMateria que gestiona las operaciones CRUD sobre la entidad Materia en la base de datos
public class ModeloMateria extends Conexion {

    // Lista que almacena las materias
    private ArrayList<Materia> carteraMateria = new ArrayList<>();

    // Método para listar todas las materias desde la base de datos
    public void listarMaterias() {
        this.carteraMateria.clear(); // Limpiar la lista antes de cargar los datos
        try {
            conectar(); // Establecer la conexión a la base de datos
            String sql = "SELECT * FROM materias ORDER BY id;"; // Consulta SQL para obtener todas las materias ordenadas por ID
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ResultSet rs = ps.executeQuery(); // Ejecutar la consulta y obtener el resultado

            // Iterar sobre los resultados y añadir cada materia a la lista
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String unidad = rs.getString("unidad");
                Materia materia = new Materia(id, nombre, descripcion, unidad);
                this.carteraMateria.add(materia);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar materias: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para obtener la lista de materias
    public ArrayList<Materia> getCarteraMateria() {
        return carteraMateria;
    }

    // Método para crear una nueva materia en la base de datos
    public void Crear(String nombre, String descripcion, String unidad) {
        try {
            conectar(); // Establecer la conexión a la base de datos
            String sql = "INSERT INTO materias (nombre, descripcion, unidad) VALUES (?, ?, ?)"; // Consulta SQL para insertar una nueva materia
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ps.setString(1, nombre); // Establecer el nombre de la materia
            ps.setString(2, descripcion); // Establecer la descripción de la materia
            ps.setString(3, unidad); // Establecer la cantidad de unidades de la materia
            ps.executeUpdate(); // Ejecutar la inserción
        } catch (SQLException ex) {
            System.out.println("Error al crear materia: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para leer una materia específica por su ID
    public Materia Leer(int id) {
        Materia materia = null; // Inicializar la materia como null
        try {
            conectar(); // Establecer la conexión a la base de datos
            String sql = "SELECT * FROM materias WHERE id = ?"; // Consulta SQL para obtener una materia por su ID
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ps.setInt(1, id); // Establecer el ID de la materia
            ResultSet rs = ps.executeQuery(); // Ejecutar la consulta y obtener el resultado
            if (rs.next()) { // Verificar si se encontró la materia
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String unidad = rs.getString("unidad");
                materia = new Materia(id, nombre, descripcion, unidad); // Crear una nueva instancia de Materia con los datos obtenidos
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer materia: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
        return materia; // Retornar la materia encontrada o null si no se encontró
    }

    // Método para actualizar una materia existente
    public void Actualizar(int idActual, Integer nuevoId, String nombre, String descripcion, String unidad) {
        PreparedStatement checkPs = null; // Declarar PreparedStatement para la verificación de ID
        ResultSet checkRs = null; // Declarar ResultSet para los resultados de la verificación
        PreparedStatement updatePs = null; // Declarar PreparedStatement para la actualización

        try {
            conectar(); // Establecer la conexión a la base de datos

            // Verificar si el nuevo ID ya está en uso si es diferente del ID actual
            if (nuevoId != null && !nuevoId.equals(idActual)) {
                String checkSql = "SELECT COUNT(*) FROM materias WHERE id = ?"; // Consulta SQL para verificar la existencia del nuevo ID
                checkPs = cnx.prepareStatement(checkSql); // Preparar la consulta SQL
                checkPs.setInt(1, nuevoId); // Establecer el nuevo ID
                checkRs = checkPs.executeQuery(); // Ejecutar la consulta y obtener el resultado
                checkRs.next();
                if (checkRs.getInt(1) > 0) {
                    System.out.println("El nuevo ID ya está en uso."); // Mensaje si el nuevo ID ya está en uso
                    return; // Salir del método
                }
            }

            // Actualizar el ID, nombre, descripción y unidad de la materia
            String updateSql = "UPDATE materias SET id = ?, nombre = ?, descripcion = ?, unidad = ? WHERE id = ?"; // Consulta SQL para actualizar la materia
            updatePs = cnx.prepareStatement(updateSql); // Preparar la consulta SQL
            updatePs.setInt(1, nuevoId != null ? nuevoId : idActual); // Establecer el nuevo ID o el ID actual si no se proporciona uno nuevo
            updatePs.setString(2, nombre); // Establecer el nuevo nombre
            updatePs.setString(3, descripcion); // Establecer la nueva descripción
            updatePs.setString(4, unidad); // Establecer la nueva unidad
            updatePs.setInt(5, idActual); // Establecer el ID actual
            updatePs.executeUpdate(); // Ejecutar la actualización

            System.out.println("Materia actualizada."); // Mensaje de éxito
        } catch (SQLException ex) {
            System.out.println("Error al actualizar materia: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            try {
                if (checkRs != null) checkRs.close(); // Cerrar el ResultSet si no es null
                if (checkPs != null) checkPs.close(); // Cerrar el PreparedStatement para la verificación si no es null
                if (updatePs != null) updatePs.close(); // Cerrar el PreparedStatement para la actualización si no es null
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage()); // Manejar errores al cerrar recursos
            }
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para eliminar una materia por su ID
    public void Eliminar(int id) {
        try {
            conectar(); // Establecer la conexión a la base de datos
            String sql = "DELETE FROM materias WHERE id = ?"; // Consulta SQL para eliminar una materia por su ID
            PreparedStatement ps = cnx.prepareStatement(sql); // Preparar la consulta SQL
            ps.setInt(1, id); // Establecer el ID de la materia a eliminar
            ps.executeUpdate(); // Ejecutar la eliminación
        } catch (SQLException ex) {
            System.out.println("Error al eliminar materia: " + ex.getMessage()); // Manejar errores de SQL
        } finally {
            desconectar(); // Asegurarse de desconectar en el bloque finally
        }
    }

    // Método para consultar y mostrar todas las materias en formato de tabla
    public void Consulta() {
        listarMaterias(); // Llamar al método para listar todas las materias
        // Mostrar los detalles de las materias en formato de tabla
        System.out.println("+----+------------+----------------------------------------------------+--------+");
        System.out.println("| id |  Materia   |              Descripcion                           | Unidad |");
        System.out.println("+----+------------+----------------------------------------------------+--------+");
        for (Materia materia : carteraMateria) {
            System.out.printf("| %-2d | %-10s | %-50s | %-6s |\n",
                    materia.getId(),
                    materia.getNombre(),
                    materia.getDescripcion(),
                    materia.getUnidad()); // Mostrar los detalles de cada materia
        }
        System.out.println("+----+------------+----------------------------------------------------+--------+");
    }
}

package f21;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeloAlumno extends Conexion {  // Clase ModeloAlumno que extiende la clase Conexion
    private ArrayList<Alumno> carteraAlumnos = new ArrayList<>();  // Lista para almacenar los alumnos

    // Método para listar los alumnos
    public void listarAlumnos() {
        carteraAlumnos.clear();  // Limpiar la lista de alumnos
        try {
            conectar();  // Conectar a la base de datos
            String sql = "SELECT " +
                    "alumnos.id, alumnos.nombre AS Alumno, grupos.id AS idGrupo, grupos.nombre AS Grupo, " +
                    "ciclos.id AS idCiclo, ciclos.nombre AS Ciclo, cuatrimestres.id AS idCuatrimestre, cuatrimestres.nombre AS Cuatrimestre, " +
                    "carreras.id AS idCarrera, carreras.nombre AS Carrera, estatus.id AS idEstado, estatus.nombre AS Estado " +
                    "FROM alumnos " +
                    "INNER JOIN grupos ON alumnos.id_grupo = grupos.id " +
                    "INNER JOIN estatus ON alumnos.id_estado = estatus.id " +
                    "INNER JOIN ciclo_cuatri ON grupos.id_ciclo_cuatri = ciclo_cuatri.id " +
                    "INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id " +
                    "INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id " +
                    "INNER JOIN carreras ON grupos.id_carrera = carreras.id " +
                    "ORDER BY alumnos.id;";  // Consulta SQL para obtener los alumnos y sus detalles
            PreparedStatement ps = cnx.prepareStatement(sql);  // Preparar la declaración SQL
            ResultSet rs = ps.executeQuery();  // Ejecutar la consulta
            while (rs.next()) {  // Iterar sobre los resultados
                int id = rs.getInt("id");
                String nombre = rs.getString("Alumno");
                int idGrupo = rs.getInt("idGrupo");
                String grupoNombre = rs.getString("Grupo");
                int idCiclo = rs.getInt("idCiclo");
                String cicloNombre = rs.getString("Ciclo");
                int idCuatrimestre = rs.getInt("idCuatrimestre");
                String cuatrimestreNombre = rs.getString("Cuatrimestre");
                int idCarrera = rs.getInt("idCarrera");
                String carreraNombre = rs.getString("Carrera");
                int idEstado = rs.getInt("idEstado");
                String estadoNombre = rs.getString("Estado");

                // Crear instancias de Ciclo y Cuatrimestre
                Ciclo ciclo = new Ciclo(idCiclo, cicloNombre);
                Cuatrimestre cuatrimestre = new Cuatrimestre(idCuatrimestre, cuatrimestreNombre);

                // Crear instancia de CC
                CC cicloCuatri = new CC(idCiclo, ciclo, cuatrimestre);

                // Crear instancia de Carrera
                Carrera carrera = new Carrera(idCarrera, carreraNombre);

                // Crear instancia de Grupo
                Grupo grupo = new Grupo(idGrupo, grupoNombre, cicloCuatri, carrera);

                // Crear instancia de Estado
                Estado estado = new Estado(idEstado, estadoNombre);

                // Crear instancia de Alumno
                Alumno alumno = new Alumno(id, nombre, grupo, estado);
                carteraAlumnos.add(alumno);  // Agregar el alumno a la lista
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar alumnos: " + ex.getMessage());
        } finally {
            desconectar();  // Desconectar de la base de datos
        }
    }

    // Método para crear un nuevo alumno
    public void Crear(String nombre, int idGrupo, int idEstado) {
        try {
            conectar();  // Conectar a la base de datos
            String sql = "INSERT INTO alumnos (nombre, id_grupo, id_estado) VALUES (?, ?, ?)";  // Consulta SQL para insertar un nuevo alumno
            PreparedStatement ps = cnx.prepareStatement(sql);  // Preparar la declaración SQL
            ps.setString(1, nombre);
            ps.setInt(2, idGrupo);
            ps.setInt(3, idEstado);
            ps.executeUpdate();  // Ejecutar la actualización
        } catch (SQLException ex) {
            System.out.println("Error al crear alumno: " + ex.getMessage());
        } finally {
            desconectar();  // Desconectar de la base de datos
        }
    }

    // Método para leer un alumno por su ID
    public Alumno Leer(int id) {
        Alumno alumno = null;  // Inicializar el alumno como null
        try {
            conectar();  // Conectar a la base de datos
            String sql = "SELECT " +
                    "alumnos.id, alumnos.nombre AS Alumno, grupos.id AS idGrupo, grupos.nombre AS Grupo, " +
                    "ciclos.id AS idCiclo, ciclos.nombre AS Ciclo, cuatrimestres.id AS idCuatrimestre, cuatrimestres.nombre AS Cuatrimestre, " +
                    "carreras.id AS idCarrera, carreras.nombre AS Carrera, estatus.id AS idEstado, estatus.nombre AS Estado " +
                    "FROM alumnos " +
                    "INNER JOIN grupos ON alumnos.id_grupo = grupos.id " +
                    "INNER JOIN estatus ON alumnos.id_estado = estatus.id " +
                    "INNER JOIN ciclo_cuatri ON grupos.id_ciclo_cuatri = ciclo_cuatri.id " +
                    "INNER JOIN ciclos ON ciclo_cuatri.id_ciclo = ciclos.id " +
                    "INNER JOIN cuatrimestres ON ciclo_cuatri.id_cuatri = cuatrimestres.id " +
                    "INNER JOIN carreras ON grupos.id_carrera = carreras.id " +
                    "WHERE alumnos.id = ?";  // Consulta SQL para obtener un alumno por su ID
            PreparedStatement ps = cnx.prepareStatement(sql);  // Preparar la declaración SQL
            ps.setInt(1, id);  // Establecer el parámetro ID
            ResultSet rs = ps.executeQuery();  // Ejecutar la consulta
            if (rs.next()) {  // Si se encuentra un resultado
                String nombre = rs.getString("Alumno");
                int idGrupo = rs.getInt("idGrupo");
                String grupoNombre = rs.getString("Grupo");
                int idCiclo = rs.getInt("idCiclo");
                String cicloNombre = rs.getString("Ciclo");
                int idCuatrimestre = rs.getInt("idCuatrimestre");
                String cuatrimestreNombre = rs.getString("Cuatrimestre");
                int idCarrera = rs.getInt("idCarrera");
                String carreraNombre = rs.getString("Carrera");
                int idEstado = rs.getInt("idEstado");
                String estadoNombre = rs.getString("Estado");

                // Crear instancias de Ciclo y Cuatrimestre
                Ciclo ciclo = new Ciclo(idCiclo, cicloNombre);
                Cuatrimestre cuatrimestre = new Cuatrimestre(idCuatrimestre, cuatrimestreNombre);

                // Crear instancia de CC
                CC cicloCuatri = new CC(idCiclo, ciclo, cuatrimestre);

                // Crear instancia de Carrera
                Carrera carrera = new Carrera(idCarrera, carreraNombre);

                // Crear instancia de Grupo
                Grupo grupo = new Grupo(idGrupo, grupoNombre, cicloCuatri, carrera);

                // Crear instancia de Estado
                Estado estado = new Estado(idEstado, estadoNombre);

                // Crear instancia de Alumno
                alumno = new Alumno(id, nombre, grupo, estado);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer alumno: " + ex.getMessage());
        } finally {
            desconectar();  // Desconectar de la base de datos
        }
        return alumno;  // Devolver el alumno
    }

    // Método para actualizar un alumno
    public void Actualizar(int id, int nuevoIdAlumno, String nombre, int idGrupo, int idEstado) {
        try {
            conectar();  // Conectar a la base de datos
            
            // Verificar y ajustar valores para los campos opcionales
            if (idGrupo == -1) {
                idGrupo = Leer(id).getGrupo().getId();
            }
            if (idEstado == -1) {
                idEstado = Leer(id).getEstado().getId();
            }

            // Verificar si idGrupo existe
            String sqlGrupo = "SELECT COUNT(*) FROM grupos WHERE id = ?";
            PreparedStatement psGrupo = cnx.prepareStatement(sqlGrupo);
            psGrupo.setInt(1, idGrupo);
            ResultSet rsGrupo = psGrupo.executeQuery();
            if (rsGrupo.next() && rsGrupo.getInt(1) == 0) {
                System.out.println("ID de grupo no valido.");
                return;
            }
            
            // Verificar si idEstado existe
            String sqlEstado = "SELECT COUNT(*) FROM estatus WHERE id = ?";
            PreparedStatement psEstado = cnx.prepareStatement(sqlEstado);
            psEstado.setInt(1, idEstado);
            ResultSet rsEstado = psEstado.executeQuery();
            if (rsEstado.next() && rsEstado.getInt(1) == 0) {
                System.out.println("ID de estado no valido.");
                return;
            }

            // Actualizar el alumno
            String sql = "UPDATE alumnos SET id = ?, nombre = ?, id_grupo = ?, id_estado = ? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, nuevoIdAlumno);
            ps.setString(2, nombre);
            ps.setInt(3, idGrupo);
            ps.setInt(4, idEstado);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar alumno: " + ex.getMessage());
        } finally {
            desconectar();  // Desconectar de la base de datos
        }
    }

    // Método para eliminar un alumno
    public void Eliminar(int id) {
        try {
            conectar();  // Conectar a la base de datos
            String sql = "DELETE FROM alumnos WHERE id = ?";  // Consulta SQL para eliminar un alumno
            PreparedStatement ps = cnx.prepareStatement(sql);  // Preparar la declaración SQL
            ps.setInt(1, id);  // Establecer el parámetro ID
            ps.executeUpdate();  // Ejecutar la actualización
        } catch (SQLException ex) {
            System.out.println("Error al eliminar alumno: " + ex.getMessage());
        } finally {
            desconectar();  // Desconectar de la base de datos
        }
    }

    // Método para obtener la lista de alumnos
    public ArrayList<Alumno> getCarteraAlumnos() {
        return carteraAlumnos;
    }
}

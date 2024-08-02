package f21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Clase Conexion para manejar la conexión con la base de datos
public class Conexion {
    // Atributo protegido que mantiene la conexión a la base de datos
    protected Connection cnx;

    // Método para establecer la conexión a la base de datos
    public Connection conectar() {
        try {
            // Cargar el driver para la base de datos MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            // Establecer la conexión a la base de datos usando la URL, el usuario y la contraseña
            this.cnx = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/ce21", "root", "");
            System.out.println("Conexion exitosa a la base de datos");
        } catch (ClassNotFoundException | SQLException ex) {
            // Capturar y manejar las excepciones de clase no encontrada y SQL
            System.out.println("Error: " + ex.getMessage());
        }
        return cnx; // Devolver la conexión establecida
    }

    // Método para cerrar la conexión a la base de datos
    public void desconectar() {
        try {
            // Verificar si la conexión no es nula y está abierta
            if (this.cnx != null && !this.cnx.isClosed()) {
                this.cnx.close(); // Cerrar la conexión
                
            }
        } catch (SQLException ex) {
            // Capturar y manejar las excepciones SQL
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
}

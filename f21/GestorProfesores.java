package f21; // Declaración del paquete

import java.util.Scanner; // Importación de la clase Scanner para la entrada del usuario

// Clase GestorProfesores extiende ModeloProfesor para usar sus métodos de CRUD
public class GestorProfesores extends ModeloProfesor {

    // Instancia de Scanner para leer la entrada del usuario
    Scanner scanner = new Scanner(System.in);

    // Método principal que muestra el menú y maneja la selección del usuario
    public void menu() {
        int opcion; // Declaración de variable para almacenar la opción del usuario
        do {
            // Mostrar el menú de opciones
            System.out.println("Bienvenido al menu de profesores");
            System.out.println("\n---Gestor---de---Profesores---");
            System.out.println("|1|. Listar ");
            System.out.println("|2|. Crear ");
            System.out.println("|3|. Leer ");
            System.out.println("|4|. Actualizar ");
            System.out.println("|5|. Eliminar ");
            System.out.println("|0|. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt(); // Leer la opción del usuario
            scanner.nextLine(); // Limpiar el buffer
            // Ejecutar la opción seleccionada
            switch (opcion) {
                case 1:
                    listar(); // Llamar al método para listar profesores
                    break;
                case 2:
                    crear(); // Llamar al método para crear un profesor
                    break;
                case 3:
                    leer(); // Llamar al método para leer un profesor
                    break;
                case 4:
                    actualizar(); // Llamar al método para actualizar un profesor
                    break;
                case 5:
                    eliminar(); // Llamar al método para eliminar un profesor
                    break;
                case 0:
                    System.out.println("Saliendo..."); // Mensaje de salida
                    break;
                default:
                    System.out.println("Opcion invalida"); // Mensaje para opción no válida
            }
        } while (opcion != 0); // Repetir hasta que el usuario elija salir
    }

    // Método para listar todos los profesores utilizando el método del modelo
    public void listar() {
        Consulta(); // Llamar al método Consulta del modelo para listar profesores
    }

    // Método para crear un nuevo profesor
    public void crear() {
        System.out.print("Ingrese nombre del profesor: ");
        String nombre = scanner.nextLine(); // Leer el nombre del profesor
        Crear(nombre); // Llamar al método Crear del modelo
        System.out.println("Profesor agregado"); // Mensaje de confirmación
    }

    // Método para leer los detalles de un profesor por su ID
    public void leer() {
        listar(); // Llamar al método para listar profesores
        System.out.print("Ingrese ID del profesor que desea consultar: ");
        int id = scanner.nextInt(); // Leer el ID del profesor
        scanner.nextLine(); // Limpiar el buffer
        Profesor profesor = Leer(id); // Llamar al método Leer del modelo
        if (profesor != null) {
            // Mostrar los detalles del profesor en formato de tabla si se encuentra
            System.out.println("+----+-------------------+");
            System.out.println("|       PROFESORES       |");
            System.out.println("+----+-------------------+");
            System.out.println("| ID |     Profesor      |");
            System.out.println("+----+-------------------+");
            System.out.printf("| %-2d | %-17s |\n", profesor.getId(), profesor.getNombre());
            System.out.println("+----+-------------------+");
        } else {
            System.out.println("Profesor no encontrado"); // Mensaje si no se encuentra
        }
    }

    // Método para actualizar un profesor existente
    public void actualizar() {
        listar(); // Llamar al método para listar profesores
        System.out.print("Ingrese ID del profesor a actualizar: ");
        int idActual = scanner.nextInt(); // Leer el ID actual del profesor
        scanner.nextLine(); // Limpiar el buffer

        // Leer el nuevo ID del profesor
        System.out.print("Ingrese nuevo ID del profesor (dejar en blanco para no realizar cambios): ");
        String nuevoIdInput = scanner.nextLine();
        int nuevoId = nuevoIdInput.isEmpty() ? idActual : Integer.parseInt(nuevoIdInput);

        // Leer el nuevo nombre del profesor
        System.out.print("Ingrese nuevo nombre del profesor (dejar en blanco para no realizar cambios): ");
        String nombre = scanner.nextLine();

        // Obtener el profesor actual
        Profesor profesor = Leer(idActual);
        if (profesor != null) {
            if (nombre.isEmpty()) {
                nombre = profesor.getNombre(); // Mantener el nombre actual si no se ingresa uno nuevo
            }
            Actualizar(idActual, nuevoId, nombre); // Llamar al método Actualizar del modelo
            System.out.println("Profesor actualizado"); // Mensaje de confirmación
        } else {
            System.out.println("Profesor no encontrado"); // Mensaje si no se encuentra
        }
    }

    // Método para eliminar un profesor por su ID
    public void eliminar() {
        listar(); // Llamar al método para listar profesores
        System.out.print("Ingrese ID del profesor que desea eliminar: ");
        int id = scanner.nextInt(); // Leer el ID del profesor
        scanner.nextLine(); // Limpiar el buffer
        Eliminar(id); // Llamar al método Eliminar del modelo
        System.out.println("Profesor eliminado"); // Mensaje de confirmación
    }

    // Clase interna Main para ejecutar el programa
    public static class Main {

        public static void main(String[] args) {
            GestorProfesores gestor = new GestorProfesores(); // Crear una instancia de GestorProfesores
            gestor.menu();  // Mostrar el menú de gestión de profesores
        }
    }
}

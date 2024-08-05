package f21; // Declaración del paquete

import java.util.Scanner; // Importa la clase Scanner para leer la entrada del usuario

// Clase GestorMaterias que maneja las operaciones CRUD sobre materias
public class GestorMaterias extends ModeloMateria {

    // Instancia de Scanner para leer la entrada del usuario
    Scanner scanner = new Scanner(System.in);

    // Método para mostrar el menú y manejar la selección del usuario
    public void menu() {
        int opcion;
        do {
            // Mostrar el menú de opciones
            System.out.println("Bienvenido al menu de materias");
            System.out.println("\n---Gestor---de---Materias---");
            System.out.println("|1|. Listar ");
            System.out.println("|2|. Crear ");
            System.out.println("|3|. Leer ");
            System.out.println("|4|. Actualizar ");
            System.out.println("|5|. Eliminar ");
            System.out.println("|0|. Salir");
            System.out.print("Seleccione una opcion: ");
            // Validar que la entrada sea un número entero
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un numero entero.");
                scanner.next(); // Limpiar el buffer
            }
            opcion = scanner.nextInt(); // Leer la opción del usuario
            scanner.nextLine(); // Limpiar el buffer
            // Ejecutar la opción seleccionada
            switch (opcion) {
                case 1:
                    listar(); // Llamar al método para listar materias
                    break;
                case 2:
                    crear(); // Llamar al método para crear una nueva materia
                    break;
                case 3:
                    leer(); // Llamar al método para leer los detalles de una materia
                    break;
                case 4:
                    actualizar(); // Llamar al método para actualizar una materia existente
                    break;
                case 5:
                    eliminar(); // Llamar al método para eliminar una materia
                    break;
                case 0:
                    System.out.println("Saliendo..."); // Mensaje de salida
                    break;
                default:
                    System.out.println("Opcion invalida."); // Mensaje para opción no válida
            }
        } while (opcion != 0); // Repetir hasta que el usuario elija salir
    }

    // Método para listar todas las materias
    public void listar() {
        Consulta(); // Llamar al método Consulta del modelo para listar materias
    }

    // Método para crear una nueva materia
    public void crear() {
        System.out.print("Ingrese nombre de la materia: ");
        String nombre = scanner.nextLine(); // Leer el nombre de la materia
        System.out.print("Ingrese descripcion de la materia: ");
        String descripcion = scanner.nextLine(); // Leer la descripción de la materia
        System.out.print("Ingrese la cantidad de unidades de la materia: ");
        String unidad = scanner.nextLine(); // Leer la cantidad de unidades de la materia
        Crear(nombre, descripcion, unidad); // Llamar al método Crear del modelo
        System.out.println("Materia agregada"); // Mensaje de confirmación
    }

    // Método para leer los detalles de una materia por su ID
    public void leer() {
        listar(); // Mostrar la lista de materias
        System.out.print("Ingrese ID de la materia que desea consultar: ");
        int id = scanner.nextInt(); // Leer el ID de la materia
        scanner.nextLine(); // Limpiar el buffer
        Materia materia = Leer(id); // Llamar al método Leer del modelo
        if (materia != null) {
            // Mostrar los detalles de la materia en formato de tabla
            System.out.println("+----+------------+----------------------------------------------------+--------+");
            System.out.println("| id |  Materia   |              Descripcion                           | Unidad |");
            System.out.println("+----+------------+----------------------------------------------------+--------+");
            System.out.printf("| %2d | %-10s | %-50s | %-6s |\n",
                    materia.getId(),
                    materia.getNombre(),
                    materia.getDescripcion(),
                    materia.getUnidad());
            System.out.println("+----+------------+----------------------------------------------------+--------+");
        } else {
            System.out.println("Materia no encontrada"); // Mensaje si no se encuentra la materia
        }
    }

    // Método para actualizar una materia existente
    public void actualizar() {
        listar(); // Mostrar la lista de materias antes de actualizar
        System.out.print("Ingrese ID de la materia que desea actualizar: ");
        int idActual = scanner.nextInt(); // Leer el ID actual de la materia
        scanner.nextLine(); // Limpiar el buffer

        // Verificar si la materia existe
        Materia materia = Leer(idActual);
        if (materia == null) {
            System.out.println("Materia no encontrada."); // Mensaje si no se encuentra la materia
            return; // Salir del método
        }

        // Leer el nuevo ID de la materia
        System.out.print("Ingrese nuevo ID de la materia (dejar en blanco para no realizar cambios): ");
        String nuevoIdInput = scanner.nextLine();
        Integer nuevoId = null;
        if (!nuevoIdInput.isEmpty()) {
            try {
                nuevoId = Integer.parseInt(nuevoIdInput); // Convertir el nuevo ID a entero
            } catch (NumberFormatException e) {
                System.out.println("ID inválido. Manteniendo el ID actual."); // Mensaje si el nuevo ID es inválido
                nuevoId = idActual; // Mantener el ID actual
            }
        } else {
            nuevoId = idActual; // Mantener el ID actual si no se ingresa un nuevo ID
        }

        // Leer el nuevo nombre de la materia
        System.out.print("Ingrese nuevo nombre de la materia (dejar en blanco para no realizar cambios): ");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) {
            nombre = materia.getNombre(); // Mantener el nombre actual si no se ingresa uno nuevo
        }

        // Leer nueva descripción de la materia
        System.out.print("Ingrese nueva descripcion de la materia (dejar en blanco para no realizar cambios): ");
        String descripcion = scanner.nextLine();
        if (descripcion.isEmpty()) {
            descripcion = materia.getDescripcion(); // Mantener la descripción actual si no se ingresa una nueva
        }

        // Leer nueva cantidad de unidades de la materia
        System.out.print("Ingrese nueva cantidad de unidades de la materia (dejar en blanco para no realizar cambios): ");
        String unidad = scanner.nextLine();
        if (unidad.isEmpty()) {
            unidad = materia.getUnidad(); // Mantener la unidad actual si no se ingresa una nueva
        }

        try {
            // Actualizar materia en el modelo
            Actualizar(idActual, nuevoId, nombre, descripcion, unidad);
            System.out.println("Materia actualizada."); // Mensaje de confirmación
        } catch (Exception e) {
            System.out.println("Error al actualizar materia: " + e.getMessage()); // Mensaje de error
        }
    }

    // Método para eliminar una materia por su ID
    public void eliminar() {
        listar(); // Mostrar la lista de materias
        System.out.print("Ingrese ID de la materia que desea eliminar: ");
        int id = scanner.nextInt(); // Leer el ID de la materia
        scanner.nextLine(); // Limpiar el buffer
        Eliminar(id); // Llamar al método Eliminar del modelo
        System.out.println("Materia eliminada"); // Mensaje de confirmación
    }

    // Clase Main para ejecutar el programa
    public static class Main {

        // Método principal para iniciar la aplicación
        public static void main(String[] args) {
            GestorMaterias gestor = new GestorMaterias(); // Crear una instancia de GestorMaterias
            gestor.menu(); // Mostrar el menú de gestión de materias
        }
    }
}

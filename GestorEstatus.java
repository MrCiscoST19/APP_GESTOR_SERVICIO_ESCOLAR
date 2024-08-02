package f21;

import java.util.Scanner;

// Clase GestorEstatus extiende ModeloEstado para usar sus métodos de CRUD
public class GestorEstatus extends ModeloEstado {

    // Instancia de Scanner para leer la entrada del usuario
    Scanner scanner = new Scanner(System.in);

    // Método principal que muestra el menú y maneja la selección del usuario
    public void menu() {
        int opcion; // Variable para almacenar la opción seleccionada por el usuario
        do {
            // Mostrar el menú de opciones
            System.out.println("Bienvenido al menu de estatus");
            System.out.println("\n---Gestor---de---Estatus---");
            System.out.println("|1|. Listar ");
            System.out.println("|2|. Crear ");
            System.out.println("|3|. Leer ");
            System.out.println("|4|. Actualizar ");
            System.out.println("|5|. Eliminar ");
            System.out.println("|0|. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt(); // Leer la opción del usuario
            scanner.nextLine(); // Limpiar el buffer del Scanner
            // Ejecutar la opción seleccionada
            switch (opcion) {
                case 1:
                    listar(); // Llamar al método para listar estatus
                    break;
                case 2:
                    crear(); // Llamar al método para crear un estatus
                    break;
                case 3:
                    leer(); // Llamar al método para leer un estatus
                    break;
                case 4:
                    actualizar(); // Llamar al método para actualizar un estatus
                    break;
                case 5:
                    eliminar(); // Llamar al método para eliminar un estatus
                    break;
                case 0:
                    System.out.println("Saliendo..."); // Mensaje de salida
                    break;
                default:
                    System.out.println("Opcion invalida"); // Mensaje para opción no válida
            }
        } while (opcion != 0); // Repetir hasta que el usuario elija salir
    }

    // Método para listar todos los estatus utilizando el método del modelo
    public void listar() {
        Consulta(); // Asume que el método Consulta() lista todos los estatus en formato tabla
    }

    // Método para crear un nuevo estatus
    public void crear() {
        System.out.print("Ingrese el nombre del nuevo estatus: ");
        String nombre = scanner.nextLine(); // Leer el nombre del estatus
        Crear(nombre); // Llamar al método para crear el estatus
        System.out.println("Estatus creado exitosamente."); // Mensaje de éxito
    }

    // Método para leer un estatus por su ID y mostrarlo en formato de tabla
    public void leer() {
        Consulta(); // Primero, listar todos los estatus para asegurar que la información esté actualizada

        // Leer el ID del estatus a buscar
        System.out.print("Ingrese el ID del Estatus que deseas consultar: ");
        int id = scanner.nextInt(); // Leer el ID del estatus
        scanner.nextLine(); // Limpiar el buffer del Scanner

        // Obtener el estatus por su ID
        Estado estado = Leer(id); // Llamar al método para leer el estatus

        // Imprimir en formato de tabla
        System.out.println("Consultando en la base de datos..........");
        System.out.println("+----+-----------+");
        System.out.println("| ID |  Estado   |");
        System.out.println("+----+-----------+");
        // Mostrar los detalles del estatus en formato de tabla
        if (estado != null) {
            System.out.printf("| %-2d | %-9s |\n", estado.getId(), estado.getNombre());
        } else {
            System.out.println("| ID no encontrado.             |");
        }
        // Mostrar el pie de la tabla
        System.out.println("+----+-----------+");
    }

    // Método para actualizar un estatus existente
    public void actualizar() {
        Consulta(); // Llamar al método para listar estatus en ModeloEstado
        System.out.print("Ingrese el ID del estatus que deseas actualizar: "); // Solicitar el ID del estatus a actualizar
        int id = scanner.nextInt(); // Leer el ID del estatus
        scanner.nextLine(); // Limpiar el buffer del Scanner

        // Obtener datos actuales del estatus
        Estado estado = Leer(id); // Llamar al método para leer el estatus en ModeloEstado
        if (estado == null) {
            System.out.println("El estatus con el ID especificado no existe."); // Mensaje si el estatus no existe
            return; // Salir del método
        }

        // Leer el nuevo ID y nombre
        System.out.print("Ingrese el nuevo ID del estatus (dejar en blanco para no cambiar): "); // Solicitar el nuevo ID
        String nuevoIdStr = scanner.nextLine(); // Leer el nuevo ID como cadena

        // Leer el nuevo nombre del estatus
        System.out.print("Ingrese el nuevo nombre del estatus (dejar en blanco para no cambiar): "); // Solicitar el nuevo nombre
        String nuevoNombre = scanner.nextLine(); // Leer el nuevo nombre

        // Verificar si el nuevo ID está vacío; si es así, mantener el ID actual
        int nuevoId = id;
        if (!nuevoIdStr.isEmpty()) {
            try {
                nuevoId = Integer.parseInt(nuevoIdStr); // Convertir la cadena a entero
            } catch (NumberFormatException e) {
                System.out.println("ID no valido, se mantendrá el ID actual."); // Mensaje de error si el ID no es válido
                nuevoId = id; // Mantener el ID actual
            }
        }

        // Verificar si el nuevo nombre está vacío; si es así, mantener el nombre actual
        if (nuevoNombre.isEmpty()) {
            nuevoNombre = estado.getNombre(); // Mantener el nombre actual
        }

        // Llamar al método de actualización con el nuevo ID y nombre
        Actualizar(id, nuevoId, nuevoNombre); // Llamar al método para actualizar el estatus en ModeloEstado
        System.out.println("Actualizando..................."); // Mensaje de actualización
        System.out.println("Estatus actualizado exitosamente."); // Mensaje de éxito
    }

    // Método para eliminar un estatus
    public void eliminar() {
        Consulta(); // Llamar al método para listar estatus en ModeloEstado
        System.out.print("Ingrese el ID del estatus a eliminar: "); // Solicitar el ID del estatus a eliminar
        int id = scanner.nextInt(); // Leer el ID del estatus a eliminar
        scanner.nextLine(); // Limpiar el buffer del Scanner
        Eliminar(id); // Llamar al método para eliminar el estatus en ModeloEstado
        System.out.println("Estatus eliminado exitosamente."); // Mensaje de éxito
    }

    // Método main para ejecutar la aplicación
    public static void main(String[] args) {
        GestorEstatus gestorEstatus = new GestorEstatus(); // Crear una instancia de GestorEstatus
        gestorEstatus.menu(); // Mostrar el menú y manejar la selección del usuario
    }
}

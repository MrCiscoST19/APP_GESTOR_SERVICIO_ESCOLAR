package f21;

import java.util.Scanner;

// Clase GestorCiclos extiende ModeloCiclo para usar sus métodos de CRUD
public class GestorCiclos extends ModeloCiclo {

    // Instancia de Scanner para leer la entrada del usuario
    Scanner scanner = new Scanner(System.in);

    // Método principal que muestra el menú y maneja la selección del usuario
    public void menu() {
        int opcion;
        do {
            // Mostrar el menú de opciones
            System.out.println("Bienvenido al menu de ciclos");
            System.out.println("\n---Gestor---de---Ciclos---");
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
                    listar(); // Llamar al método para listar ciclos
                    break;
                case 2:
                    crear(); // Llamar al método para crear un ciclo
                    break;
                case 3:
                    leer(); // Llamar al método para leer un ciclo
                    break;
                case 4:
                    actualizar(); // Llamar al método para actualizar un ciclo
                    break;
                case 5:
                    eliminar(); // Llamar al método para eliminar un ciclo
                    break;
                case 0:
                    System.out.println("Saliendo..."); // Mensaje de salida
                    break;
                default:
                    System.out.println("Opcion invalida"); // Mensaje para opción no válida
            }
        } while (opcion != 0); // Repetir hasta que el usuario elija salir
    }

    // Método para listar todos los ciclos utilizando el método del modelo
    public void listar() {
        Consulta(); // Llamar al método para listar ciclos en ModeloCiclo
    }

    // Método para crear un nuevo ciclo utilizando el método del modelo
    public void crear() {
        System.out.print("Ingrese el Nombre del Ciclo: "); // Solicitar el nombre del ciclo
        String nombre = scanner.nextLine(); // Leer el nombre del ciclo
        Crear(nombre); // Llamar al método para crear el ciclo en ModeloCiclo
        System.out.println("Creando................");
        System.out.println("Ciclo creado exitosamente."); // Mensaje de éxito
    }

    // Método para leer un ciclo por su ID utilizando el método del modelo
    public void leer() {
        Consulta(); // Llamar al método para listar ciclos en ModeloCiclo
        System.out.print("Ingrese el ID del Ciclo que deseas consultar: "); // Solicitar el ID del ciclo
        int id = scanner.nextInt(); // Leer el ID del ciclo
        scanner.nextLine(); // Limpiar el buffer
        Ciclo ciclo = Leer(id); // Llamar al método para leer el ciclo en ModeloCiclo

        // Imprimir en formato de tabla
        System.out.println("Consultando en la base de datos..........");
        System.out.println("+----+--------+");
        System.out.println("| ID | Ciclo  |");
        System.out.println("+----+--------+");
        if (ciclo != null) {
            // Mostrar los detalles del ciclo encontrado
            System.out.printf("| %-2d | %-6s |\n", ciclo.getId(), ciclo.getNombre());
        } else {
            // Mensaje si el ciclo no existe
            System.out.println("| No existe el ciclo.        |");
        }
        System.out.println("+----+--------+");
    }

    // Método para actualizar un ciclo utilizando el método del modelo
    public void actualizar() {
        Consulta(); // Llamar al método para listar ciclos en ModeloCiclo
        System.out.print("Ingrese el ID del Ciclo que deseas actualizar: "); // Solicitar el ID del ciclo a actualizar
        int id = scanner.nextInt(); // Leer el ID del ciclo
        scanner.nextLine(); // Limpiar el buffer

        // Obtener datos actuales del ciclo
        Ciclo ciclo = Leer(id); // Llamar al método para leer el ciclo en ModeloCiclo
        if (ciclo == null) {
            System.out.println("El ciclo con el ID especificado no existe."); // Mensaje si el ciclo no existe
            return; // Salir del método
        }

        // Leer el nuevo ID y nombre
        System.out.print("Ingrese el nuevo ID del Ciclo (dejar en blanco para no cambiar): "); // Solicitar el nuevo ID
        String nuevoIdStr = scanner.nextLine(); // Leer el nuevo ID como cadena

        // Leer el nuevo nombre del ciclo
        System.out.print("Ingrese Nuevo nombre del Ciclo (dejar en blanco para no cambiar): "); // Solicitar el nuevo nombre
        String nuevoNombre = scanner.nextLine(); // Leer el nuevo nombre

        // Verificar si el nuevo ID está vacío; si es así, mantener el ID actual
        int nuevoId = id;
        if (!nuevoIdStr.isEmpty()) {
            try {
                nuevoId = Integer.parseInt(nuevoIdStr); // Convertir la cadena a entero
            } catch (NumberFormatException e) {
                System.out.println("ID no valido, se mantendra el ID actual."); // Mensaje de error si el ID no es válido
                nuevoId = id; // Mantener el ID actual
            }
        }

        // Verificar si el nuevo nombre está vacío; si es así, mantener el nombre actual
        if (nuevoNombre.isEmpty()) {
            nuevoNombre = ciclo.getNombre(); // Mantener el nombre actual
        }

        // Llamar al método de actualización con el nuevo ID y nombre
        Actualizar(id, nuevoId, nuevoNombre); // Llamar al método para actualizar el ciclo en ModeloCiclo
        System.out.println("Actualizando..................."); // Mensaje de actualización
        System.out.println("Ciclo actualizado exitosamente."); // Mensaje de éxito
    }

    // Método para eliminar un ciclo utilizando el método del modelo
    public void eliminar() {
        Consulta(); // Llamar al método para listar ciclos en ModeloCiclo
        System.out.print("Ingrese el ID del Ciclo a eliminar: "); // Solicitar el ID del ciclo a eliminar
        int id = scanner.nextInt(); // Leer el ID del ciclo
        scanner.nextLine(); // Limpiar el buffer
        Eliminar(id); // Llamar al método para eliminar el ciclo en ModeloCiclo
        System.out.println("Eliminando.................."); // Mensaje de eliminación
        System.out.println("Ciclo eliminado exitosamente."); // Mensaje de éxito
    }

    // Clase principal para ejecutar el menú
    public static class Main {

        public static void main(String[] args) {
            GestorCiclos gestor = new GestorCiclos(); // Crear una instancia de GestorCiclos
            gestor.menu(); // Mostrar el menú y gestionar las opciones del usuario
        }
    }
}

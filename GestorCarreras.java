package f21;

import java.util.Scanner;

// La clase GestorCarreras extiende la funcionalidad de ModeloCarrera
public class GestorCarreras extends ModeloCarrera {

    // Instancia de Scanner para leer la entrada del usuario
    Scanner scanner = new Scanner(System.in);

    // Método principal que muestra el menú y gestiona las opciones seleccionadas por el usuario
    public void menu() {
        int opcion;
        do {
            // Mostrar las opciones del menú al usuario
            System.out.println("Bienvenido al menu de carreras");
            System.out.println("\n---Gestor---de---Carreras---");
            System.out.println("|1|. Listar ");
            System.out.println("|2|. Crear ");
            System.out.println("|3|. Leer ");
            System.out.println("|4|. Actualizar ");
            System.out.println("|5|. Eliminar ");
            System.out.println("|0|. Salir");
            System.out.print("Seleccione una opcion: ");

            // Leer la opción seleccionada por el usuario
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer de entrada

            // Ejecutar la acción correspondiente a la opción seleccionada
            switch (opcion) {
                case 1:
                    listar();
                    break;
                case 2:
                    crear();
                    break;
                case 3:
                    leer();
                    break;
                case 4:
                    actualizar();
                    break;
                case 5:
                    eliminar();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }

    // Método para listar todas las carreras
    public void listar() {
        Consulta();  // Llama al método Consulta de la clase base
    }

    // Método para crear una nueva carrera
    public void crear() {
        System.out.print("Ingrese el Nombre de la Carrera: ");
        String nombre = scanner.nextLine();
        Crear(nombre);  // Llama al método Crear de la clase base
        System.out.println("Creando................");
        System.out.println("Carrera creada exitosamente.");
    }

    // Método para leer (consultar) una carrera específica por su ID
    public void leer() {
        Consulta();  // Llama al método Consulta para mostrar todas las carreras disponibles
        System.out.print("Ingrese el ID de la Carrera que deseas consultar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer de entrada
        Carrera carrera = Leer(id);  // Llama al método Leer de la clase base

        System.out.println("Consultando en la base de datos..........");
        System.out.println("+----+---------+");
        System.out.println("| ID | Carrera |");
        System.out.println("+----+---------+");
        if (carrera != null) {
            // Si la carrera existe, muestra sus detalles
            System.out.printf("| %-2d | %-7s |\n", carrera.getId(), carrera.getNombre());
        } else {
            // Si la carrera no existe, muestra un mensaje de error
            System.out.println("| No existe la carrera.         |");
        }
        System.out.println("+----+---------+");
    }

    // Método para actualizar una carrera existente
    public void actualizar() {
        Consulta();  // Llama al método Consulta para mostrar todas las carreras disponibles
        System.out.print("Ingrese el ID de la Carrera que deseas actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer de entrada

        Carrera carrera = Leer(id);  // Llama al método Leer de la clase base
        if (carrera == null) {
            // Si la carrera no existe, muestra un mensaje de error y termina el método
            System.out.println("La carrera con el ID especificado no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo ID de la Carrera (dejar en blanco para no cambiar): ");
        String nuevoIdStr = scanner.nextLine();

        System.out.print("Ingrese nuevo nombre de la Carrera (dejar en blanco para no cambiar): ");
        String nuevoNombre = scanner.nextLine();

        int nuevoId = id;  // Mantiene el ID actual si no se ingresa un nuevo ID
        if (!nuevoIdStr.isEmpty()) {
            try {
                nuevoId = Integer.parseInt(nuevoIdStr);  // Intenta convertir el nuevo ID ingresado a un entero
            } catch (NumberFormatException e) {
                // Si el ID no es válido, muestra un mensaje de error y mantiene el ID actual
                System.out.println("ID no valido, se mantendra el ID actual.");
                nuevoId = id;
            }
        }

        if (nuevoNombre.isEmpty()) {
            // Si no se ingresa un nuevo nombre, mantiene el nombre actual
            nuevoNombre = carrera.getNombre();
        }

        Actualizar(id, nuevoId, nuevoNombre);  // Llama al método Actualizar de la clase base
        System.out.println("Actualizando...................");
        System.out.println("Carrera actualizada exitosamente.");
    }

    // Método para eliminar una carrera existente
    public void eliminar() {
        Consulta();  // Llama al método Consulta para mostrar todas las carreras disponibles
        System.out.print("Ingrese el ID de la Carrera a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer de entrada
        Eliminar(id);  // Llama al método Eliminar de la clase base
        System.out.println("Eliminando..................");
        System.out.println("Carrera eliminada exitosamente.");
    }

    // Clase interna Main que contiene el método principal
    public static class Main {

        public static void main(String[] args) {
            GestorCarreras gestor = new GestorCarreras();
            gestor.menu();  // Inicia el menú de gestión de carreras
        }
    }
}

package f21;

import java.util.Scanner;

// Clase GestorCuatrimestres extiende de la clase ModeloCuatri para manejar operaciones CRUD sobre cuatrimestres
public class GestorCuatrimestres extends ModeloCuatri {

    // Instancia de Scanner para leer la entrada del usuario
    Scanner scanner = new Scanner(System.in);

    // Método para mostrar el menú y manejar las selecciones del usuario
    public void menu() {
        int opcion;
        do {
            System.out.println("Bienvenido al menu de cuatrimestres");
            System.out.println("\n---Gestor---de---Cuatrimestres---");
            System.out.println("|1|. Listar ");
            System.out.println("|2|. Crear ");
            System.out.println("|3|. Leer ");
            System.out.println("|4|. Actualizar ");
            System.out.println("|5|. Eliminar ");
            System.out.println("|0|. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt(); // Leer la opción del usuario
            scanner.nextLine(); // Limpiar el buffer del Scanner

            // Manejar la opción seleccionada
            switch (opcion) {
                case 1:
                    listar(); // Listar todos los cuatrimestres
                    break;
                case 2:
                    crear(); // Crear un nuevo cuatrimestre
                    break;
                case 3:
                    leer(); // Leer un cuatrimestre por su ID
                    break;
                case 4:
                    actualizar(); // Actualizar un cuatrimestre existente
                    break;
                case 5:
                    eliminar(); // Eliminar un cuatrimestre
                    break;
                case 0:
                    System.out.println("Saliendo..."); // Mensaje al salir
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo."); // Mensaje para opción no válida
            }
        } while (opcion != 0); // Continuar hasta que el usuario elija salir
    }

    // Método para listar todos los cuatrimestres
    public void listar() {
        Consultar();
    }

    // Método para crear un nuevo cuatrimestre
    public void crear() {
        System.out.print("Ingrese el nombre del Cuatrimestre: ");
        String nombre = scanner.nextLine().trim(); // Leer y recortar el nombre del cuatrimestre
        if (nombre.isEmpty()) {
            // Verificar que el nombre no esté vacío
            System.out.println("El nombre no puede estar vacio.");
            return; // Salir del método si el nombre está vacío
        }
        Crear(nombre); // Llamar al método de la clase ModeloCuatri para crear el cuatrimestre
        System.out.println("Creando.......................");
        System.out.println("Cuatrimestre creado con éxito.");
    }

    // Método para leer un cuatrimestre por su ID
    public void leer() {
        Consultar();
        System.out.print("ID del Cuatrimestre a leer: ");
        int id = scanner.nextInt(); // Leer el ID del cuatrimestre
        scanner.nextLine(); // Limpiar el buffer del Scanner
        Cuatrimestre cuatrimestre = Leer(id); // Llamar al método de la clase ModeloCuatri para leer el cuatrimestre
        if (cuatrimestre != null) {
            // Imprimir el cuatrimestre en formato de tabla
            System.out.println("Consultando en la base de datos.......................");
            System.out.println("+----+--------------+");
            System.out.println("| ID | Cuatrimestre |");
            System.out.println("+----+--------------+");
            System.out.printf("| %-2d | %-12s |\n", cuatrimestre.getId(), cuatrimestre.getNombre());
            System.out.println("+----+--------------+");
        } else {
            System.out.println("Cuatrimestre no encontrado."); // Mensaje si el cuatrimestre no existe
        }
    }

    // Método para actualizar un cuatrimestre existente
    public void actualizar() {
        Consultar();
        System.out.print("Cual es el ID del Cuatrimestre que desea actualizar: ");
        int idActual = scanner.nextInt(); // Leer el ID actual del cuatrimestre
        scanner.nextLine(); // Limpiar el buffer del Scanner

        // Leer el cuatrimestre actual
        Cuatrimestre cuatrimestreActual = Leer(idActual);
        if (cuatrimestreActual == null) {
            System.out.println("Cuatrimestre no encontrado."); // Mensaje si el cuatrimestre no existe
            return;
        }

        // Solicitar nuevo ID
        System.out.print("Ingrese el nuevo ID del Cuatrimestre (dejar en blanco para no cambiar): ");
        String idNuevoStr = scanner.nextLine().trim();
        int idNuevo = idActual; // Por defecto, mantener el ID actual si no se ingresa uno nuevo

        if (!idNuevoStr.isEmpty()) {
            try {
                idNuevo = Integer.parseInt(idNuevoStr); // Convertir el nuevo ID a entero
                if (idNuevo <= 0) {
                    System.out.println("ID debe ser un numero positivo."); // Verificar que el ID sea positivo
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("ID invalido."); // Mensaje si el ID no es válido
                return;
            }
        }

        // Solicitar nuevo nombre
        System.out.print("Ingrese el nuevo nombre del Cuatrimestre (dejar en blanco para no cambiar): ");
        String nombreNuevo = scanner.nextLine().trim();
        if (nombreNuevo.isEmpty()) {
            nombreNuevo = cuatrimestreActual.getNombre(); // Mantener el nombre actual si no se ingresa uno nuevo
        }

        // Actualizar en la base de datos
        Actualizar(idActual, idNuevo, nombreNuevo);
        System.out.println("Actualizando.......................");
        System.out.println("Cuatrimestre actualizado con éxito.");
    }

    // Método para eliminar un cuatrimestre
    public void eliminar() {
        Consultar();
        System.out.print("Ingrese el ID del Cuatrimestre a eliminar: ");
        int id = scanner.nextInt(); // Leer el ID del cuatrimestre
        scanner.nextLine(); // Limpiar el buffer del Scanner
        Eliminar(id); // Llamar al método de la clase ModeloCuatri para eliminar el cuatrimestre
        System.out.println("Eliminando.......................");
        System.out.println("Cuatrimestre eliminado con éxito.");
    }

    // Clase principal para ejecutar el menú
    public static class Main {

        public static void main(String[] args) {
            GestorCuatrimestres gestor = new GestorCuatrimestres(); // Crear una instancia de GestorCuatrimestres
            gestor.menu(); // Mostrar el menú y gestionar las opciones del usuario
        }
    }
}

package f21;

import java.util.Scanner;

// Extiende ModeloCC para heredar métodos CRUD para Ciclo_Cuatri
public class GestorCC extends ModeloCC {

    // Instancia de Scanner para leer la entrada del usuario
    Scanner scanner = new Scanner(System.in);

    // Instancias de GestorCiclos y GestorCuatrimestres
    GestorCiclos gestorCiclos = new GestorCiclos();
    GestorCuatrimestres gestorCuatrimestres = new GestorCuatrimestres();

    // Método principal que muestra el menú y gestiona las opciones del usuario
    public void menu() {
        int opcion;
        do {
            // Mostrar el menú de opciones
            System.out.println("Bienvenido al menu de Ciclo_Cuatrimestre");
            System.out.println("\n--- Gestor--de--Ciclo_Cuatri---");
            System.out.println("|1|. Listar ");
            System.out.println("|2|. Crear ");
            System.out.println("|3|. Leer ");
            System.out.println("|4|. Actualizar ");
            System.out.println("|5|. Eliminar ");
            System.out.println("|0|. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt(); // Leer la opción del usuario
            scanner.nextLine(); // Limpiar el buffer
            // Manejar la opción seleccionada por el usuario
            switch (opcion) {
                case 1:
                    listar(); // Listar todos los registros
                    break;
                case 2:
                    crear(); // Crear un nuevo registro
                    break;
                case 3:
                    leer(); // Leer un registro por ID
                    break;
                case 4:
                    actualizar(); // Actualizar un registro existente
                    break;
                case 5:
                    eliminar(); // Eliminar un registro por ID
                    break;
                case 0:
                    System.out.println("Saliendo..."); // Salir del programa
                    break;
                default:
                    System.out.println("Opcion invalida"); // Manejar opciones inválidas
                    break;
            }
        } while (opcion != 0); // Repetir hasta que el usuario elija salir
    }

    // Método para listar todos los registros de Ciclo_Cuatri
    public void listar() {
        listarCiclo_Cuatri(); // Llamar al método para listar
        System.out.println("+----+--------+-------------+");
        System.out.println("|     CICLO_CUATRIMESTRE    |");
        System.out.println("+----+--------+-------------+");
        System.out.println("| ID | Ciclo  | Cuatrimestre|");
        System.out.println("+----+--------+-------------+");
        // Iterar sobre todos los registros y mostrar sus detalles
        for (CC cc : getCarteraCiclo_Cuatri()) {
            System.out.printf("| %-2d | %-6s | %-11s |\n", cc.getId(), cc.getCiclo().getNombre(),
                    cc.getCuatrimestre().getNombre());
        }
        System.out.println("+----+--------+-------------+");
    }

    // Método para crear un nuevo registro de Ciclo_Cuatri
    public void crear() {
        gestorCiclos.listar(); // Llamar al método para listar ciclos
        System.out.print("Ingrese el ID del ciclo que deseas asignar: ");
        int idCiclo = scanner.nextInt(); // Leer el ID del Ciclo

        gestorCuatrimestres.listar(); // Llamar al método para listar cuatrimestres
        System.out.print("Ingrese el ID del cuatrimestre que deseas asignar: ");
        int idCuatrimestre = scanner.nextInt(); // Leer el ID del Cuatrimestre

        Crear(idCiclo, idCuatrimestre); // Llamar al método para crear un nuevo registro
        System.out.println("Creando...........");
        System.out.println("Ciclo_Cuatri creado exitosamente.");
    }

    // Método para leer un registro de Ciclo_Cuatri por su ID
    public void leer() {
        listar(); // Actualizar la lista antes de leer
        System.out.print("Ingrese el ID de Ciclo_Cuatri que deseas consultar: ");
        int id = scanner.nextInt(); // Leer el ID del registro
        scanner.nextLine(); // Limpiar el buffer
        CC cc = Leer(id); // Obtener el registro

        System.out.println("Consultando en la base de datos ...........");
        System.out.println("+----+--------+-------------+");
        System.out.println("| ID | Ciclo  | Cuatrimestre|");
        System.out.println("+----+--------+-------------+");

        // Mostrar los detalles del registro si existe
        if (cc != null) {
            System.out.printf("| %-2d | %-6s | %-11s |\n", cc.getId(), cc.getCiclo().getNombre(),
                    cc.getCuatrimestre().getNombre());
        } else {
            System.out.println("| N/A | N/A    | N/A         |"); // Mostrar un mensaje cuando el registro no se encuentra
        }

        System.out.println("+----+--------+-------------+");
    }

    // Método para actualizar un registro de Ciclo_Cuatri
    public void actualizar() {
        listar(); // Llamar al método para listar
        System.out.print("Ingrese el Id de ciclo_cuatri que deseas actualizar: ");
        int idActual = scanner.nextInt(); // Leer el ID actual del registro
        scanner.nextLine(); // Limpiar el buffer

        CC cc = Leer(idActual); // Obtener el registro existente
        if (cc != null) {
            // Leer nuevos valores, si se proporcionan
            System.out.print("Ingrese el nuevo ID de ciclo_cuatri (dejar en blanco para no realizar cambios): ");
            String nuevaIdStr = scanner.nextLine(); // Leer nuevo ID, si se proporciona
            int nuevoId = idActual; // Inicializar con el ID actual

            if (!nuevaIdStr.isEmpty()) {
                nuevoId = Integer.parseInt(nuevaIdStr); // Convertir el nuevo ID a entero
            }

            gestorCiclos.listar(); // Llamar al método para listar ciclos
            System.out.print("Ingrese el nuevo ID del Ciclo (dejar en blanco para no realizar cambios): ");
            String nuevoCicloIdStr = scanner.nextLine(); // Leer nuevo ID del Ciclo, si se proporciona
            int nuevoCicloId = cc.getCiclo().getId(); // Usar el ID actual si no se proporciona uno nuevo

            if (!nuevoCicloIdStr.isEmpty()) {
                nuevoCicloId = Integer.parseInt(nuevoCicloIdStr); // Convertir el nuevo ID a entero
            }

            gestorCuatrimestres.listar(); // Llamar al método para listar cuatrimestres
            System.out.print("Ingrese el nuevo ID del Cuatrimestre (dejar en blanco para no realizar cambios): ");
            String nuevoCuatriIdStr = scanner.nextLine(); // Leer nuevo ID del Cuatrimestre, si se proporciona
            int nuevoCuatriId = cc.getCuatrimestre().getId(); // Usar el ID actual si no se proporciona uno nuevo

            if (!nuevoCuatriIdStr.isEmpty()) {
                nuevoCuatriId = Integer.parseInt(nuevoCuatriIdStr); // Convertir el nuevo ID a entero
            }

            // Llamar al método de la clase padre para actualizar el registro
            Actualizar(idActual, nuevoId, nuevoCicloId, nuevoCuatriId);
            System.out.println("Actualizando...........");
            System.out.println("Ciclo_Cuatri actualizado exitosamente.");
        } else {
            System.out.println("Ciclo_Cuatri no encontrado."); // Mensaje si el registro no se encuentra
        }
    }

    // Método para eliminar un registro de Ciclo_Cuatri por su ID
    public void eliminar() {
        listar(); // Actualizar la lista antes de eliminar
        System.out.print("Ingrese el ID de Ciclo_Cuatri que desea eliminar: ");
        int id = scanner.nextInt(); // Leer el ID del registro a eliminar
        Eliminar(id); // Llamar al método para eliminar el registro
        System.out.println("Eliminando...........");
        System.out.println("Ciclo_Cuatri eliminado exitosamente.");
    }

    // Clase principal para ejecutar el programa
    public static class Main {

        public static void main(String[] args) {
            GestorCC gestor = new GestorCC(); // Crear una instancia de GestorCC
            gestor.menu(); // Mostrar el menú y gestionar las opciones del usuario
        }
    }
}

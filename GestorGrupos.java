package f21;

import java.util.Scanner;

// Clase GestorGrupos que extiende la clase ModeloGrupo
public class GestorGrupos extends ModeloGrupo {

    Scanner scanner = new Scanner(System.in);

    // Instancias de GestorCiclos y GestorCarreras para gestionar ciclos y carreras
    GestorCC gestorCC = new GestorCC();
    GestorCarreras gestorCarreras = new GestorCarreras();

    // Método para mostrar el menú y gestionar las opciones seleccionadas
    public void menu() {
        int opcion;
        do {
            System.out.println("Bienvenido al menu de Grupos");
            System.out.println("\n---Gestor--de--Grupos---");
            System.out.println("|1|. Listar");
            System.out.println("|2|. Crear");
            System.out.println("|3|. Leer");
            System.out.println("|4|. Actualizar");
            System.out.println("|5|. Eliminar");
            System.out.println("|0|. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
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
                    break;
            }
        } while (opcion != 0);
    }

    // Método para listar los grupos en formato de tabla
    public void listar() {
        listarGrupos();
        System.out.println("+----+--------+--------+--------------+---------+");
        System.out.println("|                    GRUPOS                     |");
        System.out.println("+----+--------+--------+--------------+---------+");
        System.out.println("| ID | Nombre | Ciclo  | Cuatrimestre | Carrera |");
        System.out.println("+----+--------+--------+--------------+---------+");
        for (Grupo grupo : getCarteraGrupos()) {
            System.out.printf("| %-2d | %-6s | %-6s | %-12s | %-7s |\n",
                    grupo.getId(),
                    grupo.getNombre(),
                    grupo.getCicloCuatri().getCiclo().getNombre(),
                    grupo.getCicloCuatri().getCuatrimestre().getNombre(),
                    grupo.getCarrera().getNombre());
        }
        System.out.println("+----+--------+--------+--------------+---------+");
    }

    // Método para crear un nuevo grupo
    public void crear() {
        System.out.print("Ingrese el nombre del grupo: ");
        String nombre = scanner.nextLine();

        gestorCC.listar();  // Listar ciclos y cuatrimestres disponibles
        System.out.print("Ingrese el ID del ciclo_cuatri: ");
        int idCicloCuatri = scanner.nextInt();

        gestorCarreras.listar();  // Listar carreras disponibles
        System.out.print("Ingrese el ID de la carrera: ");
        int idCarrera = scanner.nextInt();

        Crear(nombre, idCicloCuatri, idCarrera);  // Llamar al método Crear heredado de ModeloGrupo
        System.out.println("Grupo creado exitosamente.");
    }

    // Método para leer y mostrar un grupo específico por su ID
    public void leer() {
        listar();  // Listar todos los grupos
        System.out.print("Ingrese el ID del grupo que desea consultar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Grupo grupo = Leer(id);  // Llamar al método Leer heredado de ModeloGrupo

        System.out.println("Consultando en la base de datos ...........");
        System.out.println("+----+--------+--------+--------------+---------+");
        System.out.println("| ID | Nombre | Ciclo  | Cuatrimestre | Carrera |");
        System.out.println("+----+--------+--------+--------------+---------+");

        if (grupo != null) {
            System.out.printf("| %-2d | %-6s | %-6s | %-12s | %-7s |\n",
                    grupo.getId(),
                    grupo.getNombre(),
                    grupo.getCicloCuatri().getCiclo().getNombre(),
                    grupo.getCicloCuatri().getCuatrimestre().getNombre(),
                    grupo.getCarrera().getNombre());
        } else {
            System.out.println("| N/A | N/A    | N/A    | N/A          | N/A     |");
        }

        System.out.println("+----+--------+--------+--------------+---------+");
    }

    // Método para actualizar un grupo existente
    public void actualizar() {
        listar();  // Listar todos los grupos
        System.out.print("Ingrese el ID del grupo que deseas actualizar: ");
        int idActual = scanner.nextInt();
        scanner.nextLine();

        Grupo grupo = Leer(idActual);  // Llamar al método Leer heredado de ModeloGrupo
        if (grupo != null) {
            System.out.print("Ingrese el nuevo ID del grupo (dejar en blanco para no realizar cambios): ");
            String idNuevoStr = scanner.nextLine();
            int idNuevo = idActual;  // Mantener el ID actual si no se proporciona un nuevo ID

            if (!idNuevoStr.isEmpty()) {
                try {
                    idNuevo = Integer.parseInt(idNuevoStr);
                } catch (NumberFormatException e) {
                    System.out.println("ID no valido. Manteniendo el ID actual.");
                }
            }

            System.out.print("Ingrese el nuevo nombre del grupo (dejar en blanco para no realizar cambios): ");
            String nuevoNombre = scanner.nextLine();
            if (nuevoNombre.isEmpty()) {
                nuevoNombre = grupo.getNombre();
            }

            gestorCC.listar();  // Listar ciclos y cuatrimestres disponibles
            System.out.print("Ingrese el nuevo ID de ciclo_cuatri (dejar en blanco para no realizar cambios): ");
            String nuevoCicloCuatriIdStr = scanner.nextLine();
            int nuevoCicloCuatriId = grupo.getCicloCuatri().getId();
            if (!nuevoCicloCuatriIdStr.isEmpty()) {
                try {
                    nuevoCicloCuatriId = Integer.parseInt(nuevoCicloCuatriIdStr);
                } catch (NumberFormatException e) {
                    System.out.println("ID del ciclo_cuatri no valido. Manteniendo el ID actual.");
                }
            }

            gestorCarreras.listar();  // Listar carreras disponibles
            System.out.print("Ingrese el nuevo ID de la carrera (dejar en blanco para no realizar cambios): ");
            String nuevoCarreraIdStr = scanner.nextLine();
            int nuevoCarreraId = grupo.getCarrera().getId();
            if (!nuevoCarreraIdStr.isEmpty()) {
                try {
                    nuevoCarreraId = Integer.parseInt(nuevoCarreraIdStr);
                } catch (NumberFormatException e) {
                    System.out.println("ID de la carrera no valido. Manteniendo el ID actual.");
                }
            }

            Actualizar(idActual, idNuevo, nuevoNombre, nuevoCicloCuatriId, nuevoCarreraId);  // Llamar al método Actualizar heredado de ModeloGrupo
            System.out.println("Grupo actualizado exitosamente.");
        } else {
            System.out.println("Grupo no encontrado.");
        }
    }

    // Método para eliminar un grupo por su ID
    public void eliminar() {
        listar();  // Listar todos los grupos
        System.out.print("Ingrese el ID del grupo que desea eliminar: ");
        int id = scanner.nextInt();
        Eliminar(id);  // Llamar al método Eliminar heredado de ModeloGrupo
        System.out.println("Grupo eliminado exitosamente.");
    }

    // Clase principal con método main para ejecutar el programa
    public static class Main {

        public static void main(String[] args) {
            GestorGrupos gestor = new GestorGrupos();
            gestor.menu();  // Mostrar el menú de gestión de grupos
        }
    }
}

package f21;

import java.util.Scanner;

// Clase que gestiona las operaciones relacionadas con las asignaciones.
public class GestorAsignaciones extends ModeloAsignacion {

    Scanner scanner = new Scanner(System.in);  // Instancia de Scanner para entrada de datos.

    GestorGrupos gestorGrupos = new GestorGrupos();  // Instancia del gestor de grupos.
    GestorProfesores gestorProfesores = new GestorProfesores();  // Instancia del gestor de profesores.
    GestorMaterias gestorMaterias = new GestorMaterias();  // Instancia del gestor de materias.

    // Método que muestra el menú y maneja la opción seleccionada por el usuario.
    public void menu() {
        int opcion;
        do {
            // Muestra el menú de opciones.
            System.out.println("Bienvenido al menu de Asignaciones");
            System.out.println("\n---Gestor---de---Asignaciones ---");
            System.out.println("|1|. Listar ");
            System.out.println("|2|. Crear ");
            System.out.println("|3|. Leer ");
            System.out.println("|4|. Actualizar ");
            System.out.println("|5|. Eliminar ");
            System.out.println("|0|. Salir ");
            System.out.print("Ingrese una opcion: ");
            opcion = scanner.nextInt();  // Lee la opción seleccionada por el usuario.
            scanner.nextLine(); // Limpiar el buffer

            // Ejecuta la acción correspondiente según la opción seleccionada.
            switch (opcion) {
                case 1:
                    listar();  // Llama al método listar.
                    break;
                case 2:
                    crear();  // Llama al método crear.
                    break;
                case 3:
                    leer();  // Llama al método leer.
                    break;
                case 4:
                    actualizar();  // Llama al método actualizar.
                    break;
                case 5:
                    eliminar();  // Llama al método eliminar.
                    break;
                case 0:
                    System.out.println("Saliendo...");  // Mensaje al salir del menú.
                    break;
                default:
                    System.out.println("Opcion invalida");  // Mensaje para opción inválida.
                    break;
            }
        } while (opcion != 0);  // Repite el menú hasta que el usuario seleccione salir.
    }

    // Método para listar todas las asignaciones.
    public void listar() {
        listarAsignaciones();  // Llama al método de la clase base para obtener la lista de asignaciones.
        // Imprime el encabezado de la tabla de asignaciones.
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
        System.out.println("|                                                                      ASIGNACIONES                                                                         |");
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
        System.out.println("| ID | Grupo  |   Carrera   |    Ciclo   | Cuatrimestre  |        Profesor      |   Materia   |                     Descripcion                    | Unidad |");
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
        // Itera sobre la lista de asignaciones y muestra cada una en formato tabla.
        for (Asignacion asignacion : getAsignaciones()) {
            System.out.printf("| %-2d | %-6s | %-11s | %-10s | %-13s | %-20s | %-11s | %-50s | %-6s |\n",
                    asignacion.getId(),
                    asignacion.getGrupo().getNombre(),
                    asignacion.getCarrera().getNombre(),
                    asignacion.getCiclo().getNombre(),
                    asignacion.getCuatrimestre().getNombre(),
                    asignacion.getProfesor().getNombre(),
                    asignacion.getMateria().getNombre(),
                    asignacion.getMateria().getDescripcion(),
                    asignacion.getMateria().getUnidad());
        }
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
    }

    // Método para crear una nueva asignación.
    public void crear() {
        gestorGrupos.listar();  // Muestra la lista de grupos.
        System.out.print("Ingrese el ID del Grupo: ");
        int idGrupo = scanner.nextInt();  // Lee el ID del grupo.

        gestorProfesores.listar();  // Muestra la lista de profesores.
        System.out.print("Ingrese el ID del Profesor: ");
        int idProfesor = scanner.nextInt();  // Lee el ID del profesor.

        gestorMaterias.listar();  // Muestra la lista de materias.
        System.out.print("Ingrese el ID de la Materia: ");
        int idMateria = scanner.nextInt();  // Lee el ID de la materia.

        Crear(idGrupo, idProfesor, idMateria);  // Llama al método Crear de la clase base.
        System.out.println("Creando....................");
        System.out.println("Asignacion creada exitosamente.");  // Mensaje de éxito.
    }

    // Método para leer una asignación específica.
    public void leer() {
        listar();  // Muestra la lista de asignaciones.
        System.out.print("Ingrese el ID de la Asignacion que deseas consultar: ");
        int id = scanner.nextInt();  // Lee el ID de la asignación.
        scanner.nextLine(); // Limpiar el buffer
        Asignacion asignacion = Leer(id);  // Llama al método Leer de la clase base.

        // Imprime el encabezado de la tabla de asignaciones.
        System.out.println("Consultando en la base de datos...................");
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
        System.out.println("| ID | Grupo  |   Carrera   |    Ciclo   | Cuatrimestre  |        Profesor      |   Materia   |                     Descripcion                    | Unidad |");
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
        // Muestra los detalles de la asignación consultada.
        if (asignacion != null) {
            System.out.printf("| %-2d | %-6s | %-11s | %-10s | %-13s | %-20s | %-11s | %-50s | %-6s |\n",
                    asignacion.getId(),
                    asignacion.getGrupo().getNombre(),
                    asignacion.getCarrera().getNombre(),
                    asignacion.getCiclo().getNombre(),
                    asignacion.getCuatrimestre().getNombre(),
                    asignacion.getProfesor().getNombre(),
                    asignacion.getMateria().getNombre(),
                    asignacion.getMateria().getDescripcion(),
                    asignacion.getMateria().getUnidad());
        } else {
            System.out.println("| N/A | N/A    | N/A         | N/A        | N/A           | N/A       | N/A         | N/A         | N/A    |");  // Mensaje si la asignación no se encuentra.
        }
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
    }

    // Método para actualizar una asignación existente.
    public void actualizar() {
        listar();  // Muestra la lista de asignaciones.
        System.out.print("Ingrese el ID de la asignacion que deseas actualizar: ");
        int idActual = scanner.nextInt();  // Lee el ID de la asignación a actualizar.
        scanner.nextLine(); // Limpiar el buffer

        Asignacion asignacion = Leer(idActual);  // Llama al método Leer de la clase base.
        if (asignacion != null) {  // Verifica si la asignación existe.
            // Solicita los nuevos datos, permitiendo dejar campos en blanco para no realizar cambios.
            Integer idNuevo = idActual;  // Mantiene el ID actual por defecto.

            System.out.print("Ingrese el nuevo ID de la asignacion (dejar en blanco para no realizar cambios): ");
            String idNuevoStr = scanner.nextLine();
            if (!idNuevoStr.trim().isEmpty()) {
                try {
                    idNuevo = Integer.parseInt(idNuevoStr);  // Intenta convertir el nuevo ID.
                } catch (NumberFormatException e) {
                    System.out.println("ID no valido. Manteniendo el ID actual.");  // Mensaje si el nuevo ID no es válido.
                }
            }

            // Solicita los nuevos IDs para grupo, profesor y materia.
            gestorGrupos.listar();
            Integer nuevoGrupoId = asignacion.getGrupo().getId();
            System.out.print("Ingrese el nuevo ID del Grupo (dejar en blanco para no realizar cambios): ");
            String nuevoGrupoIdStr = scanner.nextLine();
            if (!nuevoGrupoIdStr.trim().isEmpty()) {
                try {
                    nuevoGrupoId = Integer.parseInt(nuevoGrupoIdStr);  // Intenta convertir el nuevo ID de grupo.
                } catch (NumberFormatException e) {
                    System.out.println("ID del Grupo no valido. Manteniendo el ID actual.");  // Mensaje si el nuevo ID de grupo no es válido.
                }
            }

            gestorProfesores.listar();
            Integer nuevoProfesorId = asignacion.getProfesor().getId();
            System.out.print("Ingrese el nuevo ID del Profesor (dejar en blanco para no realizar cambios): ");
            String nuevoProfesorIdStr = scanner.nextLine();
            if (!nuevoProfesorIdStr.trim().isEmpty()) {
                try {
                    nuevoProfesorId = Integer.parseInt(nuevoProfesorIdStr);  // Intenta convertir el nuevo ID de profesor.
                } catch (NumberFormatException e) {
                    System.out.println("ID del Profesor no valido. Manteniendo el ID actual.");  // Mensaje si el nuevo ID de profesor no es válido.
                }
            }

            gestorMaterias.listar();
            Integer nuevoMateriaId = asignacion.getMateria().getId();
            System.out.print("Ingrese el nuevo ID de la Materia (dejar en blanco para no realizar cambios): ");
            String nuevoMateriaIdStr = scanner.nextLine();
            if (!nuevoMateriaIdStr.trim().isEmpty()) {
                try {
                    nuevoMateriaId = Integer.parseInt(nuevoMateriaIdStr);  // Intenta convertir el nuevo ID de materia.
                } catch (NumberFormatException e) {
                    System.out.println("ID de la Materia no valido. Manteniendo el ID actual.");  // Mensaje si el nuevo ID de materia no es válido.
                }
            }

            Actualizar(idActual, idNuevo, nuevoGrupoId, nuevoProfesorId, nuevoMateriaId);  // Llama al método Actualizar de la clase base.
            System.out.println("Actualizando..................");
            System.out.println("Asignacion actualizada exitosamente.");  // Mensaje de éxito.
        } else {
            System.out.println("Asignacion no encontrada.");  // Mensaje si la asignación no se encuentra.
        }
    }

    // Método para eliminar una asignación.
    public void eliminar() {
        listar();  // Muestra la lista de asignaciones.
        System.out.print("Ingrese el ID de la Asignacion que desea eliminar: ");
        int id = scanner.nextInt();  // Lee el ID de la asignación a eliminar.
        Eliminar(id);  // Llama al método Eliminar de la clase base.
        System.out.println("Eliminando...............");
        System.out.println("Asignacion eliminada exitosamente.");  // Mensaje de éxito.
    }

    // Clase principal para ejecutar el gestor de asignaciones.
    public static class Main {

        public static void main(String[] args) {
            GestorAsignaciones gestor = new GestorAsignaciones();  // Crea una instancia del gestor de asignaciones.
            gestor.menu();  // Llama al método menu para iniciar la aplicación.
        }
    }
}

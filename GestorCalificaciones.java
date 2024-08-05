package f21;

import java.util.Scanner;

public class GestorCalificaciones extends ModeloCalificacion {

    private Scanner scanner = new Scanner(System.in);  // Objeto Scanner para la entrada de datos por consola
    GestorAlumnos gestorAlumnos = new GestorAlumnos();  // Objeto para gestionar operaciones relacionadas con los alumnos
    GestorAsignaciones gestorAsignaciones = new GestorAsignaciones();  // Objeto para gestionar operaciones relacionadas con las asignaciones

    // Método para mostrar el menú y gestionar las opciones seleccionadas
    public void menu() {
        int opcion;
        do {
            System.out.println("Bienvenido al menu de Calificaciones");  // Mensaje de bienvenida
            System.out.println("\n---Gestor---de---Calificaciones---");
            System.out.println("|1|. Listar");  // Opción para listar calificaciones
            System.out.println("|2|. Crear");  // Opción para crear una nueva calificación
            System.out.println("|3|. Leer");  // Opción para leer una calificación específica
            System.out.println("|4|. Actualizar");  // Opción para actualizar una calificación existente
            System.out.println("|5|. Eliminar");  // Opción para eliminar una calificación
            System.out.println("|0|. Salir");  // Opción para salir del menú
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();  // Leer la opción del usuario
            scanner.nextLine();  // Limpiar el buffer
            switch (opcion) {
                case 1:
                    listar();  // Llamar al método listar
                    break;
                case 2:
                    crear();  // Llamar al método crear
                    break;
                case 3:
                    leer();  // Llamar al método leer
                    break;
                case 4:
                    actualizar();  // Llamar al método actualizar
                    break;
                case 5:
                    eliminar();  // Llamar al método eliminar
                    break;
                case 0:
                    System.out.println("Saliendo...");  // Mensaje al salir del menú
                    break;
                default:
                    System.out.println("Opcion no valida.");  // Mensaje para opción no válida
            }
        } while (opcion != 0);  // Repetir el menú hasta que el usuario elija salir
    }

    // Método para listar todas las calificaciones en formato tabla
    private void listar() {
        listarCalificaciones();  // Llamar al método para listar calificaciones desde la base de datos
        System.out.println("+----+---------------+----------+-------+-------+--------------+---------+------------+----------------------+--------+--------------+");
        System.out.println("|                                                        CALIFICACIONES                                                              |");
        System.out.println("+----+---------------+----------+-------+-------+--------------+---------+------------+----------------------+--------+--------------+");
        System.out.println("| id |    Alumno     | Estado   | Grupo | Ciclo | Cuatrimestre | Carrera |   Materia  |       Profesor       | Unidad | Calificacion |");
        System.out.println("+----+---------------+----------+-------+-------+--------------+---------+------------+----------------------+--------+--------------+");

        // Imprimir cada calificación en formato de tabla
        for (Calificacion calificacion : getCarteraCalificaciones()) {
            System.out.printf("| %-2d | %-13s | %-8s | %-5s | %-5s | %-12s | %-7s | %-10s | %-20s | %-6s | %-12s |%n",
                    calificacion.getId(),
                    calificacion.getAlumno(),
                    calificacion.getEstado(),
                    calificacion.getGrupo(),
                    calificacion.getCiclo(),
                    calificacion.getCuatrimestre(),
                    calificacion.getCarrera(),
                    calificacion.getMateria(),
                    calificacion.getProfesor(),
                    calificacion.getUnidad(),
                    calificacion.getCalificacion());
        }

        // Línea final de la tabla
        System.out.println("+----+---------------+----------+-------+-------+--------------+---------+------------+----------------------+--------+--------------+");
    }

    // Método para crear una nueva calificación
    private void crear() {
        System.out.print("Ingrese la unidad que desea calificar: ");
        String unidad = scanner.nextLine();  // Leer unidad
        System.out.print("Ingrese la calificacion obtenida en esa unidad: ");
        String calificacion = scanner.nextLine();  // Leer calificación

        gestorAlumnos.listar();  // Listar alumnos disponibles
        System.out.print("Ingrese el ID del Alumno: ");
        int idAlumno = scanner.nextInt();  // Leer ID del alumno
        gestorAsignaciones.listar();  // Listar asignaciones disponibles
        System.out.print("Ingrese el ID de la Asignacion: ");
        int idAsigna = scanner.nextInt();  // Leer ID de la asignación
        scanner.nextLine();  // Limpiar el buffer
        Crear(unidad, calificacion, idAlumno, idAsigna);  // Llamar al método Crear heredado de ModeloCalificacion
        System.out.println("Creando............");  // Mensaje de creación en progreso
        System.out.println("Calificacion creada exitosamente.");  // Mensaje de éxito
    }

    // Método para leer una calificación específica en formato de tabla
    private void leer() {
        listar();  // Listar todas las calificaciones
        System.out.print("Ingrese el ID de la calificacion que desea consultar: ");
        int id = scanner.nextInt();  // Leer el ID de la calificación a consultar
        scanner.nextLine();  // Limpiar el buffer
        Calificacion calificacion = Leer(id);  // Llamar al método Leer heredado de ModeloCalificacion
        if (calificacion != null) {
            // Encabezado de la tabla
            System.out.println("Consultando en la base de datos..... ");
            System.out.println("+----+---------------+----------+-------+-------+--------------+---------+------------+----------------------+--------+--------------+");
            System.out.println("| id |    Alumno     | Estado   | Grupo | Ciclo | Cuatrimestre | Carrera |   Materia  |       Profesor       | Unidad | Calificacion |");
            System.out.println("+----+---------------+----------+-------+-------+--------------+---------+------------+----------------------+--------+--------------+");

            // Imprimir la calificación en formato de tabla
            System.out.printf("| %-2d | %-13s | %-8s | %-5s | %-5s | %-12s | %-7s | %-10s | %-20s | %-6s | %-12s |%n",
                    calificacion.getId(),
                    calificacion.getAlumno(),
                    calificacion.getEstado(),
                    calificacion.getGrupo(),
                    calificacion.getCiclo(),
                    calificacion.getCuatrimestre(),
                    calificacion.getCarrera(),
                    calificacion.getMateria(),
                    calificacion.getProfesor(),
                    calificacion.getUnidad(),
                    calificacion.getCalificacion());

            // Línea final de la tabla
            System.out.println("+----+---------------+----------+-------+-------+--------------+---------+------------+----------------------+--------+--------------+");
        } else {
            System.out.println("No se encontro la calificacion con el ID proporcionado.");  // Mensaje si no se encuentra la calificación
        }
    }

    // Método para actualizar una calificación existente
    public void actualizar() {
        listar();  // Listar todas las calificaciones
        System.out.print("Ingrese el ID de la calificacion que deseas actualizar: ");
        int idActual = scanner.nextInt();  // Leer el ID de la calificación a actualizar
        scanner.nextLine();  // Limpiar el buffer

        Calificacion calificacion = Leer(idActual);  // Llamar al método Leer heredado de ModeloCalificacion
        if (calificacion != null) {
            System.out.print("Ingrese el nuevo ID de la calificacion (dejar en blanco para no realizar cambios): ");
            String idNuevoStr = scanner.nextLine();  // Leer nuevo ID
            int idNuevo = idActual;  // Mantener el ID actual si no se proporciona un nuevo ID

            if (!idNuevoStr.isEmpty()) {
                try {
                    idNuevo = Integer.parseInt(idNuevoStr);  // Convertir a entero
                } catch (NumberFormatException e) {
                    System.out.println("ID no valido. Manteniendo el ID actual.");  // Mensaje si el ID no es válido
                }
            }

            System.out.print("Ingrese la nueva Unidad (dejar en blanco para no realizar cambios): ");
            String nuevaUnidad = scanner.nextLine();  // Leer nueva unidad
            if (nuevaUnidad.isEmpty()) {
                nuevaUnidad = calificacion.getUnidad();  // Mantener unidad actual si no se proporciona una nueva
            }

            System.out.print("Ingrese la nueva calificacion (dejar en blanco para no realizar cambios): ");
            String nuevaCalificacion = scanner.nextLine();  // Leer nueva calificación
            if (nuevaCalificacion.isEmpty()) {
                nuevaCalificacion = calificacion.getCalificacion();  // Mantener calificación actual si no se proporciona una nueva
            }

            gestorAlumnos.listar();  // Listar alumnos disponibles
            System.out.print("Ingrese el nuevo ID del alumno (dejar en blanco para no realizar cambios): ");
            String nuevoIdAlumnoStr = scanner.nextLine();  // Leer nuevo ID de alumno
            int nuevoIdAlumno = calificacion.getIdAlumno();  // Mantener ID de alumno actual si no se proporciona uno nuevo
            if (!nuevoIdAlumnoStr.isEmpty()) {
                try {
                    nuevoIdAlumno = Integer.parseInt(nuevoIdAlumnoStr);  // Convertir a entero
                } catch (NumberFormatException e) {
                    System.out.println("ID del alumno no valido. Manteniendo el ID actual.");  // Mensaje si el ID del alumno no es válido
                }
            }

            gestorAsignaciones.listar();  // Listar asignaciones disponibles
            System.out.print("Ingrese el nuevo ID de la asignacion (dejar en blanco para no realizar cambios): ");
            String nuevoIdAsignaStr = scanner.nextLine();  // Leer nuevo ID de asignación
            int nuevoIdAsigna = calificacion.getIdAsigna();  // Mantener ID de asignación actual si no se proporciona uno nuevo
            if (!nuevoIdAsignaStr.isEmpty()) {
                try {
                    nuevoIdAsigna = Integer.parseInt(nuevoIdAsignaStr);  // Convertir a entero
                } catch (NumberFormatException e) {
                    System.out.println("ID de la asignacion no valido. Manteniendo el ID actual.");  // Mensaje si el ID de la asignación no es válido
                }
            }

            Actualizar(idActual, idNuevo, nuevaUnidad, nuevaCalificacion, nuevoIdAlumno, nuevoIdAsigna);  // Llamar al método Actualizar heredado de ModeloCalificacion
            System.out.println("Actualizando...................");  // Mensaje de actualización en progreso
            System.out.println("Calificacion actualizada exitosamente.");  // Mensaje de éxito
        } else {
            System.out.println("Calificacion no encontrada.");  // Mensaje si no se encuentra la calificación
        }
    }

    // Método para eliminar una calificación por su ID
    private void eliminar() {
        listar();  // Listar todas las calificaciones
        System.out.print("Ingrese el ID de la calificacion que desea eliminar: ");
        int id = scanner.nextInt();  // Leer el ID de la calificación a eliminar
        scanner.nextLine();  // Limpiar el buffer
        Eliminar(id);  // Llamar al método Eliminar heredado de ModeloCalificacion
        System.out.println("Eliminando..........");  // Mensaje de eliminación en progreso
        System.out.println("Calificacion eliminada exitosamente.");  // Mensaje de éxito
    }

    // Clase principal para ejecutar el programa
    public static class Main {

        public static void main(String[] args) {
            GestorCalificaciones gestor = new GestorCalificaciones();  // Crear instancia de GestorCalificaciones
            gestor.menu();  // Mostrar el menú de gestión de calificaciones
        }
    }
}

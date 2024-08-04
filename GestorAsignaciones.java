package f21;

import java.util.Scanner;

public class GestorAsignaciones extends ModeloAsignacion {

    Scanner scanner = new Scanner(System.in);

    GestorGrupos gestorGrupos = new GestorGrupos();
    GestorProfesores gestorProfesores = new GestorProfesores();
    GestorMaterias gestorMaterias = new GestorMaterias();

    public void menu() {
        int opcion;
        do {
            System.out.println("Bienvenido al menu de Asignaciones");
            System.out.println("\n--- Gestor de Asignaciones ---");
            System.out.println("|1|. Listar ");
            System.out.println("|2|. Crear ");
            System.out.println("|3|. Leer ");
            System.out.println("|4|. Actualizar ");
            System.out.println("|5|. Eliminar ");
            System.out.println("|0|. Salir ");
            System.out.print("Ingrese una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

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

    public void listar() {
        listarAsignaciones();
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
        System.out.println("|                                                                      ASIGNACIONES                                                                         |");
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
        System.out.println("| ID | Grupo  |   Carrera   |    Ciclo   | Cuatrimestre  |        Profesor      |   Materia   |                     Descripcion                    | Unidad |");
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
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

    public void crear() {
        gestorGrupos.listar();
        System.out.print("Ingrese el ID del Grupo: ");
        int idGrupo = scanner.nextInt();

        gestorProfesores.listar();
        System.out.print("Ingrese el ID del Profesor: ");
        int idProfesor = scanner.nextInt();

        gestorMaterias.listar();
        System.out.print("Ingrese el ID de la Materia: ");
        int idMateria = scanner.nextInt();

        Crear(idGrupo, idProfesor, idMateria);
        System.out.println("Asignacion creada exitosamente.");
    }

    public void leer() {
        listar();
        System.out.print("Ingrese el ID de la Asignacion que deseas consultar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        Asignacion asignacion = Leer(id);

        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
        System.out.println("| ID | Grupo  |   Carrera   |    Ciclo   | Cuatrimestre  |        Profesor      |   Materia   |                     Descripcion                    | Unidad |");
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
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
            System.out.println("| N/A | N/A    | N/A         | N/A        | N/A           | N/A       | N/A         | N/A         | N/A    |");
        }
        System.out.println("+----+--------+-------------+------------+---------------+----------------------+-------------+----------------------------------------------------+--------+");
    }

    public void actualizar() {
        listar();
        System.out.print("Ingrese el ID de la asignacion que deseas actualizar: ");
        int idActual = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Asignacion asignacion = Leer(idActual);
        if (asignacion != null) {
            System.out.print("Ingrese el nuevo ID de la asignacion (dejar en blanco para no realizar cambios): ");
            String idNuevoStr = scanner.nextLine();
            Integer idNuevo = idActual;

            if (!idNuevoStr.trim().isEmpty()) {
                try {
                    idNuevo = Integer.parseInt(idNuevoStr);
                } catch (NumberFormatException e) {
                    System.out.println("ID no valido. Manteniendo el ID actual.");
                    idNuevo = idActual;
                }
            }

            gestorGrupos.listar();
            System.out.print("Ingrese el nuevo ID del Grupo (dejar en blanco para no realizar cambios): ");
            String nuevoGrupoIdStr = scanner.nextLine();
            Integer nuevoGrupoId = asignacion.getGrupo().getId();

            if (!nuevoGrupoIdStr.trim().isEmpty()) {
                try {
                    nuevoGrupoId = Integer.parseInt(nuevoGrupoIdStr);
                } catch (NumberFormatException e) {
                    System.out.println("ID del Grupo no valido. Manteniendo el ID actual.");
                    nuevoGrupoId = asignacion.getGrupo().getId();
                }
            }

            gestorProfesores.listar();
            System.out.print("Ingrese el nuevo ID del Profesor (dejar en blanco para no realizar cambios): ");
            String nuevoProfesorIdStr = scanner.nextLine();
            Integer nuevoProfesorId = asignacion.getProfesor().getId();

            if (!nuevoProfesorIdStr.trim().isEmpty()) {
                try {
                    nuevoProfesorId = Integer.parseInt(nuevoProfesorIdStr);
                } catch (NumberFormatException e) {
                    System.out.println("ID del Profesor no valido. Manteniendo el ID actual.");
                    nuevoProfesorId = asignacion.getProfesor().getId();
                }
            }

            gestorMaterias.listar();
            System.out.print("Ingrese el nuevo ID de la Materia (dejar en blanco para no realizar cambios): ");
            String nuevoMateriaIdStr = scanner.nextLine();
            Integer nuevoMateriaId = asignacion.getMateria().getId();

            if (!nuevoMateriaIdStr.trim().isEmpty()) {
                try {
                    nuevoMateriaId = Integer.parseInt(nuevoMateriaIdStr);
                } catch (NumberFormatException e) {
                    System.out.println("ID de la Materia no valido. Manteniendo el ID actual.");
                    nuevoMateriaId = asignacion.getMateria().getId();
                }
            }

            Actualizar(idActual, idNuevo, nuevoGrupoId, nuevoProfesorId, nuevoMateriaId);
        } else {
            System.out.println("Asignación no encontrada.");
        }
    }

    public void eliminar() {
        listar();
        System.out.print("Ingrese el ID de la Asignación que desea eliminar: ");
        int id = scanner.nextInt();
        Eliminar(id);
        System.out.println("Asignación eliminada exitosamente.");
    }

    public static class Main {

        public static void main(String[] args) {
            GestorAsignaciones gestor = new GestorAsignaciones();
            gestor.menu();
        }
    }
}

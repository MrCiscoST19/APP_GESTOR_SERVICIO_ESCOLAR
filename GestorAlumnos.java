package f21;  // Paquete que contiene la clase GestorAlumnos

import java.util.Scanner;  // Importación de la clase Scanner para la entrada de datos por consola

public class GestorAlumnos extends ModeloAlumno {  // Definición de la clase GestorAlumnos que extiende de ModeloAlumno

    Scanner scanner = new Scanner(System.in);  // Instancia de Scanner para leer la entrada del usuario

    GestorGrupos gestorGrupos = new GestorGrupos();  // Instancia de GestorGrupos para gestionar grupos
    GestorEstatus gestorEstatus = new GestorEstatus();  // Instancia de GestorEstatus para gestionar estados

    public void menu() {  // Método para mostrar y gestionar el menú principal
        int opcion;
        do {
            System.out.println("Bienvenido al menu de Alumnos");
            System.out.println("\n----Gestor---de---Alumnos---");
            System.out.println("|1|. Listar ");
            System.out.println("|2|. Crear ");
            System.out.println("|3|. Leer ");
            System.out.println("|4|. Actualizar ");
            System.out.println("|5|. Eliminar ");
            System.out.println("|0|. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();  // Lee la opción seleccionada por el usuario
            scanner.nextLine();  // Limpia el buffer del scanner
            switch (opcion) {
                case 1:
                    listar();  // Llama al método listar
                    break;
                case 2:
                    crear();  // Llama al método crear
                    break;
                case 3:
                    leer();  // Llama al método leer
                    break;
                case 4:
                    actualizar();  // Llama al método actualizar
                    break;
                case 5:
                    eliminar();  // Llama al método eliminar
                    break;
                case 0:
                    System.out.println("Saliendo...");  // Mensaje de salida
                    break;
                default:
                    System.out.println("Opcion invalida");  // Mensaje para opción inválida
                    break;
            }
        } while (opcion != 0);  // Repite el menú hasta que el usuario elija salir
    }

    public void listar() {  // Método para listar los alumnos
        listarAlumnos();  // Llama al método listarAlumnos de ModeloAlumno
        System.out.println("+----+-----------------+-------+-------+--------------+---------+----------+");
        System.out.println("|                               ALUMNOS                                    |");
        System.out.println("+----+-----------------+-------+-------+--------------+---------+----------+");
        System.out.println("| id |     Alumno      | Grupo | Ciclo | Cuatrimestre | Carrera |  Estado  |");
        System.out.println("+----+-----------------+-------+-------+--------------+---------+----------+");
        for (Alumno alumno : getCarteraAlumnos()) {  // Itera sobre la lista de alumnos
            System.out.printf("|%4d| %-15s | %-5s | %-4s | %-12s | %-7s | %-8s |\n",
                    alumno.getId(),
                    alumno.getNombre(),
                    alumno.getGrupo().getNombre(),
                    alumno.getGrupo().getCicloCuatri().getCiclo().getNombre(), // Obtiene el nombre del ciclo
                    alumno.getGrupo().getCicloCuatri().getCuatrimestre().getNombre(), // Obtiene el nombre del cuatrimestre
                    alumno.getGrupo().getCarrera().getNombre(),
                    alumno.getEstado().getNombre()
            );
        }
        System.out.println("+----+-----------------+-------+-------+--------------+---------+----------+");
    }

    public void crear() {  // Método para crear un nuevo alumno
        System.out.print("Ingrese el nombre del alumno: ");
        String nombre = scanner.nextLine();  // Lee el nombre del alumno

        gestorGrupos.listar();  // Lista los grupos
        System.out.print("Ingrese el id del grupo: ");
        int idGrupo = scanner.nextInt();  // Lee el ID del grupo

        gestorEstatus.listar();  // Lista los estados
        System.out.print("Ingrese el id del estado: ");
        int idEstado = scanner.nextInt();  // Lee el ID del estado
        scanner.nextLine();  // Limpia el buffer del scanner
        Crear(nombre, idGrupo, idEstado);  // Llama al método Crear de ModeloAlumno
    }

    public void leer() {  // Método para leer la información de un alumno
        listar();  // Llama al método listar
        System.out.print("Ingrese el id del alumno que deseas consultar: ");
        int id = scanner.nextInt();  // Lee el ID del alumno
        scanner.nextLine();  // Limpia el buffer del scanner
        Alumno alumno = Leer(id);  // Llama al método Leer de ModeloAlumno
        if (alumno != null) {  // Si el alumno existe
            System.out.println("+----+-----------------+-------+-------+--------------+---------+----------+");
            System.out.println("| id |     Alumno      | Grupo | Ciclo | Cuatrimestre | Carrera |  Estado  |");
            System.out.println("+----+-----------------+-------+-------+--------------+---------+----------+");
            System.out.printf("|%4d| %-15s | %-5s | %-4s | %-12s | %-7s | %-8s |\n",
                    alumno.getId(),
                    alumno.getNombre(),
                    alumno.getGrupo().getNombre(),
                    alumno.getGrupo().getCicloCuatri().getCiclo().getNombre(),
                    alumno.getGrupo().getCicloCuatri().getCuatrimestre().getNombre(),
                    alumno.getGrupo().getCarrera().getNombre(),
                    alumno.getEstado().getNombre()
            );
            System.out.println("+----+-----------------+-------+-------+--------------+---------+----------+");
        } else {
            System.out.println("Alumno no encontrado");  // Mensaje si el alumno no es encontrado
        }
    }

    public void actualizar() {  // Método para actualizar la información de un alumno
        listar();  // Llama al método listar
        System.out.print("Ingrese el ID del alumno que deseas actualizar: ");
        int id = scanner.nextInt();  // Lee el ID del alumno
        scanner.nextLine();  // Limpia el buffer del scanner
        Alumno alumno = Leer(id);  // Llama al método Leer de ModeloAlumno
        if (alumno == null) {  // Si el alumno no existe
            System.out.println("Alumno no encontrado.");
            return;  // Sale del método
        }

        System.out.print("Ingrese el nuevo ID del alumno (dejar en blanco para no realizar cambios): ");
        String inputIdAlumno = scanner.nextLine();  // Lee el nuevo ID del alumno
        int nuevoIdAlumno = inputIdAlumno.isEmpty() ? id : Integer.parseInt(inputIdAlumno);  // Si no se ingresa nada, mantiene el ID actual

        System.out.print("Ingrese el nuevo nombre del alumno (dejar en blanco para no realizar cambios): ");
        String nombre = scanner.nextLine();  // Lee el nuevo nombre del alumno
        if (nombre.isEmpty()) {  // Si no se ingresa nada, mantiene el nombre actual
            nombre = alumno.getNombre();
        }

        gestorGrupos.listar();  // Lista los grupos
        System.out.print("Ingrese el nuevo ID del grupo (dejar en blanco para no realizar cambios): ");
        String inputGrupo = scanner.nextLine();  // Lee el nuevo ID del grupo
        int idGrupo = inputGrupo.isEmpty() ? alumno.getGrupo().getId() : Integer.parseInt(inputGrupo);  // Si no se ingresa nada, mantiene el ID actual

        gestorEstatus.listar();  // Lista los estados
        System.out.print("Ingrese el nuevo ID del estado (dejar en blanco para no realizar cambios): ");
        String inputEstado = scanner.nextLine();  // Lee el nuevo ID del estado
        int idEstado = inputEstado.isEmpty() ? alumno.getEstado().getId() : Integer.parseInt(inputEstado);  // Si no se ingresa nada, mantiene el ID actual

        // Llama al método de actualización en ModeloAlumno
        Actualizar(id, nuevoIdAlumno, nombre, idGrupo, idEstado);
    }

    public void eliminar() {  // Método para eliminar un alumno
        listar();  // Llama al método listar
        System.out.print("Ingrese el id del alumno que deseas eliminar: ");
        int id = scanner.nextInt();  // Lee el ID del alumno
        scanner.nextLine();  // Limpia el buffer del scanner
        Eliminar(id);  // Llama al método Eliminar de ModeloAlumno
    }

    public static class Main {  // Clase principal para ejecutar el programa

        public static void main(String[] args) {
            GestorAlumnos gestor = new GestorAlumnos();  // Crea una instancia de GestorAlumnos
            gestor.menu();  // Muestra el menú de gestión de alumnos
        }
    }
}

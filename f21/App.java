package f21;

// Importar la clase Scanner para leer la entrada del usuario desde la consola
import java.util.Scanner;

public class App {

    // Método principal que se ejecuta al iniciar el programa
    public static void main(String[] args) {
        // Crear una instancia de Scanner para leer la entrada del usuario desde la consola
        Scanner scanner = new Scanner(System.in);

        int opcion; // Variable para almacenar la opción seleccionada por el usuario

        // Bucle principal del menú que se ejecuta hasta que el usuario elija salir (opción 0)
        do {
            // Mostrar una línea en blanco para mejorar la legibilidad del menú
            System.out.println(" ");
            // Mensaje de bienvenida a la aplicación
            System.out.println("Bienvenido a la aplicacion de Servicios Escolares.");
            // Información sobre el grupo de desarrollo
            System.out.println("Este proyecto esta desarrollado por el grupo TIDSM21.");
            System.out.println("Integrantes del equipo: ");
            // Detallar los integrantes del equipo y sus identificadores
            System.out.println("\n 1. Castillo Rodriguez Enrique - 2302045 ");
            System.out.println("\n 2. Guzman Perez Yair Gamaliel - 2302133 ");
            System.out.println("\n 3. Sanchez Trejo Cesar David - 2302073 ");
            System.out.println("\n 4. Santos Absalon Aaron de Jesus - 2302042 ");
            // Línea en blanco para separar la información
            System.out.println(" ");
            // Mostrar el menú principal con opciones para diferentes gestores
            System.out.println("\n---Menu---Principal---de---Gestores---");
            System.out.println("|1|. Gestor de Ciclos");
            System.out.println("|2|. Gestor de Cuatrimestres");
            System.out.println("|3|. Gestor de Ciclo_Cuatri");
            System.out.println("|4|. Gestor de Carreras");
            System.out.println("|5|. Gestor de Grupos");
            System.out.println("|6|. Gestor de Estatus");
            System.out.println("|7|. Gestor de Alumnos");
            System.out.println("|8|. Gestor de Profesores");
            System.out.println("|9|. Gestor de Materias");
            System.out.println("|10|. Gestor de Asignaciones");
            System.out.println("|11|. Gestor de Calificaciones");
            System.out.println("|0|. Salir");
            // Pedir al usuario que seleccione una opción
            System.out.print("Seleccione una opcion: ");
            // Leer la opción seleccionada por el usuario
            opcion = scanner.nextInt();
            // Limpiar el buffer del Scanner para evitar problemas con entradas posteriores
            scanner.nextLine();

            // Ejecutar la opción seleccionada mediante una estructura de control switch
            switch (opcion) {
                case 1:
                    // Crear una instancia de GestorCiclos y mostrar su menú
                    GestorCiclos gestorCiclos = new GestorCiclos();
                    gestorCiclos.menu();
                    break;
                case 2:
                    // Crear una instancia de GestorCuatrimestres y mostrar su menú
                    GestorCuatrimestres gestorCuatrimestres = new GestorCuatrimestres();
                    gestorCuatrimestres.menu();
                    break;
                case 3:
                    // Crear una instancia de GestorCC y mostrar su menú
                    GestorCC gestorCC = new GestorCC();
                    gestorCC.menu();
                    break;
                case 4:
                    // Crear una instancia de GestorCarreras y mostrar su menú
                    GestorCarreras gestorCarreras = new GestorCarreras();
                    gestorCarreras.menu();
                    break;
                case 5:
                    // Crear una instancia de GestorGrupos y mostrar su menú
                    GestorGrupos gestorGrupos = new GestorGrupos();
                    gestorGrupos.menu();
                    break;
                case 6:
                    // Crear una instancia de GestorEstatus y mostrar su menú
                    GestorEstatus gestorEstatus = new GestorEstatus();
                    gestorEstatus.menu();
                    break;
                case 7:
                    // Crear una instancia de GestorAlumnos y mostrar su menú
                    GestorAlumnos gestorAlumnos = new GestorAlumnos();
                    gestorAlumnos.menu();
                    break;
                case 8:
                    // Crear una instancia de GestorProfesores y mostrar su menú
                    GestorProfesores gestorProfesores = new GestorProfesores();
                    gestorProfesores.menu();
                    break;
                case 9:
                    // Crear una instancia de GestorMaterias y mostrar su menú
                    GestorMaterias gestorMaterias = new GestorMaterias();
                    gestorMaterias.menu();
                    break;
                case 10:
                    // Crear una instancia de GestorAsignaciones y mostrar su menú
                    GestorAsignaciones gestorAsignaciones = new GestorAsignaciones();
                    gestorAsignaciones.menu();
                    break;
                case 11:
                    // Crear una instancia de GestorCalificaciones y mostrar su menú
                    GestorCalificaciones gestorCalificaciones = new GestorCalificaciones();
                    gestorCalificaciones.menu();
                    break;
                case 0:
                    // Mensaje de salida cuando el usuario elige salir
                    System.out.println("Saliendo......");
                    break;
                default:
                    // Mensaje de error para opciones no válidas
                    System.out.println("Opcion invalida, por favor intente de nuevo.");
            }
        } while (opcion != 0); // Continuar el bucle hasta que el usuario elija salir (opción 0)

        // Cerrar el Scanner para liberar los recursos
        scanner.close();
    }
}

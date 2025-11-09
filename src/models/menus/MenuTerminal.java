//package models.menus;
//
//import Exceptions.GestorOperationException;
//import Exceptions.UnauthorizedActionException;
//import gestores.GenericGestor;
//import models.rutinas.Ejercicio;
//import models.rutinas.Rutina;
//import models.users.User;
//
//import java.util.Scanner;
//
//public class MenuTerminal {
//
//    //Crear MENU Terminal
//    public static void menuPrincipal(GenericGestor<User> usuarios, GenericGestor<Ejercicio> ejercicios, Scanner sc){
//
//        try {
//            System.out.print("Rol actual (admin/profesor/alumno): ");
//            String rol = sc.nextLine().trim().toLowerCase();
//
//            boolean running = true;
//            while (running) {
//                System.out.println("\nElija una opción:");
//                System.out.println("1 - Crear usuario (solo admin)");
//                System.out.println("2 - Crear ejercicio (admin/profesor)");
//                System.out.println("3 - Crear rutina (admin/profesor)");
//                System.out.println("4 - Dar de baja usuario (solo admin)");
//                System.out.println("5 - Dar de baja rutina");
//                System.out.println("6 - Buscar usuario");
//                System.out.println("7 - Salir");
//                String opt = sc.nextLine().trim();
//
//                try {
//                    switch (opt) {
//                        case "1": // ----- Crear Usuario -----
//                            if (!"admin".equals(rol)) {
//                                throw new UnauthorizedActionException("Solo el admin puede crear usuarios.");
//                            }
//                            System.out.print("Rol del nuevo usuario (profesor/cliente): ");
//                            String tipoUsuario = sc.nextLine().trim().toLowerCase();
//
//                            User nuevoUsuario;
//                            switch (tipoUsuario) {
//                                case "profesor":
//                                    //nuevoUsuario = new models.users.Profesor(); // ??
//                                    break;
//                                case "cliente":
//                                default:
//                                    nuevoUsuario = new models.users.Cliente();
//                                    break;
//                            }
//                            nuevoUsuario = nuevoUsuario.crear(sc);
//                            usuarios.altaItem(nuevoUsuario);
//                            System.out.println("Usuario creado: " + nuevoUsuario);
//
//                            break;
//
//                        case "2": {// ----- Crear Ejercicio -----
//                            if (!("admin".equals(rol) || "profesor".equals(rol))) {
//                                throw new UnauthorizedActionException("Solo los admins o profesores pueden crear ejercicios");
//                            }
//                            Ejercicio ejercicio = new Ejercicio();
//                            ejercicio = ejercicio.crearEjercicio(sc);// ----- Duda sobre como pasar el Scanner.
//                            ejercicios.altaItem(ejercicio);
//                            System.out.println("Ejercicio creado: " + ejercicio);
//
//                            break;
//                        }
//                        case "3": // ----- Crear Rutina -----
//                            if (!("admin".equals(rol) || "profesor".equals(rol))) {
//                                throw new UnauthorizedActionException("Solo los admin o profesores pueden crear rutinas");
//                            }
//                            Rutina rutina = Rutina.crearRutina(); // ----- Duda sobre como pasar el Scanner.
//                            rutinas.altaItem(rutina);
//                            System.out.println("Rutina creada: " + rutina);
//
//                            break;
//
//                        case "4": // ----- Baja de Usuario -----
//                            if (!"admin".equals(rol)) {
//                                throw new UnauthorizedActionException("Solo los admins puede dar de baja usuarios");
//                            }
//                            System.out.print("ID del usuario a dar de baja: ");
//                            int claveBaja = Integer.parseInt(sc.nextLine().trim());
//                            User encontrado = usuarios.buscarItem(claveBaja);
//                            if (encontrado == null) {
//                                throw new GestorOperationException("ERROR. Usuario no encontrado: " + claveBaja);
//                            }
//                            usuarios.bajaItem(claveBaja);
//                            System.out.println("Usuario dado de baja: " + encontrado);
//
//                            break;
//
//                        case "5": // ----- Baja de Rutina -----
//                            System.out.print("ID de la rutina a dar de baja: ");
//                            int claveRut = Integer.parseInt(sc.nextLine().trim());
//                            Rutina rutinaEncontrada = rutinas.buscarItem(claveRut);
//                            if (rutinaEncontrada == null) {
//                                throw new GestorOperationException("ERROR. Rutina no encontrada: " + claveRut);
//                            }
//                            rutinas.bajaItem(claveRut);
//                            System.out.println("Rutina dada de baja: " + rutinaEncontrada);
//
//                            break;
//
//                        case "6": // -----Buscar Usuario -----
//                            System.out.print("Buscar usuario por ID: ");
//                            int  buscar = Integer.parseInt(sc.nextLine().trim());
//                            User u = usuarios.buscarItem(buscar);
//                            System.out.println(u == null ? "ERROR. Usuario no encontrado" : "Usuario: " + u);
//
//                            break;
//
//                        case "7":
//                            running = false;
//
//                            break;
//
//                        default:
//                            System.out.println("Opción no válida");
//                    }
//                } catch (UnauthorizedActionException | GestorOperationException ex) {
//                    System.err.println("Error: " + ex.getMessage());
//                } catch (Exception ex) {
//                    System.err.println("Error inesperado: " + ex.getMessage());
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("Error general: " + e.getMessage());
//        }
//    }
//    // Pensar flujo del programa
//
//    // Ver en las clases los metodos que faltan y si los que estan , estan bien
//
//
//}

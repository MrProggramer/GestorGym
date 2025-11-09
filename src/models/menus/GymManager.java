//import gestores.GenericGestor;
//import models.rutinas.Rutina;
//import models.users.Cliente;
//import java.util.Scanner;
//
//public class GymManager {
//    public static void main(String[] args) {
//        GenericGestor<Rutina> gestorRutinas = new GenericGestor<>();
//        GenericGestor<Cliente> gestorClientes = new GenericGestor<>();
//        Scanner scanner = new Scanner(System.in);
//        int opcion;
//
//        do {
//            System.out.println("=== GESTIÓN GIMNASIO ===");
//            System.out.println("1. Agregar rutina");
//            System.out.println("2. Buscar rutina");
//            System.out.println("3. Modificar rutina");
//            System.out.println("4. Eliminar rutina");
//            System.out.println("5. Agregar cliente");
//            System.out.println("6. Buscar cliente");
//            System.out.println("7. Modificar cliente");
//            System.out.println("8. Eliminar cliente");
//            System.out.println("0. Salir");
//            System.out.print("Seleccione una opción: ");
//            opcion = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (opcion) {
//                case 1:
//                    /// ----- Agregar rutina -----
//
//                    try {
//                        System.out.print("Nombre: ");
//                        String nombreRutina = scanner.nextLine();
//                        System.out.print("Descripción: ");
//                        String desc = scanner.nextLine();
//                        System.out.print("Cantidad de días por semana: ");
//                        int dias = scanner.nextInt();
//                        scanner.nextLine();
//
//                        Rutina nueva = new Rutina(dias, desc, nombreRutina);
//                        gestorRutinas.altaItem(nueva);
//                        System.out.println("Rutina agregada. ID generado: " + nueva.getId());
//                    } catch (Exception e) {
//                        System.out.println("Error: " + e.getMessage());
//                    }
//                    break;
//
//                case 2:
//                    /// ----- Buscar rutina -----
//
//                    try {
//                        System.out.print("ID rutina: ");
//                        int idRutina = Integer.parseInt(scanner.nextLine());
//                        Rutina r = gestorRutinas.buscarItem(idRutina);
//                        System.out.println("Rutina encontrada: " + r);
//                    } catch (Exception e) {
//                        System.out.println("Error: " + e.getMessage());
//                    }
//                    break;
//
//                case 3:
//                    /// ----- Modificar rutina -----
//
//                    try {
//                        System.out.print("ID rutina: ");
//                        int idRutina = Integer.parseInt(scanner.nextLine());
//                        Rutina r = gestorRutinas.buscarItem(idRutina);
//                        System.out.print("Nueva descripción: ");
//                        String nuevaDesc = scanner.nextLine();
//                        r.setDescripcionRutina(nuevaDesc);
//                        gestorRutinas.modificarItem(r);
//                        System.out.println("Rutina modificada.");
//                    } catch (Exception e) {
//                        System.out.println("Error: " + e.getMessage());
//                    }
//                    break;
//
//                case 4:
//                    /// ----- Eliminar rutina -----
//
//                    try {
//                        System.out.print("ID rutina: ");
//                        int idRutina = Integer.parseInt(scanner.nextLine());
//                        gestorRutinas.bajaItem(idRutina);
//                        System.out.println("Rutina eliminada");
//                    } catch (Exception e) {
//                        System.out.println("Error: " + e.getMessage());
//                    }
//                    break;
//
//                case 5:
//                    /// ----- Agregar cliente -----
//
//                    try {
//                        System.out.print("Nombre: ");
//                        String nombre = scanner.nextLine();
//                        System.out.print("DNI: ");
//                        String dni = scanner.nextLine();
//                        System.out.print("Email: ");
//                        String mail = scanner.nextLine();
//                        System.out.print("Teléfono: ");
//                        String telefono = scanner.nextLine();
//                        System.out.print("Usuario: ");
//                        String user = scanner.nextLine();
//                        System.out.print("Contraseña: ");
//                        String pass = scanner.nextLine();
//                        System.out.print("¿Cuota al día? (true/false): ");
//                        boolean cuotaAlDia = Boolean.parseBoolean(scanner.nextLine());
//                        System.out.print("Cantidad de días por semana: ");
//                        int dias = Integer.parseInt(scanner.nextLine());
//
//                        Cliente cliente = new Cliente(nombre, dni, mail, telefono, user, pass, cuotaAlDia, dias);
//                        gestorClientes.altaItem(cliente);
//                        System.out.println("Cliente agregado. ID generado: " + cliente.getId());
//                    } catch (Exception e) {
//                        System.out.println("Error: " + e.getMessage());
//                    }
//                    break;
//
//                case 6:
//                    /// ----- Buscar cliente -----
//
//                    try {
//                        System.out.print("ID cliente: ");
//                        int idCliente = Integer.parseInt(scanner.nextLine());
//                        Cliente c = gestorClientes.buscarItem(idCliente);
//                        System.out.println("Cliente encontrado: " + c.getNombre());
//                    } catch (Exception e) {
//                        System.out.println("Error: " + e.getMessage());
//                    }
//                    break;
//
//                case 7:
//                    /// ----- Modificar cliente -----
//
//                    try {
//                        System.out.print("ID cliente: ");
//                        int idCliente = Integer.parseInt(scanner.nextLine());
//                        Cliente c = gestorClientes.buscarItem(idCliente);
//                        System.out.print("Nuevo nombre: ");
//                        String nuevoNombre = scanner.nextLine();
//                        c.setNombre(nuevoNombre);
//                        gestorClientes.modificarItem(c);
//                        System.out.println("Cliente modificado.");
//                    } catch (Exception e) {
//                        System.out.println("Error: " + e.getMessage());
//                    }
//                    break;
//
//                case 8:
//                    /// ----- Eliminar cliente -----
//
//                    try {
//                        System.out.print("ID cliente: ");
//                        int idCliente = Integer.parseInt(scanner.nextLine());
//                        gestorClientes.bajaItem(idCliente);
//                        System.out.println("Cliente eliminado");
//                    } catch (Exception e) {
//                        System.out.println("Error: " + e.getMessage());
//                    }
//                    break;
//
//                case 0:
//                    /// ----- Salir -----
//
//                    System.out.println("Saliendo...");
//                    break;
//
//                default:
//                    System.out.println("Opción inválida, intente de nuevo");
//            }
//            System.out.println();
//        } while (opcion != 0);
//
//        scanner.close();
//    }
//}
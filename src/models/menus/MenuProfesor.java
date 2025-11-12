package models.menus;

import gestores.GenericGestor;
import models.database.ControlData;
import models.rutinas.Rutina;
import models.users.Cliente;
import models.users.Profesor;
import models.users.User;

import java.util.Objects;
import java.util.Scanner;

public class MenuProfesor {
    private final Scanner sc;
    private final GenericGestor<User> usuarios;
    private final GenericGestor<Rutina> rutinas;
    private final Profesor profesor;

    public MenuProfesor(Scanner sc, GenericGestor<User> usuarios, GenericGestor<Rutina> rutinas, Profesor profesor) {
        this.sc = sc;
        this.usuarios = usuarios;
        this.rutinas = rutinas;
        this.profesor = profesor;
    }

    public void show() {
        int opt;
        do {
            System.out.println("\n=== MENÚ PROFESOR: " + profesor.getNombre() + " ===");
            System.out.println("1. Ver clientes con sus rutinas");
            System.out.println("2. Asignar rutina a cliente");
            System.out.println("3. Modificar rutina de cliente");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            opt = readInt();
            
            switch (opt) {
                case 1:
                    verClientesConRutinas();
                    break;
                case 2:
                    asignarRutina();
                    break;
                case 3:
                    modificarRutinaCliente();
                    break;
                case 4:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opt != 4);
    }

    // 1. Ver clientes con sus rutinas
    private void verClientesConRutinas() {
        System.out.println("\n=== CLIENTES Y SUS RUTINAS ===");
        
        boolean hayClientes = false;
        for (User u : usuarios.getInventario()) {
            if (u instanceof Cliente c && c.isActive()) {
                hayClientes = true;
                System.out.println("\nCliente: " + c.getNombre() + " (" + c.getUser() + ")");
                System.out.println("  DNI: " + c.getDni());
                System.out.println("  Días restantes: " + c.getDias());
                System.out.println("  Cuota al día: " + (c.isCuotaAlDia() ? "Sí" : "No"));
                
                if (c.getRutina() != null) {
                    Rutina rutina = c.getRutina();
                    System.out.println("  Rutina asignada: " + rutina.getNombre());
                    System.out.println("    - Descripción: " + rutina.getDescripcionRutina());
                    System.out.println("    - Días por semana: " + rutina.getCantidadDeDias());
                    System.out.println("    - Cantidad de ejercicios: " + rutina.getListaEjercicios().size());
                } else {
                    System.out.println("No hay rutinas asignadas");
                }
            }
        }
        
        if (!hayClientes) {
            System.out.println("No hay clientes registrados");
        }
    }

    // 2. Asignar rutina a cliente
    private void asignarRutina() {
        System.out.println("\n=== ASIGNAR RUTINA A CLIENTE ===");
        
        Cliente cliente = buscarClientePorUsuario();
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        }
        
        if (rutinas.getInventario().isEmpty()) {
            System.out.println("No hay rutinas disponibles");
        }
        
        System.out.println("\nCliente seleccionado: " + cliente.getNombre());
        if (cliente.getRutina() != null) {
            System.out.println("Rutina actual: " + cliente.getRutina().getNombre());
        } else {
            System.out.println("No se encontro ninguna rutina");
        }
        
        System.out.println("\nRutinas disponibles:");
        for (int i = 0; i < rutinas.getInventario().size(); i++) {
            Rutina r = rutinas.getInventario().get(i);
            System.out.println((i + 1) + ". " + r.getNombre() + " (" + r.getCantidadDeDias() + " días/semana)");
        }
        
        System.out.print("Seleccione el número de la rutina a asignar: ");
        int numRutina = readInt();
        
        if (numRutina <= 0 || numRutina > rutinas.getInventario().size()) {
            System.out.println("Número de rutina inválido");
            return;
        }
        
        Rutina rutinaSeleccionada = rutinas.getInventario().get(numRutina - 1);
        cliente.setRutina(rutinaSeleccionada);
        
        ControlData.guardarData(usuarios, "users");
        usuarios.actualizarGestor("users");
        
        System.out.println("Rutina \"" + rutinaSeleccionada.getNombre() + "\" asignada exitosamente a " + cliente.getNombre());
    }

    // 3. Modificar rutina de cliente
    private void modificarRutinaCliente() {
        System.out.println("\n=== MODIFICAR RUTINA DE CLIENTE ===");
        
        Cliente cliente = buscarClientePorUsuario();
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }
        
        System.out.println("\nCliente: " + cliente.getNombre());
        
        if (cliente.getRutina() == null) {
            System.out.println("El cliente no tiene una rutina asignada.");
            System.out.println("¿Desea asignar una rutina? (s/n): ");
            String respuesta = sc.nextLine().toLowerCase();
            if (respuesta.equals("s") || respuesta.equals("si")) {
                asignarRutina();
            }
            return;
        }
        
        System.out.println("Rutina actual: " + cliente.getRutina().getNombre());
        
        if (rutinas.getInventario().isEmpty()) {
            System.out.println("No hay rutinas disponibles");
            return;
        }
        
        System.out.println("\nOpciones:");
        System.out.println("1. Cambiar a otra rutina");
        System.out.println("2. Eliminar rutina asignada");
        System.out.print("Seleccione una opción: ");
        
        int opcion = readInt();
        
        switch (opcion) {
            case 1:
                // Cambiar rutina
                System.out.println("\nRutinas disponibles:");
                for (int i = 0; i < rutinas.getInventario().size(); i++) {
                    Rutina r = rutinas.getInventario().get(i);
                    String marca = (r.getId() == cliente.getRutina().getId()) ? " (actual)" : "";
                    System.out.println((i + 1) + ". " + r.getNombre() + " (" + r.getCantidadDeDias() + " días/semana)" + marca);
                }
                
                System.out.print("Seleccione el número de la nueva rutina: ");
                int numRutina = readInt();
                
                if (numRutina <= 0 || numRutina > rutinas.getInventario().size()) {
                    System.out.println("Número de rutina inválido");
                }
                
                Rutina nuevaRutina = rutinas.getInventario().get(numRutina - 1);
                
                if (nuevaRutina.getId() == cliente.getRutina().getId()) {
                    System.out.println("El cliente ya tiene asignada esa rutina");
                }
                
                cliente.setRutina(nuevaRutina);
                ControlData.guardarData(usuarios, "users");
                usuarios.actualizarGestor("users");
                
                System.out.println("Rutina modificada exitosamente. Nueva rutina: " + nuevaRutina.getNombre());
                break;
                
            case 2:
                // Eliminar rutina
                System.out.print("¿Está seguro de eliminar la rutina asignada? (s/n): ");
                String confirmacion = sc.nextLine().toLowerCase();
                
                if (confirmacion.equals("s") || confirmacion.equals("si")) {
                    cliente.setRutina(null);
                    ControlData.guardarData(usuarios, "users");
                    usuarios.actualizarGestor("users");
                    System.out.println("Rutina eliminada exitosamente de " + cliente.getNombre());
                } else {
                    System.out.println("Operación cancelada");
                }
                break;
                
            default:
                System.out.println("Opción inválida");
        }
    }

    // buscar cliente por usuario
    private Cliente buscarClientePorUsuario() {
        System.out.print("Ingrese el usuario del cliente: ");
        String usuario = sc.nextLine();
        
        for (User u : usuarios.getInventario()) {
            if (u instanceof Cliente c && Objects.equals(c.getUser(), usuario) && c.isActive()) {
                return c;
            }
        }
        return null;
    }


    private int readInt() {
        try {
            String line = sc.nextLine();
            return Integer.parseInt(line.trim());
        } catch (Exception e) {
            System.out.println("Número inválido, ingrese un número válido");
            return -1;
        }
    }
}

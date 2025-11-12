package models.menus;

import gestores.GenericGestor;
import models.database.ControlData;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.Cliente;
import models.users.Profesor;
import models.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenuAdmin {
    private final Scanner sc;
    private final GenericGestor<User> usuarios;
    private final GenericGestor<Rutina> rutinas;
    private final GenericGestor<Ejercicio> ejercicios;

    public MenuAdmin(Scanner sc, GenericGestor<User> usuarios, GenericGestor<Rutina> rutinas, GenericGestor<Ejercicio> ejercicios) {
        this.sc = sc;
        this.usuarios = usuarios;
        this.rutinas = rutinas;
        this.ejercicios = ejercicios;
    }

    public void show() {
        int opt;
        do {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Crear cliente");
            System.out.println("2. Modificar cliente");
            System.out.println("3. Buscar cliente");
            System.out.println("4. Crear profesor");
            System.out.println("5. Buscar profesor");
            System.out.println("6. Eliminar profesor");
            System.out.println("7. Crear rutina");
            System.out.println("8. Asignar rutina");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            
            opt = readInt();
            
            switch (opt) {
                case 1:
                    crearCliente();
                    break;
                case 2:
                    modificarCliente();
                    break;
                case 3:
                    buscarCliente();
                    break;
                case 4:
                    crearProfesor();
                    break;
                case 5:
                    buscarProfesor();
                    break;
                case 6:
                    eliminarProfesor();
                    break;
                case 7:
                    crearRutina();
                    break;
                case 8:
                    asignarRutina();
                    break;
                case 9:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opt != 9);
    }

    // 1. Crear cliente
    private void crearCliente() {
        System.out.println("\n=== CREAR CLIENTE ===");
        
        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        
        System.out.print("Ingrese el DNI: ");
        String dni = sc.nextLine();
        
        System.out.print("Ingrese el mail: ");
        String mail = sc.nextLine();
        
        System.out.print("Ingrese el teléfono: ");
        String telefono = sc.nextLine();
        
        System.out.print("Ingrese el usuario: ");
        String user = sc.nextLine();
        
        // Verificar que el usuario no exista
        for (User u : usuarios.getInventario()) {
            if (Objects.equals(u.getUser(), user)) {
                System.out.println("Error: El usuario ya existe");
            }
        }
        
        System.out.print("Ingrese la contraseña: ");
        String pass = sc.nextLine();
        
        System.out.print("Ingrese los días de membresía (por defecto 30): ");
        int dias = readInt();
        if (dias <= 0) dias = 30;
        
        Cliente nuevoCliente = new Cliente(nombre, dni, mail, telefono, user, pass, true, dias);
        usuarios.altaItem(nuevoCliente);
        ControlData.guardarData(usuarios, "users");
        usuarios.actualizarGestor("users");
        
        System.out.println("Cliente creado exitosamente con ID: " + nuevoCliente.getId());
    }

    // 2. Modificar cliente
    private void modificarCliente() {
        System.out.println("\n=== MODIFICAR CLIENTE ===");
        
        Cliente cliente = buscarClientePorUsuario();
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        }
        
        System.out.println("Cliente encontrado: " + cliente.getNombre());
        System.out.println("Deje en blanco para mantener el valor actual");
        
        System.out.print("Nuevo nombre [" + cliente.getNombre() + "]: ");
        String nombre = sc.nextLine();
        if (!nombre.isBlank()) {
            cliente.setNombre(nombre);
        }
        
        System.out.print("Nuevo DNI [" + cliente.getDni() + "]: ");
        String dni = sc.nextLine();
        if (!dni.isBlank()) {
            cliente.setDni(dni);
        }
        
        System.out.print("Nuevo mail [" + cliente.getMail() + "]: ");
        String mail = sc.nextLine();
        if (!mail.isBlank()) {
            cliente.setMail(mail);
        }
        
        System.out.print("Nuevo teléfono [" + cliente.getTelefono() + "]: ");
        String telefono = sc.nextLine();
        if (!telefono.isBlank()) {
            cliente.setTelefono(telefono);
        }
        
        System.out.print("Nueva contraseña [***]: ");
        String pass = sc.nextLine();
        if (!pass.isBlank()) {
            cliente.setPass(pass);
        }

        
        ControlData.guardarData(usuarios, "users");
        usuarios.actualizarGestor("users");
        System.out.println("Cliente modificado exitosamente");
    }

    // 3. Buscar cliente
    private void buscarCliente() {
        System.out.println("\n=== BUSCAR CLIENTE ===");
        
        Cliente cliente = buscarClientePorUsuario();
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        }
        
        System.out.println("\n=== DATOS DEL CLIENTE ===");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("DNI: " + cliente.getDni());
        System.out.println("Mail: " + cliente.getMail());
        System.out.println("Teléfono: " + cliente.getTelefono());
        System.out.println("Usuario: " + cliente.getUser());
        System.out.println("Días restantes: " + cliente.getDias());
        System.out.println("Cuota al día: " + (cliente.isCuotaAlDia() ? "Sí" : "No"));
        System.out.println("Activo: " + (cliente.isActive() ? "Sí" : "No"));
        if (cliente.getRutina() != null) {
            System.out.println("Rutina asignada: " + cliente.getRutina().getNombre());
        } else {
            System.out.println("Rutina asignada: Ninguna");
        }
    }

    // Metodo de busqueda por usuario
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

    // 4. Crear profesor
    private void crearProfesor() {
        System.out.println("\n=== CREAR PROFESOR ===");
        
        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        
        System.out.print("Ingrese el DNI: ");
        String dni = sc.nextLine();
        
        System.out.print("Ingrese el mail: ");
        String mail = sc.nextLine();
        
        System.out.print("Ingrese el teléfono: ");
        String telefono = sc.nextLine();
        
        System.out.print("Ingrese el usuario: ");
        String user = sc.nextLine();
        
        // Verificar que el usuario no exista
        for (User u : usuarios.getInventario()) {
            if (Objects.equals(u.getUser(), user)) {
                System.out.println("Error: El usuario ya existe");
                return;
            }
        }
        
        System.out.print("Ingrese la contraseña: ");
        String pass = sc.nextLine();
        
        Profesor nuevoProfesor = new Profesor();
        nuevoProfesor.setActive(true);
        nuevoProfesor.setNombre(nombre);
        nuevoProfesor.setDni(dni);
        nuevoProfesor.setMail(mail);
        nuevoProfesor.setTelefono(telefono);
        nuevoProfesor.setUser(user);
        nuevoProfesor.setPass(pass);
        
        usuarios.altaItem(nuevoProfesor);
        ControlData.guardarData(usuarios, "users");
        usuarios.actualizarGestor("users");
        
        System.out.println("Profesor creado exitosamente con ID: " + nuevoProfesor.getId());
    }

    // 5. Buscar profesor
    private void buscarProfesor() {
        System.out.println("\n=== BUSCAR PROFESOR ===");
        
        System.out.print("Ingrese el usuario del profesor: ");
        String usuario = sc.nextLine();
        
        Profesor profesor = null;
        for (User u : usuarios.getInventario()) {
            if (u instanceof Profesor p && Objects.equals(p.getUser(), usuario) && p.isActive()) {
                profesor = p;
            }
        }
        
        if (profesor == null) {
            System.out.println("Profesor no encontrado");
            return;
        }
        
        System.out.println("\n=== DATOS DEL PROFESOR ===");
        System.out.println("ID: " + profesor.getId());
        System.out.println("Nombre: " + profesor.getNombre());
        System.out.println("DNI: " + profesor.getDni());
        System.out.println("Mail: " + profesor.getMail());
        System.out.println("Teléfono: " + profesor.getTelefono());
        System.out.println("Usuario: " + profesor.getUser());
        System.out.println("Activo: " + (profesor.isActive() ? "Sí" : "No"));
    }

    // 6. Eliminar profesor
    private void eliminarProfesor() {
        System.out.println("\n=== ELIMINAR PROFESOR ===");
        
        System.out.print("Ingrese el usuario del profesor a eliminar: ");
        String usuario = sc.nextLine();
        
        Profesor profesor = null;
        for (User u : usuarios.getInventario()) {
            if (u instanceof Profesor p && Objects.equals(p.getUser(), usuario) && p.isActive()) {
                profesor = p;
            }
        }
        
        if (profesor == null) {
            System.out.println("Profesor no encontrado");
        }
        
        System.out.println("¿Está seguro de eliminar al profesor " + profesor.getNombre() + "? (s/n)");
        String confirmacion = sc.nextLine().toLowerCase();
        
        if (confirmacion.equals("s") || confirmacion.equals("si")) {
            usuarios.bajaItem(profesor.getId());
            ControlData.guardarData(usuarios, "users");
            usuarios.actualizarGestor("users");
            System.out.println("Profesor eliminado exitosamente");
        } else {
            System.out.println("Operación cancelada");
        }
    }

    // 7. Crear rutina
    private void crearRutina() {
        System.out.println("\n=== CREAR RUTINA ===");
        
        System.out.print("Ingrese el nombre de la rutina: ");
        String nombre = sc.nextLine();
        
        System.out.print("Ingrese la descripción: ");
        String descripcion = sc.nextLine();
        
        System.out.print("Ingrese la cantidad de días a la semana: ");
        int cantidadDias = readInt();
        if (cantidadDias <= 0) {
            System.out.println("Cantidad de días inválida");
        }
        
        // Mostrar ejercicios disponibles
        if (ejercicios.getInventario().isEmpty()) {
            System.out.println("No hay ejercicios disponibles. Debe crear ejercicios primero.");
            return;
        }
        
        System.out.println("\nEjercicios disponibles:");
        for (int i = 0; i < ejercicios.getInventario().size(); i++) {
            Ejercicio e = ejercicios.getInventario().get(i);
            System.out.println((i + 1) + ". " + e.getNombre() + " (" + e.getTipoGrupoMuscular() + ")");
        }
        
        List<Ejercicio> ejerciciosSeleccionados = new ArrayList<>();
        System.out.println("\nIngrese los números de los ejercicios (separados por comas, 0 para terminar):");
        String input = sc.nextLine();
        String[] numeros = input.split(",");
        
        for (String numStr : numeros) {
            try {
                int num = Integer.parseInt(numStr.trim());
                if (num == 0) break;
                if (num > 0 && num <= ejercicios.getInventario().size()) {
                    ejerciciosSeleccionados.add(ejercicios.getInventario().get(num - 1));
                }
            } catch (NumberFormatException e) {
            }
        }
        
        if (ejerciciosSeleccionados.isEmpty()) {
            System.out.println("Debe seleccionar al menos un ejercicio");
        }
        
        Rutina nuevaRutina = new Rutina(cantidadDias, descripcion, nombre);
        nuevaRutina.setListaEjercicios(ejerciciosSeleccionados);
        
        rutinas.altaItem(nuevaRutina);
        ControlData.guardarData(rutinas, "rutinas");
        rutinas.actualizarGestor("rutinas");
        
        System.out.println("Rutina creada exitosamente con ID: " + nuevaRutina.getId());
    }

    // 8. Asignar rutina
    private void asignarRutina() {
        System.out.println("\n=== ASIGNAR RUTINA ===");

        Cliente cliente = buscarClientePorUsuario();
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        }


        if (rutinas.getInventario().isEmpty()) {
            System.out.println("No hay rutinas disponibles");
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
        }
        
        Rutina rutinaSeleccionada = rutinas.getInventario().get(numRutina - 1);
        cliente.setRutina(rutinaSeleccionada);
        
        ControlData.guardarData(usuarios, "users");
        usuarios.actualizarGestor("users");
        
        System.out.println("Rutina \"" + rutinaSeleccionada.getNombre() + "\" asignada exitosamente a " + cliente.getNombre());
    }

    // metodo para leer enteros (si en vez de un int ponen un String sale error)
    private int readInt() {
        try {
            String line = sc.nextLine();
            return Integer.parseInt(line.trim());
        } catch (Exception e) {
            return -1;
        }
    }
}

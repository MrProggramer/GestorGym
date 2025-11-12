package models.menus;

import gestores.GenericGestor;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.Admin;
import models.users.Cliente;
import models.users.ClienteTemporal;
import models.users.Profesor;
import models.users.User;

import java.util.Objects;
import java.util.Scanner;

public class MenuLogin {
    private final Scanner sc;
    private final GenericGestor<User> usuarios;

    public MenuLogin(Scanner sc, GenericGestor<User> usuarios) {
        this.sc = sc;
        this.usuarios = usuarios;
    }

    public void MenuLogin() {
        System.out.println("Bienvenido a GYM");

        System.out.println("Ingrese su usuario");
        String usuario = sc.nextLine();
        System.out.println("Ingrese su clave");
        String clave = sc.nextLine();

        // Buscar y autenticar usuario
        User user = autenticar(usuario, clave);

        if (user == null) {
            System.out.println("Usuario o contraseña incorrectos");
            return;
        }

        // Verificar tipo de usuario y redirigir al menú correspondiente
        if (user instanceof Admin) {
            System.out.println("Bienvenido Administrador: " + user.getNombre());
            Admin admin = (Admin) user;
            
            // Inicializar gestores del admin
            GenericGestor<Rutina> rutinas = admin.getRutinas();
            if (rutinas == null) {
                rutinas = new GenericGestor<>();
                admin.setRutinas(rutinas);
            }
            try {
                rutinas.actualizarGestor("rutinas");
            } catch (Exception e) {
                // Continuar si hay error
            }
            
            GenericGestor<User> usuariosAdmin = admin.getUsers();
            if (usuariosAdmin == null) {
                usuariosAdmin = usuarios; // Usar el mismo gestor de usuarios
                admin.setUsers(usuariosAdmin);
            } else {
                try {
                    usuariosAdmin.actualizarGestor("users");
                } catch (Exception e) {
                    // Continuar si hay error
                }
            }
            
            // Crear gestor de ejercicios
            GenericGestor<Ejercicio> ejercicios = new GenericGestor<>();
            try {
                ejercicios.actualizarGestor("ejercicios");
            } catch (Exception e) {
                // Continuar si no hay ejercicios
            }
            
            MenuAdmin menuAdmin = new MenuAdmin(sc, usuariosAdmin, rutinas, ejercicios);
            menuAdmin.show();
        } else if (user instanceof Profesor) {
            System.out.println("Bienvenido Profesor: " + user.getNombre());
            Profesor profesor = (Profesor) user;
            
            // Crear gestor de rutinas
            GenericGestor<Rutina> rutinas = new GenericGestor<>();
            try {
                rutinas.actualizarGestor("rutinas");
            } catch (Exception e) {
                // Continuar si no hay rutinas
            }
            
            MenuProfesor menuProfesor = new MenuProfesor(sc, usuarios, rutinas, profesor);
            menuProfesor.show();
        } else if (user instanceof ClienteTemporal) {
            System.out.println("Bienvenido Cliente Temporal: " + user.getNombre());
            Cliente cliente = (Cliente) user;
            MenuCliente menuCliente = new MenuCliente(
                cliente.getNombre(),
                cliente.isCuotaAlDia(),
                sc
            );
            menuCliente.show();
        } else if (user instanceof Cliente) {
            System.out.println("Bienvenido Cliente: " + user.getNombre());
            Cliente cliente = (Cliente) user;
            MenuCliente menuCliente = new MenuCliente(
                cliente.getNombre(),
                cliente.isCuotaAlDia(),
                sc
            );
            menuCliente.show();
        } else {
            System.out.println("Tipo de usuario no reconocido");
        }
    }

    private User autenticar(String usuario, String clave) {
        if (usuarios == null || usuarios.getInventario() == null) {
            System.out.println("Error: Lista de usuarios sin inicializar");
            return null;
        }

        if (usuario == null || usuario.isBlank() || clave == null || clave.isBlank()) {
            System.out.println("Usuario y contraseña no pueden estar vacíos");
            return null;
        }

        for (User u : usuarios.getInventario()) {
            if (Objects.equals(u.getUser(), usuario) && 
                Objects.equals(u.getPass(), clave) && 
                u.isActive()) {
                return u;
            }
        }

        return null;
    }
}

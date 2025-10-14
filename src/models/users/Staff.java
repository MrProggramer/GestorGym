package models.users;

import exceptions.Exc_Gestor;
import gestores.GenericGestor;
import models.rutinas.Rutina;
import java.util.Scanner;

public class Staff extends User  {
    private String usuario;
    private String contrasenia;
    private boolean isAdmin;    //en vez de enums, podemos usar isAdmin y/o instanceof para acceder a ciertos métodos
    private final GenericGestor<Rutina> rutinas;


    /// ----- Constructores -----
    public Staff(String nombre, String dni, String mail, String telefono, String usuario, String contrasenia, String user, String pass, boolean isAdmin, GenericGestor<Rutina> rutinas) {
        super(nombre, dni, mail, telefono, user, pass);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.isAdmin = isAdmin;
        this.rutinas = rutinas;
    }

    public Staff() {
        super();
        this.usuario = "";
        this.contrasenia = "";
        this.isAdmin = false;
        this.rutinas = null;
    }

    public Staff(GenericGestor<Rutina> rutinas) {
        this.rutinas = rutinas;
    }

    /// ----- Alta -----
    public void altaRutina(Rutina rutina) throws Exc_Gestor {
        rutinas.altaItem(rutina);
    }

    /// ----- Baja -----
    public void bajaRutina(int idRutina) throws Exc_Gestor {
        rutinas.bajaItem(idRutina);
    }

    /// ----- Modificar -----
    public void modificarRutina(Rutina rutina) throws Exc_Gestor {
        rutinas.modificarItem(rutina);
    }

    /// ----- Buscar -----
    public Rutina buscarRutina(int idRutina) throws Exc_Gestor {
        return rutinas.buscarItem(idRutina);
    }

    /// ----- Asignar -----
    public void asignarRutina(Cliente cliente, int idRutina) throws Exc_Gestor {
        Rutina rutina = rutinas.buscarItem(idRutina);
        cliente.setRutina(rutina);
    }


    /// -----  Crear user Staff -----
    public User crear(Scanner sc) {
        User aux = null;

        System.out.println("Ingresa tu nombre completo");
        aux.setNombre(sc.nextLine());
        System.out.println("Ingresa tu dni");
        aux.setDni(sc.nextLine());
        System.out.println("Ingresa tu mail");
        aux.setMail(sc.nextLine());
        System.out.println("Ingresa tu telefono");
        aux.setTelefono(sc.nextLine());
        System.out.println("Ingresa el usuario que queres tener");
        aux.setUser(sc.nextLine());
        System.out.println("Ingresa la clave que queres tener");
        aux.setPass(sc.nextLine());

        return aux;
    }


    /// ----- Getter&Setter -----
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}

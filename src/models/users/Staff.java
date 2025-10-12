package models.users;

import enums.TipoRol;
import gestores.GenericGestor;
import models.rutinas.Rutina;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Staff extends User  {
    private String usuario;
    private String contrasenia;
    private boolean isAdmin;    //en vez de enums, podemos usar isAdmin y/o instanceof para acceder a ciertos métodos
    private ArrayList<ArrayList<Rutina>> listaRutinas;
    private GenericGestor<Rutina> rutinas;

    // Constructor
    public Staff(String nombre, String dni, String mail, String telefono, String usuario, String contrasenia, String user, String pass, boolean isAdmin, GenericGestor rutinas) {
        super(nombre, dni, mail, telefono, user, pass);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.isAdmin = isAdmin;
        this.rutinas = rutinas;
    }

    // metodo asignar turina
    public void asignarRutina (Cliente cliente, int opcion){
        cliente.setRutina(rutinas.buscarItem(opcion));
    }

    /* Deberian ir estos metodos en cliente o staff? Solo puede acceder el entrenador
     * Por ahora, los dejo acá */
    public void asignarRutina(ArrayList<Rutina> rutina) {
        listaRutinas.add(rutina);
    }
    public void eliminarRutina(int idRutina) {
        ListIterator<ArrayList<Rutina>> iterador = getListaRutinas().listIterator();

        while(iterador.hasNext()) {
            if(iterador.next().equals(idRutina)) {
                listaRutinas.remove(iterador);
            }
        }
    }

    // crear user Staff

    @Override
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

    //Getter&Setter
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

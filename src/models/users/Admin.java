package models.users;

import gestores.GenericGestor;
import models.rutinas.Rutina;

import java.util.Scanner;

public class Admin extends User  {
    private GenericGestor<Rutina> rutinas;
    private GenericGestor<User> users;

    // Constructor
    public Admin(String nombre, String dni, String mail, String telefono, String user, String pass, boolean isAdmin) {
        super(nombre, dni, mail, telefono, user, pass);
        this.rutinas.actualizarGestor("rutinas");
        this.users.actualizarGestor("users");
    }
    public Admin(GenericGestor<User> usuarios, GenericGestor<Rutina> rutinas){
        this.rutinas = rutinas;
        this.users = usuarios;
    }
    public Admin(){
        this.rutinas.actualizarGestor("rutinas");
        this.users.actualizarGestor("users");
    }


    // crear user Admin

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


    public GenericGestor<User> getUsers() {
        return users;
    }

    public void setUsers(GenericGestor<User> users) {
        this.users = users;
    }

    public GenericGestor<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(GenericGestor<Rutina> rutinas) {
        this.rutinas = rutinas;
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                '}';
    }



}

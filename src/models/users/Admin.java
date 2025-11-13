package models.users;

import gestores.GenericGestor;
import models.rutinas.Rutina;

import java.util.Scanner;

public class Admin extends User  {
    private GenericGestor<Rutina> rutinas = new GenericGestor<>();
    private GenericGestor<User> users = new GenericGestor<>();

    // Constructor
    public Admin(String nombre, String dni, String mail, String telefono, String user, String pass, boolean isAdmin) {
        super(nombre, dni, mail, telefono, user, pass);
        this.rutinas.actualizarGestor("rutinas");
        this.users.actualizarGestor("users");
    }

    public Admin() {

    }

    // crear user Admin

    @Override
    public User crear(Scanner sc) {
        return null;
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

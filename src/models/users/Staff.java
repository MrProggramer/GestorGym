package models.users;

import gestores.GenericGestor;
import models.rutinas.Rutina;

import java.util.Scanner;

public class Staff extends User  {
    private GenericGestor<Rutina> rutinas;

    // Constructor
    public Staff(String nombre, String dni, String mail, String telefono, String user, String pass, boolean isAdmin, GenericGestor rutinas) {
        super(nombre, dni, mail, telefono, user, pass);
        this.rutinas = rutinas;
    }
    public Staff(){}

    // metodo asignar turina
    public void asignarRutina (Cliente cliente, int opcion){
       // cliente.setRutina(rutinas.buscarItem(opcion));
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


    public GenericGestor<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(GenericGestor<Rutina> rutinas) {
        this.rutinas = rutinas;
    }

    @Override
    public String toString() {
        return "Staff{" +
                super.toString() +
                '}';
    }



}

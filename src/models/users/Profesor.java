package models.users;

import gestores.GenericGestor;
import models.rutinas.Rutina;

import java.util.Scanner;

public class Profesor extends User{
    private GenericGestor<Rutina> rutinas;

    public Profesor(String nombre, String dni, String mail, String telefono, String user, String pass, GenericGestor<Rutina> rutinas) {
        super(nombre, dni, mail, telefono, user, pass);
        this.rutinas = rutinas;
    }
    public Profesor(GenericGestor<Rutina> rutinas){
        this.rutinas = rutinas;
    }

    public GenericGestor<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(GenericGestor<Rutina> rutinas) {
        this.rutinas = rutinas;
    }

    @Override
    public User crear(Scanner sc) {
        return null;
    }
    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return "Profesor{" +
                super.toString() +
                "rutinas=" + rutinas +
                '}';
    }
}

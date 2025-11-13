package models;

import gestores.GenericGestor;
import gui.GestorEscenas;
import javafx.application.Application;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.User;

import java.util.Scanner;

public class App {
    public void inicializar() {
        Scanner sc = new Scanner(System.in);
        GenericGestor<Rutina> rutinas = new GenericGestor<>();
        rutinas.actualizarGestor("rutinas");
        GenericGestor<Ejercicio> ejercicios = new GenericGestor<>();
        ejercicios.actualizarGestor("ejercicios");
        GenericGestor<User> usuarios = new GenericGestor<>();
        usuarios.actualizarGestor("users");
        GestorEscenas.setGestores(usuarios, rutinas, ejercicios);
        Application.launch(GestorEscenas.class);



        finalizar(rutinas, ejercicios, usuarios);
    }


public void  finalizar(GenericGestor<Rutina> rutina ,GenericGestor<Ejercicio> ejercicio, GenericGestor<User> user) {

}
}

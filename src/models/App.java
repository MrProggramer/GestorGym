package models;

import Exceptions.GestorOperationException;
import Exceptions.UnauthorizedActionException;
import gestores.GenericGestor;
import gui.GestorEscenas;
import javafx.application.Application;
import models.database.ControlData;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.Cliente;
import models.users.User;
import models.utils.Utilidades;

import java.util.Scanner;

public class App {
    public void inicializar() { //Esta funcion retorna errores de JSON. Revisar

        Scanner sc = new Scanner(System.in);

        GenericGestor<Rutina> rutinas = new GenericGestor<>();
        rutinas.actualizarGestor("rutinas");

        GenericGestor<Ejercicio> ejercicios = new GenericGestor<>();
        ejercicios.actualizarGestor("ejercicios");

        GenericGestor<User> usuarios = new GenericGestor<>();
        usuarios.actualizarGestor("users");


        User test = usuarios.buscarItem(99);
        if(test instanceof Cliente a){
            for(Ejercicio e: a.getRutina().getListaEjercicios()){
                System.out.println(e);
            }
        }



        GestorEscenas.setGestores(usuarios, rutinas, ejercicios);
        Application.launch(GestorEscenas.class);

    }

}

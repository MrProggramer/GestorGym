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

        User pepe = new Cliente("gonazlo", "pap", "asdas@asd", "asdasd", "GRam", "pass", true, 30);
        usuarios.altaItem(pepe);


        for(User e : usuarios.getInventario()){
            System.out.println(e.getUser());
            System.out.println(e.getPass());
            System.out.println("--------------------");
        }


        GestorEscenas.setUsuarios(usuarios);
        Application.launch(GestorEscenas.class);

    }

}

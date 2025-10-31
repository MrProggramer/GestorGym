package models;

import gestores.GenericGestor;
import gui.GestorEscenas;
import javafx.application.Application;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.User;

public class App {
    public void inicializar(){ //Esta funcion retorna errores de JSON. Revisar

        GenericGestor<Rutina> rutinas = new GenericGestor<>();
        rutinas.actualizarGestor("rutinas");

        GenericGestor<Ejercicio> ejercicios = new GenericGestor<>();
        ejercicios.actualizarGestor("ejercicios");

        GenericGestor<User> usuarios = new GenericGestor<>();
        usuarios.actualizarGestor("users");
        System.out.println(usuarios);

        GestorEscenas.setUsuarios(usuarios);
        Application.launch(GestorEscenas.class);

    }


}

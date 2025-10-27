package models;

import gestores.GenericGestor;
import gestores.GestorUsers;
import gestores.Login;
import gui.GestorEscenas;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.User;

public class App {
    public void inicializar(){

        GenericGestor<Rutina> rutinas = new GenericGestor<>();
        rutinas.actualizarGestor("rutinas");

        GenericGestor<Ejercicio> ejercicios = new GenericGestor<>();
        ejercicios.actualizarGestor("ejercicios");

        GenericGestor<User> usuarios = new GenericGestor<>();
        usuarios.actualizarGestor("users");

        //Login login = new Login(usuarios);
        //GestorEscenas gestor_escenas = new GestorEscenas(usuarios);
    }


}

package gui.controller;

import gestores.GenericGestor;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.User;

public abstract class BaseUserController {
    protected GenericGestor<User> usuarios;
    protected GenericGestor<Rutina> rutinas;
    protected GenericGestor<Ejercicio> ejercicios;
    protected User user_actual;

    public void setGestores(GenericGestor<User> u, GenericGestor<Rutina> r, GenericGestor<Ejercicio> e, User logged) {
        this.usuarios = u;
        this.rutinas = r;
        this.ejercicios = e;
        this.user_actual = logged;

        inicializarVista();
    }

    protected abstract void inicializarVista();
}

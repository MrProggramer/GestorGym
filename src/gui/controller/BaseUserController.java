package gui.controller;

import gestores.GenericGestor;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;
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

    public void mostrarMensaje(String msg, javafx.scene.paint.Color color, Label lb_status) {
        lb_status.setTextFill(color);
        lb_status.setText(msg);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> lb_status.setText(""));
        pause.play();
    }
}

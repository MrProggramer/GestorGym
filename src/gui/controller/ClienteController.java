package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import models.rutinas.Ejercicio;
import models.users.Cliente;


import java.net.URL;
import java.util.ResourceBundle;

public class ClienteController extends BaseUserController implements Initializable {
    @FXML private ListView<Ejercicio> lv_ejercicios;

    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    @Override
    protected void inicializarVista() {
        cargarRutinas();
    }

    private void cargarRutinas() {
        Cliente cliente = (Cliente) this.user_actual;

        lv_ejercicios.getItems().clear(); //limpiar el visor
        lv_ejercicios.getItems().setAll(cliente.getRutina().getListaEjercicios());
    }

}

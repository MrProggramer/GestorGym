package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.rutinas.Rutina;


import java.net.URL;
import java.util.ResourceBundle;

public class ClienteController extends BaseUserController implements Initializable {
    @FXML private ListView<Rutina> lv_rutinas;
    @FXML private Label lb_nombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    protected void inicializarVista() {
        lb_nombre.setText("Cliente: " + user_actual.getNombre());
        cargarRutinas();
    }

    private void cargarRutinas() {
        lv_rutinas.getItems().clear(); //limpiar el visor

        /** Acá habría que mostrar las rutinas asignadas al cliente, llamando a un arreglo con los ids o lo que usemos */
    }

}

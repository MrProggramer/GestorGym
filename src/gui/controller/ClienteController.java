package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import models.rutinas.Rutina;

import javax.swing.text.html.ListView;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ClienteController extends BaseUserController implements Initializable {
    @FXML private ListView lv_rutinas;
    @FXML private Label lb_nombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) { }

    @Override
    protected void inicializarVista() {
        lb_nombre.setText("Cliente: " + user_actual.getNombre());
        cargarRutinas();
    }

    private void cargarRutinas() {
        //lv_rutinas.getI
    }

}

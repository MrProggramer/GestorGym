package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.rutinas.Rutina;
import models.users.Cliente;
import models.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfesorController extends BaseUserController implements Initializable {
    @FXML private ComboBox<User> cb_clientes;
    @FXML private ListView<Rutina> lv_rutinas_disponibles;
    @FXML private Button btn_asignar;
    @FXML private Label lb_nombre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    protected void inicializarVista() {
        lb_nombre.setText("Profesor: " + user_actual.getNombre());
        cargarAlumnos();
        cargarRutinas();
    }

    private void cargarAlumnos() {
        cb_clientes.getItems().clear(); //limpiar visor
        for(User u : usuarios.getInventario()) {
            if(u instanceof Cliente) {
                cb_clientes.getItems().add(u);
            }
        }
    }

    private void cargarRutinas() {
        lv_rutinas_disponibles.getItems().setAll(rutinas.getInventario());
    }

    @FXML
    private void asignarRutina() {
        User user_selecc = cb_clientes.getValue();
        Rutina rutina_selecc = lv_rutinas_disponibles.getSelectionModel().getSelectedItem();

        if(user_selecc == null || rutina_selecc == null) return;

        if(user_selecc instanceof Cliente cliente) {
            cliente.setRutina(rutina_selecc);
            usuarios.actualizarGestor("users");
            //Mostrar mensaje temporal
        } /* else mostrando mensaje temporal con error */

    }
}

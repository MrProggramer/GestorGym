package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import models.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController extends BaseUserController implements Initializable {
    @FXML private TableView<User> tbl_users;
    @FXML private Button btn_add_user;
    @FXML private Button btn_del_user;
    @FXML private Label lb_nombre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    protected void inicializarVista() {
        lb_nombre.setText("Administrador: " + user_actual.getNombre());
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        tbl_users.getItems().setAll(usuarios.getInventario());
    }

    @FXML
    private void agregarUsuario() {
        // por hacer
    }

    @FXML void eliminarUsuario() {
        User seleccionado = tbl_users.getSelectionModel().getSelectedItem();
        if(seleccionado != null) {
            usuarios.bajaItem(seleccionado.getId());
            usuarios.actualizarGestor("users");
            cargarUsuarios();
        }
    }


}

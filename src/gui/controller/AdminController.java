package gui.controller;

import enums.TipoGrupoMuscular;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import models.database.ControlData;
import models.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController extends ProfesorController implements Initializable {
    @FXML private ListView<User> lv_usuarios;
    @FXML private Button btn_add_user;
    @FXML private Button btn_del_user;
    @FXML private Label lb_status;

    @FXML private TextField tf_u_id;
    @FXML private TextField tf_u_nombre;
    @FXML private TextField tf_u_user;
    @FXML private TextField tf_u_pass;
    @FXML private TextField tf_u_mail;
    @FXML private TextField tf_u_dni;
    @FXML private TextField tf_u_tlf;
    @FXML private ChoiceBox<TipoGrupoMuscular> cb_u_tipo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    protected void inicializarVista() {
        super.inicializarVista();
        cargarPanelAdmin();
    }

    private void cargarPanelAdmin() {
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        lv_usuarios.getItems().clear();
        lv_usuarios.getItems().setAll(this.usuarios.getInventario());

        lv_usuarios.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                setText(empty || user == null ? null : user.getNombre());
            }
        });
    }

    @FXML
    private void registrarNuevoUser() {

    }

    @FXML
    private void bajarUsuario() {
        User u = lv_usuarios.getSelectionModel().getSelectedItem();
        if(u != null) {
            this.usuarios.bajaItem(u.getId());
            ControlData.guardarData(this.usuarios, "users");
            this.usuarios.actualizarGestor("users");
            mostrarMensaje("Usuario eliminado correctamente", Color.GREEN, lb_status);
            cargarUsuarios();
        }
    }

    @FXML
    private void actualizarUsuario() {

    }

}

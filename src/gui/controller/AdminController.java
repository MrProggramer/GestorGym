package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import models.database.ControlData;
import models.users.Admin;
import models.users.Cliente;
import models.users.Profesor;
import models.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController extends ProfesorController implements Initializable {
    @FXML
    private ListView<User> lv_usuarios;
    @FXML
    private Button btn_add_user;
    @FXML
    private Button btn_del_user;
    @FXML
    private Label lb_status;

    @FXML
    private TextField tf_u_id;
    @FXML
    private TextField tf_u_nombre;
    @FXML
    private TextField tf_u_user;
    @FXML
    private TextField tf_u_pass;
    @FXML
    private TextField tf_u_mail;
    @FXML
    private TextField tf_u_dni;
    @FXML
    private TextField tf_u_tlf;
    @FXML
    private ChoiceBox<String> cb_u_tipo;
    @FXML
    private Button btn_admin_borrar_rutina;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_admin_borrar_rutina.setOnAction(mouseEvent -> {
            this.borrarRutinaReal();
        });
    }

    @Override
    protected void inicializarVista() {
        super.inicializarVista();
        cargarPanelAdmin();
    }

    private void cargarPanelAdmin() {
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        cb_u_tipo.getItems().setAll("Profesor", "Cliente", "Admin");
        cb_u_tipo.setValue("Tipo");

        lv_usuarios.getItems().clear();

        //  solo los usuarios activos
        for (User u : this.usuarios.getInventario()) {
            if (u.isActive()) {
                lv_usuarios.getItems().add(u);
            }
        }

        lv_usuarios.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                setText(empty || user == null ? null : user.getNombre());
            }
        });

        lv_usuarios.getSelectionModel().selectedItemProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                mostrarDatosUser(selected);
            } else {
                limpiarCampos();
            }
        });
    }

    private void mostrarDatosUser(User u) {
        if (u == null) return;

        tf_u_id.setText(String.valueOf(u.getId()));
        tf_u_nombre.setText(u.getNombre());
        tf_u_user.setText(u.getUser());
        tf_u_pass.setText(u.getPass());
        tf_u_mail.setText(u.getMail());
        tf_u_dni.setText(u.getDni());
        tf_u_tlf.setText(u.getTelefono());
        if (u instanceof Cliente) {
            cb_u_tipo.setValue("Cliente");
        } else if (u instanceof Profesor) {
            cb_u_tipo.setValue("Profesor");
        } else if (u instanceof Admin) {
            cb_u_tipo.setValue("Admin");
        } else {
            mostrarMensaje("Error de tipos", Color.RED, lb_status);
        }
    }

    private void limpiarCampos() {
        tf_u_id.clear();
        tf_u_nombre.clear();
        tf_u_user.clear();
        tf_u_pass.clear();
        tf_u_mail.clear();
        tf_u_dni.clear();
        tf_u_tlf.clear();
        cb_u_tipo.setValue(null);
    }

    @FXML
    private void registrarNuevoUser() {
        User user_selecc = lv_usuarios.getSelectionModel().getSelectedItem();

        if (user_selecc == null) {
            mostrarMensaje("Seleccione un usuario primero", Color.RED, lb_status);
        }

        String nombre = tf_u_nombre.getText().trim();
        String user = tf_u_user.getText().trim();
        String pass = tf_u_pass.getText().trim();
        String mail = tf_u_mail.getText().trim();
        String dni = tf_u_dni.getText().trim();
        String tlf = tf_u_tlf.getText().trim();
        String rol = cb_u_tipo.getValue();

        if (nombre.isBlank() || user.isBlank() || pass.isBlank() || mail.isBlank() || dni.isBlank() || tlf.isBlank()) {
            mostrarMensaje("Uno de los campos está vacío", Color.RED, lb_status);
            return;
        }
        if (rol == null || rol.equals("Tipo")) {
            mostrarMensaje("Seleccionar un tipo de usuario", Color.RED, lb_status);
            return;
        }

        User nuevo = null;
        if (rol.equals("Profesor")) {
            nuevo = new Profesor();
            nuevo.setActive(true);
            nuevo.setNombre(nombre);
            nuevo.setDni(dni);
            nuevo.setMail(mail);
            nuevo.setTelefono(tlf);
            nuevo.setUser(user);
            nuevo.setPass(pass);
        } else if (rol.equals("Cliente")) {
            nuevo = new Cliente(nombre, dni, mail, tlf, user, pass, true, 30);
        } else if (rol.equals("Admin")) {
            nuevo = new Admin(nombre, dni, mail, tlf, user, pass, true);
        } else {
            mostrarMensaje("Tipo de Rol inválido", Color.RED, lb_status);
            return;
        }

        usuarios.altaItem(nuevo);
        ControlData.guardarData(usuarios, "users");  //Comentado por ahora ya que reformatea todo el json
        usuarios.actualizarGestor("users");
        lv_usuarios.getItems().add(nuevo);
        mostrarMensaje("Usuario creado correctamente", Color.GREEN, lb_status);
        System.out.println("Usuario agregado: " + nuevo);
    }

    @FXML
    private void bajarUsuario() {
        User u = lv_usuarios.getSelectionModel().getSelectedItem();
        if (u != null) {
            this.usuarios.bajaItem(u.getId());
            ControlData.guardarData(this.usuarios, "users");
            this.usuarios.actualizarGestor("users");
            mostrarMensaje("Usuario eliminado correctamente", Color.GREEN, lb_status);
            cargarUsuarios();
        }
    }

    @FXML
    private void actualizarUsuario() {
        User user_selecc = lv_usuarios.getSelectionModel().getSelectedItem();

        if (user_selecc == null) {
            mostrarMensaje("Seleccione un usuario para actualizar", Color.RED, lb_status);
            return;
        }

        String nombre = tf_u_nombre.getText().trim();
        String user = tf_u_user.getText().trim();
        String pass = tf_u_pass.getText().trim();
        String mail = tf_u_mail.getText().trim();
        String dni = tf_u_dni.getText().trim();
        String tlf = tf_u_tlf.getText().trim();
        String rol = cb_u_tipo.getValue();

        if (nombre.isBlank() || user.isBlank() || pass.isBlank() || mail.isBlank() || dni.isBlank() || tlf.isBlank()) {
            mostrarMensaje("Uno de los campos está vacío", Color.RED, lb_status);
            return;
        }
        if (rol == null || rol.equals("Tipo")) {
            mostrarMensaje("Seleccione un tipo de usuario válido", Color.RED, lb_status);
            return;
        }

        user_selecc.setNombre(nombre);
        user_selecc.setUser(user);
        user_selecc.setPass(pass);
        user_selecc.setMail(mail);
        user_selecc.setDni(dni);
        user_selecc.setTelefono(tlf);

        if (!tipoCoincide(user_selecc, rol)) {
            // reemplazar el usuario por una nueva instancia del tipo correcto
            User nuevo = switch (rol) {
                case "Profesor" -> {
                    Profesor p = new Profesor();
                    p.setActive(true);
                    p.setNombre(nombre);
                    p.setDni(dni);
                    p.setMail(mail);
                    p.setTelefono(tlf);
                    p.setUser(user);
                    p.setPass(pass);
                    yield p;
                }
                case "Cliente" -> new Cliente(nombre, dni, mail, tlf, user, pass, true, 30);
                case "Admin" -> new Admin(nombre, dni, mail, tlf, user, pass, true);
                default -> null;
            };

            if (nuevo == null) {
                mostrarMensaje("Tipo de Rol inválido", Color.RED, lb_status);
                return;
            }

            nuevo.setId(user_selecc.getId());
            usuarios.bajaItem(user_selecc.getId());
            usuarios.altaItem(nuevo);
            int index = lv_usuarios.getItems().indexOf(user_selecc);
            lv_usuarios.getItems().set(index, nuevo);
        } else {
            lv_usuarios.refresh();
        }

        ControlData.guardarData(usuarios, "users");
        usuarios.actualizarGestor("users");
        mostrarMensaje("Usuario actualizado correctamente", Color.GREEN, lb_status);
    }

    private boolean tipoCoincide(User u, String rol) {
        return (u instanceof Profesor && rol.equals("Profesor")) ||
                (u instanceof Cliente && rol.equals("Cliente")) ||
                (u instanceof Admin && rol.equals("Admin"));
    }
}
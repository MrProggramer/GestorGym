package gui.controller;

import gestores.GenericGestor;
import gui.GestorEscenas;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.database.ControlData;
import models.users.Cliente;
import models.users.Profesor;
import models.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private double x = 0, y = 0;
    private Stage stage;
    private GenericGestor<User> usuarios;

    @FXML
    private AnchorPane panel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField dniField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField tlfField;
    @FXML
    private Button quitButton;
    @FXML
    private ChoiceBox<String> userTypeChoiceBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTypeChoiceBox.getItems().addAll("Profesor", "Cliente");
        userTypeChoiceBox.setValue("Tipo");

        panel.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getScreenX() - stage.getX();
            y = mouseEvent.getScreenY() - stage.getY();
        });
        panel.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
        quitButton.setOnAction(mouseEvent -> {
            stage.close(); //cerrar hilos primero
        });

        registerButton.setOnAction(e -> registrarUsuario());
        backButton.setOnAction(e -> volverALogin());
    }

    private void registrarUsuario() {
        String nombre = nameField.getText();
        String usuario = userField.getText();
        String pass = passField.getText();
        String dni = dniField.getText();
        String mail = mailField.getText();
        String tlf = tlfField.getText();
        String tipo_usuario = userTypeChoiceBox.getValue();

        if (nombre.isBlank() || usuario.isBlank() || pass.isBlank() || dni.isBlank() || mail.isBlank() || tlf.isBlank() ) {
            showMensajeTemporal("Completar todos los campos", Color.RED);
            return;
        }
        if (tipo_usuario == null || tipo_usuario.equals("Tipo")) {
            showMensajeTemporal("Seleccionar un tipo de usuario", Color.RED);
            return;
        }

        for (User u : usuarios.getInventario()) {
            if (u.getUser().equals(usuario)) {
                showMensajeTemporal("Usuario ya existente", Color.RED);
                return;
            }
        }

        User nuevo;
        if (tipo_usuario.equals("Profesor")) {
            //nuevo = new Profesor(nombre, dni, mail, tlf, usuario, pass, null);
        } else {
            nuevo = new Cliente(nombre, dni, mail, tlf, usuario, pass, true, 30);
        }

        //usuarios.altaItem(nuevo);
        // ControlData.guardarData(usuarios, "users");  //Comentado por ahora ya que reformatea todo el json
        //usuarios.actualizarGestor("users");

        showMensajeTemporal("Usuario creado correctamente", Color.GREEN);
        System.out.println("Usuario agregado: " + usuario);
    }

    private void showMensajeTemporal(String msg, Color color) {
        statusLabel.setTextFill(color);
        statusLabel.setText(msg);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> statusLabel.setText(""));
        pause.play();
    }

    private void volverALogin() {
        try {
            GestorEscenas.showLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //GS inyeccion
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsuarios(GenericGestor<User> usuarios) {
        this.usuarios = usuarios;
    }

}

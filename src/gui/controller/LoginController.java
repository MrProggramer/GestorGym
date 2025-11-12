package gui.controller;

import Exceptions.UserNotFoundException;
import gestores.GenericGestor;
import gui.GestorEscenas;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;
import models.users.User;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private double x = 0, y = 0;
    private Stage stage;
    private GenericGestor<User> usuarios;

    public LoginController() {
    }

    @FXML
    private AnchorPane panel;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button quitButton;
    @FXML
    private Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panel.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getScreenX() - stage.getX();
            y = mouseEvent.getScreenY() - stage.getY();
        });

        panel.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        loginButton.setOnAction( event -> {
            String username = userField.getText();
            String password = passField.getText();

            System.out.println(username);
            System.out.println(password);

            //Metodo que envia al programa principal
            try {
                GestorEscenas.showSession(autenticar(username, password));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

        passField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                String username = userField.getText();
                String password = passField.getText();

                System.out.println(username);
                System.out.println(password);

                //Metodo que envia al programa principal
                try {
                    GestorEscenas.showSession(autenticar(username, password));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        registerButton.setOnAction(mouseEvent -> {
            //Enviar a menu registrar
            try {
                GestorEscenas.showRegistro();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        quitButton.setOnAction(mouseEvent -> {
            stage.close(); //cerrar hilos primero
        });
    }

    public User autenticar(String user, String pass) throws UserNotFoundException, IllegalStateException, IllegalArgumentException {
        if(usuarios == null || usuarios.getInventario() == null)
            throw new IllegalStateException("Lista de usuarios sin inicializar");
        if(user == null || user.isBlank() || pass == null || pass.isBlank())
            throw new IllegalArgumentException("Usuario y contraseña no pueden estar vacios");


        for (User u : usuarios.getInventario()) {
            if (Objects.equals(u.getUser(), user) && Objects.equals(u.getPass(), pass) && u.isActive()) {
                return u;
            }
        }
        showMensajeTemporal("Usuario o contraseña incorrectos", Color.RED);
        throw new UserNotFoundException("Usuario o contraseña incorrectos");
    }

    private void showMensajeTemporal(String msg, Color color) {
        statusLabel.setTextFill(color);
        statusLabel.setText(msg);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> statusLabel.setText(""));
        pause.play();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void closeProgram(ActionEvent event) {
        stage.close();
    }

    public GenericGestor<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(GenericGestor<User> usuarios) {
        this.usuarios = usuarios;
    }
}

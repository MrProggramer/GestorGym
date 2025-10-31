package gui.controller;

import gestores.GenericGestor;
import gui.GestorEscenas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SessionController implements Initializable {
    private double x = 0, y = 0;
    private Stage stage;
    private GenericGestor<User> usuarios;

    @FXML
    private AnchorPane panel;
    @FXML
    private Button quitButton;
    @FXML
    private Button backButton;

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
        quitButton.setOnAction(mouseEvent -> {
            stage.close(); //cerrar hilos primero
        });
        backButton.setOnAction(e -> volverALogin());




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

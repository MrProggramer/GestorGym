package gui.controller;

import gestores.GestorLogin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private GestorLogin gestor;
    private double x = 0, y = 0;
    private Stage stage;

    // fx:id
    @FXML
    private AnchorPane panel;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button loginButton;

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

             gestor.autenticar(username, password); // Al recibir el User si es verdadero, se lo debera enviar a una clase Session que corra el programa principal con ese usuario
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void closeProgram(ActionEvent event) {
        stage.close();
    }
}

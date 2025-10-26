package gui.controller;

import gestores.GenericGestor;
import gestores.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import models.users.Cliente;
import models.users.User;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Login implements Initializable {
    private double x = 0, y = 0;
    private Stage stage;


    public LoginController(GenericGestor<User> usuarios) {
        super(usuarios);
    }


    // fx:id
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

            User user1 = new Cliente("Pedro", "40123123", "mail@mail.com", "2231231234", "pedrito12", "passw", true, 31);
            this.getUsuarios().altaItem(user1);

            System.out.println(username);
            System.out.println(password);

            //gestor.autenticar(username, password); // Al recibir el User si es verdadero, se lo debera enviar a una clase Session que corra el programa principal con ese usuario
            this.autenticar(username, password);
        });

        registerButton.setOnAction(mouseEvent -> {
            //Enviar a menu registrar
        });

        quitButton.setOnAction(mouseEvent -> {
            stage.close(); //cerrar hilos primero
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

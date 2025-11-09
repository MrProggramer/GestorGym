package gui.controller;

import gestores.GenericGestor;
import gui.GestorEscenas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.Admin;
import models.users.Cliente;
import models.users.Profesor;
import models.users.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SessionController implements Initializable {
    private double x = 0, y = 0;
    private Stage stage;

    private User user;
    private GenericGestor<User> usuarios;
    private GenericGestor<Rutina> rutinas;
    private GenericGestor<Ejercicio> ejercicios;

    @FXML   private AnchorPane panel;
    @FXML   private Button quitButton;
    @FXML   private Button backButton;
    @FXML   private StackPane contentPane;
    @FXML   private Label welcomeLabel;

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

    public void setUsuario(User u) {
        this.user = u;
        welcomeLabel.setText("Bienvenido, " + user.getNombre());
        cargarVistaPorTipo();
    }

    private void cargarVistaPorTipo() throws RuntimeException { //dependiendo la instancia de la clase, cargar un fxml u otro
        String fxml;
        if(user instanceof Cliente)
            fxml = "/gui/fxml/ClienteSession.fxml";
//        else if(user instanceof Profesor)
//            fxml = "/gui/fxml/ProfesorSession.fxml";
        else if(user instanceof Admin)
            fxml = "/gui/fxml/AdminSession.fxml";
        else
            throw new RuntimeException("Usuario no válido");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent vista = loader.load();

            //inyectar los gestores en el sub controlador
            Object controller = loader.getController();
            if(controller instanceof ClienteController c)
                c.setGestores(usuarios, rutinas, ejercicios, user);
            else if(controller instanceof ProfesorController p)
                p.setGestores(usuarios, rutinas, ejercicios, user);
            else if(controller instanceof AdminController a)
                a.setGestores(usuarios, rutinas, ejercicios, user);

            contentPane.getChildren().setAll(vista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void volverALogin() {
        try {
            GestorEscenas.showLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //GS inyeccion
    public void setStage(Stage stage) { this.stage = stage; }
    public void setUsuarios(GenericGestor<User> usuarios) { this.usuarios = usuarios; }

    public void setGestores(GenericGestor<User> u, GenericGestor<Rutina> r, GenericGestor<Ejercicio> e) {
        this.usuarios = u;
        this.rutinas = r;
        this.ejercicios = e;
    }


}

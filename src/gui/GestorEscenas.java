package gui;

import gestores.GenericGestor;
import gui.controller.LoginController;
import gui.controller.RegisterController;
import gui.controller.SessionController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.rutinas.Ejercicio;
import models.rutinas.Rutina;
import models.users.User;

public class GestorEscenas extends Application {
    private static Stage primaryStage;
    private static GenericGestor<User> usuarios = new GenericGestor<>();
    private static GenericGestor<Rutina> rutinas = new GenericGestor<>();
    private static GenericGestor<Ejercicio> ejercicios = new GenericGestor<>();

    public GestorEscenas() {}

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        showLogin();
    }

    public static void showLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(GestorEscenas.class.getResource("/gui/fxml/Login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setUsuarios(usuarios);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        controller.setStage(primaryStage);
        primaryStage.show();
    }

    public static void showRegistro() throws Exception {
        FXMLLoader loader = new FXMLLoader(GestorEscenas.class.getResource("/gui/fxml/Register.fxml"));
        Parent root = loader.load();
        RegisterController controller = loader.getController();
        controller.setUsuarios(usuarios);
        controller.setStage(primaryStage);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Registro de Usuario");
        primaryStage.show();
    }

    public static void showSession(User user_logged) throws Exception {
        FXMLLoader loader = new FXMLLoader(GestorEscenas.class.getResource("/gui/fxml/Session.fxml"));
        Parent root = loader.load();
        SessionController controller = loader.getController();
        controller.setSesion(user_logged, usuarios, rutinas, ejercicios);
        controller.setStage(primaryStage);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Panel principal");
        primaryStage.show();
    }


    public static void setGestores(GenericGestor<User> u, GenericGestor<Rutina> r, GenericGestor<Ejercicio> e) {
        usuarios = u;
        rutinas = r;
        ejercicios = e;
    }

}

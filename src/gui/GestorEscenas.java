package gui;

import gestores.GenericGestor;
import gui.controller.LoginController;
import gui.controller.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.users.User;

public class GestorEscenas extends Application {
    private static Stage primaryStage;
    private static GenericGestor<User> usuarios = new GenericGestor<>();

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

    public static void setUsuarios(GenericGestor<User> u) {
        usuarios = u;
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

}

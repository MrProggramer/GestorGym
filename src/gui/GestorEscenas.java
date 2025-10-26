package gui;

import gui.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GestorEscenas extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showLogin();
    }

    public static void showLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(GestorEscenas.class.getResource("/gui/fxml/Login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        Scene scene = new Scene(root);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        controller.setStage(primaryStage);
        primaryStage.show();
    }

}

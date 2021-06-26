package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage home;
    @Override
    public void start(Stage primaryStage) throws Exception{
        home = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        home.setTitle("HiLoDa 2.0");
        home.setScene(new Scene(root, 800, 550));
        home.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

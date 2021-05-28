package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage home ;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        home = new Stage();
        home.setTitle("Graph Algorithms");
        home.setScene(new Scene(root, 1035, 600));
        home.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

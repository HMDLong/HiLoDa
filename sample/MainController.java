package sample;

import javafx.fxml.FXML;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    //connect to javafx components
    @FXML
    private JFXRadioButton dButton, udButton;

    private static boolean isDirected = false, isUndirected = false;
    private Stage menu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        dButton.setSelected(false);
        udButton.setSelected((false));
        dButton.setOnAction(e -> {
            isDirected = true;
            isUndirected = false;
            udButton.setSelected(false);
            dButton.setSelected(true);
        });
        udButton.setOnAction(e -> {
            isDirected = false;
            isUndirected = true;
            dButton.setSelected(false);
            udButton.setSelected(true);
        });
    }

    @FXML
    void click(ActionEvent actionEvent) throws IOException {
        if(isUndirected || isDirected){
            menu = new Stage();
            menu.setScene(new Scene(FXMLLoader.load(getClass().getResource("Menu.fxml"))));
            Main.home.close();
            menu.show();
        }
    }
}

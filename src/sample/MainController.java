package sample;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable{
    public static boolean directed = false, undirected = false;
    @FXML
    private JFXRadioButton dButton, udButton;

    public static Stage menu ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dButton.setSelected(directed);
        udButton.setSelected(undirected);
        dButton.setOnAction(e -> {
            directed = true;
            undirected = false;
            System.out.println("Direct");
        });
        udButton.setOnAction(e -> {
            directed = false;
            undirected = true;
            System.out.println("UnDirect");
        });
    }

    @FXML
    void click(ActionEvent event) throws IOException {

        if(undirected || directed){
            menu = new Stage();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("menu.fxml"));
            menu.setScene(new Scene(anchorPane));
            Main.home.close();
            menu.show();
        }
    }


}

package graphEngine.utils;

import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class Prompter {
    static TextInputDialog dialog;

    static private void setupDialog(String content, String title, String header, String defaultVal){
        dialog = new TextInputDialog(defaultVal);
        dialog.setHeaderText(header);
        dialog.setTitle(title);
        dialog.setContentText(content);
    }

    static public int askInt(String content, String title, String defaultVal){
        setupDialog(content, title, null, defaultVal);
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            try{
                return Integer.parseInt(result.get());
            } catch (NumberFormatException e){
                return askIntAgain(content, title, defaultVal);
            }
        }
        return -1;
    }

    static private int askIntAgain(String content, String title, String defaultVal){
        setupDialog(content, title, "Invalid input format, please input again", defaultVal);
        Optional<String> ask_again_result = dialog.showAndWait();
        if(ask_again_result.isPresent()){
            try{
                return Integer.parseInt(ask_again_result.get());
            } catch (NumberFormatException e){
                return askIntAgain(content, title, defaultVal);
            }
        }
        return -1;
    }
}

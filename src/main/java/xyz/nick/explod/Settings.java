package xyz.nick.explod;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class Settings implements Initializable {
    @FXML private TextArea statsArea;
    @FXML private CheckBox greenCheckBox;
    @FXML private CheckBox debugCheckBox;

    public static boolean green = false;
    public static boolean DEBUG_MODE = false;

    private int pr = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String stats = """
                You have...
                Exploded %d items.
                Reseted %d times.
                =========================
                """.formatted(APPLE.getExplods(), APPLE.getResets());
        statsArea.setText(stats);
        greenCheckBox.setSelected(green);
        debugCheckBox.setSelected(DEBUG_MODE);
    }

    @FXML
    public void back(ActionEvent e) throws IOException {
        Explod2.quitToTitle(e);
    }

    @FXML
    public void toggleGreen(){
        green = !green;
    }

    @FXML
    public void button(){
        pr++;
        if(pr==42){
            debugCheckBox.setVisible(true);
        }
    }

    @FXML
    public void toggleDebugMode(){
        DEBUG_MODE = !DEBUG_MODE;
        if(DEBUG_MODE){
            String str = """
                    YOU ARE ACTIVATING DEBUG MODE.
                    Any and all achievements are now disabled.
                    """;
            JOptionPane.showMessageDialog(null, str);
        }
    }
}

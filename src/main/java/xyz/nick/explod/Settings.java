package xyz.nick.explod;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class Settings implements Initializable {
    @FXML private TextArea statsArea;
    @FXML private CheckBox greenCheckBox;
    @FXML private CheckBox debugCheckBox;
    @FXML private CheckBox classicOnlyCheckbox;

    public static boolean green = false;
    public static boolean DEBUG_MODE = false;
    public static boolean classicExplosionOnly = false;

    private int pr = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var stats = """
                You have...
                Exploded %d items.
                Reseted %d times.
                =========================
                """.formatted(APPLE.getExplods(), APPLE.getResets());
        statsArea.setText(stats);
        greenCheckBox.setSelected(green);
        debugCheckBox.setSelected(DEBUG_MODE);
        classicOnlyCheckbox.setSelected(classicExplosionOnly);
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
    public void toggleCEO(){
        classicExplosionOnly = !classicExplosionOnly;
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
            var str = """
                    YOU ARE ACTIVATING DEBUG MODE.
                    Any and all achievements are now disabled.
                    """;
            JOptionPane.showMessageDialog(null, str);
        }
    }
}

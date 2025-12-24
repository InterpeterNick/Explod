package xyz.nick.explod;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Apple3D implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void quitToTitle(ActionEvent e) throws IOException {
        Explod2.quitToTitle(e);
    }
}

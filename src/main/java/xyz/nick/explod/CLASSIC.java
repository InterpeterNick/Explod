package xyz.nick.explod;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public final class CLASSIC implements Initializable {

    @FXML
    private ImageView imag;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imag.setImage(Explod2.defaultApple());
    }

    @FXML
    public void explod(){
        var sfx = Explod2.defaultExplosionSFX();
        var player = new MediaPlayer(sfx);
        imag.setImage(Explod2.defaultExplosionImage());
        player.play();
    }

    @FXML
    public void quitToTitle(ActionEvent e) throws IOException {
        Explod2.quitToTitle(e);
    }
}

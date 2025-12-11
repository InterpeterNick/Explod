package xyz.nick.explod;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CLASSIC implements Initializable {

    @FXML
    private ImageView imag;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imag.setImage(APPLE.defaultApple());
    }

    @FXML
    public void explod(){
        Media sfx = APPLE.defaultExplosionSFX();
        MediaPlayer player = new MediaPlayer(sfx);
        imag.setImage(APPLE.defaultExplosionImage());
        player.play();
    }

    @FXML
    public void reset(){
        imag.setImage(APPLE.defaultApple());
    }
}

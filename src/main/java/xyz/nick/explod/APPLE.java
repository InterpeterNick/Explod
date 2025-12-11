package xyz.nick.explod;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class APPLE implements Initializable {

    @FXML private AnchorPane root;
    @FXML private ImageView imag;
    @FXML private Label itemLabel;
    @FXML private Label explosionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing controller");
        root.setOnKeyPressed((KeyEvent e) -> {
            System.out.println(e.getCode());
            if(e.getCode()==KeyCode.Q) rotate();
            if(e.getCode()==KeyCode.E) explod();
            if(e.getCode()==KeyCode.R) reset();
        });
        reset();
        imag.setRotationAxis(new Point3D(0,0,0));
    }

    @FXML
    public void explod(){
        Media sfx = Explod2.defaultExplosionSFX();
        MediaPlayer player = new MediaPlayer(sfx);
        imag.setImage(Explod2.defaultExplosionImage());
        player.play();
    }

    @FXML
    public void reset(){
        int index = Explod2.getRandomItemImageIndex();
        Image image = Explod2.getItemImage(index);
        imag.setImage(image);
        itemLabel.setText("Item: "+Explod2.getImageName(index));
    }

    @FXML
    public void rotate(){
        Point3D axis;
        axis = new Point3D(1, 1, 1);
        System.out.println(axis);
        imag.setRotationAxis(axis);
        double r = imag.getRotate();
        imag.setRotate(r+5);
    }

    @FXML
    public void quitToTitle(ActionEvent e) throws IOException {
        Explod2.quitToTitle(e);
    }
}

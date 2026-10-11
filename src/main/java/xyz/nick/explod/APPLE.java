package xyz.nick.explod;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.*;

import static xyz.nick.explod.Explod2.*;

public final class APPLE implements Initializable {

    @FXML private AnchorPane root;
    @FXML private ImageView imag;
    @FXML private Label itemLabel;
    @FXML private Label resetLabel;
    @FXML private Label explosionLabel;
    @FXML private Label explosionName;
    @FXML private TextField itemTextField;
    @FXML private Rectangle overlay;

    private static int resets = 0;
    private static int explods = 0;
    private boolean hasExplod = false;
    private static final LinkedList<Integer> indexOrder = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initItemsAndExplosions();
        root.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode()==KeyCode.Q) rotate();
            if(e.getCode()==KeyCode.E) explod();
            if(e.getCode()==KeyCode.R) reset();
        });
        reset();
        imag.setRotationAxis(new Point3D(0,0,0));
        if(overlay!=null){
            if(Settings.green) overlay.opacityProperty().set(0.3);
            else overlay.opacityProperty().set(0.0);
        }
        if(Settings.DEBUG_MODE) itemTextField.setVisible(true);
    }

    @FXML
    public void explod(){
        if (hasExplod) return;
        Media explodSfx;
        Image explodImag;
        var nameText = "%s";
        if(Settings.classicExplosionOnly){
            explodSfx = defaultExplosionSFX();
            explodImag = defaultExplosionImage();
        } else {
            int a = getRandomExplosionIndex();
            int b = getRandomExplosionSoundIndex();
            var pi = getExplosion(a);
            var ps = getExplosionSFX(b);
            try {
                explodImag = new Image(pi.toUri().toURL().toString());
                explodSfx = new Media(ps.toUri().toString());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            nameText = nameText.formatted(getExplosion(a).getFileName().toString());
        }
        var player = new MediaPlayer(explodSfx);
        imag.setImage(explodImag);
        player.play();
        explods++;
        hasExplod = true;
        var labelText = "You have blown up %d items".formatted(explods);
        explosionLabel.setText(labelText);
        explosionName.setText(nameText);
    }

    @FXML
    public void reset() {
        int index;
        hasExplod = false;
        resets++;
        Path imagePath;
        if(Settings.DEBUG_MODE && !Objects.equals(itemTextField.getText(), "")){
            imagePath = getItem(Integer.parseInt(itemTextField.getText()));
        } else {
            index = getRandomItemIndex();
            indexOrder.add(index);
            imagePath = getItem(index);
        }
        Image image;
        try {
            image = new Image(imagePath.toUri().toURL().toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        imag.setImage(image);
        itemLabel.setText(imagePath.getFileName().toString());
        resetLabel.setText("resets: "+resets);
    }

    @FXML
    public void rotate(){
        Point3D axis;
        axis = new Point3D(1, 1, 1);
        imag.setRotationAxis(axis);
        double r = imag.getRotate();
        imag.setRotate(r+5);
    }

    @FXML
    public void quitToTitle(ActionEvent e) throws IOException {
        Explod2.quitToTitle(e);
    }

    public static int getExplods(){
        return explods;
    }

    public static int getResets(){
        return resets;
    }

    public static LinkedList<Integer> getIndexOrder(){
        return indexOrder;
    }
}

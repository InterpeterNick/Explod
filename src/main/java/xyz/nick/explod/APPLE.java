package xyz.nick.explod;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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

public class APPLE implements Initializable {

    @FXML private AnchorPane root;
    @FXML private ImageView imag;

    private static Map<String, Integer> itemImageNameIndex;
    private static Map<Integer, Image> itemImageIndex;
    private static int IMAG_AMOUNT = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.setOnKeyPressed((KeyEvent e) -> {
            System.out.println(e.getCode());
            if(e.getCode()== KeyCode.W){
                rotateX();
            }
            if(e.getCode()== KeyCode.D){
                rotateRight();
            }
        });
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
        imag.setImage(APPLE.getRandomItemImage());
    }

    @FXML
    public void rotateRight(){
        Point3D axis = imag.getRotationAxis();
        axis = new Point3D(axis.getX(), axis.getY(), 1);
        System.out.println(axis);
        imag.setRotationAxis(axis);
        double r = imag.getRotate();
        imag.setRotate(r + 5);
    }

    @FXML
    public void rotateX(){
        Point3D axis = imag.getRotationAxis();
        axis = new Point3D(1, axis.getY(), axis.getZ());
        System.out.println(axis);
        imag.setRotationAxis(axis);
        double r = imag.getRotate();
        imag.setRotate(r + 5);
    }

    public static void initImages(){
        itemImageIndex = new HashMap<>();
        itemImageNameIndex = new HashMap<>();
        try {
            URI itemPlaceholder = Objects.requireNonNull(EXPLOD.class.getResource("item/placeholder.txt")).toURI();
            Path itemPath = Path.of(itemPlaceholder).resolve("..");
            try(Stream<Path> pathStream = Files.list(itemPath)){
                AtomicInteger i = new AtomicInteger();
                pathStream.forEach(
                        (path -> {
                            if(Files.isDirectory(path)) return;
                            String fileName = String.valueOf(path.getFileName());
                            if(fileName.contains(".txt")) return;
                            itemImageNameIndex.put(fileName, i.get());
                            try {
                                Image imag = new Image(path.toUri().toURL().toString());
                                itemImageIndex.put(i.get(), imag);
                                System.out.println(fileName+" "+i.get()+" "+imag);
                            } catch (MalformedURLException e) {
                                throw new RuntimeException(e);
                            }
                            i.getAndIncrement();
                        })
                );
                IMAG_AMOUNT = i.get() + 1;
            }
        } catch (URISyntaxException | NullPointerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Image getRandomItemImage(){
        Random random = new Random(-4_206_769_342_521L);
        int i = random.nextInt(IMAG_AMOUNT);
        return itemImageIndex.get(i);
    }

    public static Image getItemImage(String name){
        int index = itemImageNameIndex.get(name);
        return itemImageIndex.get(index);
    }

    public static int getItemImageIndex(String name){
        return itemImageNameIndex.get(name);
    }

    public static Image defaultApple(){
        return new Image(Objects.requireNonNull(EXPLOD.class.getResource("item/the-classic.jpg")).toExternalForm());
    }

    public static Image defaultExplosionImage(){
        return new Image(Objects.requireNonNull(EXPLOD.class.getResource("explosion/the-classic.jpeg")).toExternalForm());
    }

    public static Media defaultExplosionSFX(){
        return new Media(Objects.requireNonNull(EXPLOD.class.getResource("explosion/explosion-289984.mp3")).toExternalForm());
    }
}

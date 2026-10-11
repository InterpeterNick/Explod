package xyz.nick.explod;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public final class Explod2 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        var fxmlLoader = new FXMLLoader(TITLE.class.getResource("title.fxml"));
        var scene = new Scene(fxmlLoader.load());
        stage.setTitle("about dang time");
        stage.setScene(scene);
        stage.show();
    }

    private static final Map<Integer, Path> itemIndex = new HashMap<>();
    private static final Map<Integer, Path> explosionIndex = new HashMap<>();
    private static final Map<Integer, Path> explosionSoundIndex = new HashMap<>();
    private static final Random random = new Random(-4_206_769_342_521L);

    private static int ITEM_IMAG_AMOUNT = 0;
    private static int EXPLOD_IMAG_AMOUNT = 0;
    private static int EXPLOD_SOUND_AMOUNT = 0;

    public static void initItemsAndExplosions(){
        try {
            initItemImages();
            initExplosionImages();
            initExplosionSound();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initItemImages() throws IOException {
        var itemPath = getResource("item");
        try(var pathStream = Files.list(itemPath)) {
            var i = new AtomicInteger();
            pathStream.forEach(
                    (path -> {
                        if(Files.isDirectory(path)) return;
                        var fileName = path.getFileName();
                        if(fileName.toString().contains(".txt")) return;
                        itemIndex.put(i.get(), path);
                        i.getAndIncrement();
                    })
            );
            ITEM_IMAG_AMOUNT = i.get();
        }
    }

    public static void initExplosionImages() throws IOException {
        var itemPath = getResource("explosion");
        try(var pathStream = Files.list(itemPath)) {
            var i = new AtomicInteger();
            pathStream.forEach(
                    (path -> {
                        if(Files.isDirectory(path)) return;
                        var fileName = path.getFileName();
                        if(fileName.toString().contains(".txt")) return;
                        if(fileName.toString().contains(".mp3")) return;
                        explosionIndex.put(i.get(), path);
                        i.getAndIncrement();
                    })
            );
            EXPLOD_IMAG_AMOUNT = i.get();
        }
    }

    public static void initExplosionSound() throws IOException {
        var itemPath = getResource("sound");
        try(var pathStream = Files.list(itemPath)) {
            AtomicInteger i = new AtomicInteger();
            pathStream.forEach(
                    (path -> {
                        if(Files.isDirectory(path)) return;
                        var fileName = path.getFileName();
                        if(!fileName.toString().contains(".mp3")) return;
                        explosionSoundIndex.put(i.get(), path);
                        i.getAndIncrement();
                    })
            );
            EXPLOD_SOUND_AMOUNT = i.get();
        }
    }

    public static int getRandomItemIndex(){
        return random.nextInt(0, ITEM_IMAG_AMOUNT);
    }
    public static int getRandomExplosionIndex(){
        return random.nextInt(0, EXPLOD_IMAG_AMOUNT);
    }
    public static int getRandomExplosionSoundIndex(){
        return random.nextInt(0, EXPLOD_SOUND_AMOUNT);
    }

    public static Path getItem(int index){
        return itemIndex.get(index);
    }
    public static Path getExplosion(int index){
        return explosionIndex.get(index);
    }
    public static Path getExplosionSFX(int index){
        return explosionSoundIndex.get(index);
    }

    public static Image defaultApple(){
        return new Image(getResource("item/the-classic.jpg").toUri().toString());
    }

    public static Image defaultExplosionImage(){
        return new Image(getResource("explosion/the-classic.jpeg").toUri().toString());
    }

    public static Media defaultExplosionSFX(){
        return new Media(getResource("sound/the-classic.mp3").toUri().toString());
    }

    public static void quitToTitle(ActionEvent e) throws IOException {
        var stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        var fxmlLoader = new FXMLLoader(TITLE.class.getResource("title.fxml"));
        var scene = new Scene(fxmlLoader.load());
        stage.setTitle("about dang time.");
        stage.setScene(scene);
        stage.show();
    }

    public static Path getResource(String name){
        return Path.of("resources").resolve(name);
    }
    public static Path getResource(Path name){
        return Path.of("resources").resolve(name);
    }
}

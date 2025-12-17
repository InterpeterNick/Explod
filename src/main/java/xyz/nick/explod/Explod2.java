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
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public final class Explod2 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TITLE.class.getResource("title.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    private static final Map<Integer, Path> itemIndex = new HashMap<>();
    private static final Map<Integer, Path> explosionIndex = new HashMap<>();
    private static final Random random = new Random(-4_206_769_342_521L);

    private static int ITEM_IMAG_AMOUNT = 0;

    public static void initItemsAndExplosions(){
        try {
            initItemImages();
            initExplosionImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initItemImages() throws IOException {
        Path itemPath = getResource("item");
        try(Stream<Path> pathStream = Files.list(itemPath)) {
            AtomicInteger i = new AtomicInteger();
            pathStream.forEach(
                    (path -> {
                        if(Files.isDirectory(path)) return;
                        Path fileName = path.getFileName();
                        if(fileName.toString().contains(".txt")) return;
                        itemIndex.put(i.get(), path);
                        i.getAndIncrement();
                    })
            );
            ITEM_IMAG_AMOUNT = i.get();
        }
    }

    public static void initExplosionImages() throws IOException {
        itemIndex.forEach((key, value) -> System.out.println(key+" "+value.getFileName()));
    }

    public static int getRandomItemIndex(){
        return random.nextInt(0, ITEM_IMAG_AMOUNT);
    }

    public static Path getItem(int index){
        return itemIndex.get(index);
    }

    public static Image defaultApple(){
        return new Image(getResource("item/the-classic.jpg").toUri().toString());
    }

    public static Image defaultExplosionImage(){
        return new Image(getResource("explosion/the-classic.jpeg").toUri().toString());
    }

    public static Media defaultExplosionSFX(){
        return new Media(getResource("explosion/explosion-289984.mp3").toUri().toString());
    }

    public static void quitToTitle(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TITLE.class.getResource("title.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("about dang time.");
        stage.setScene(scene);
        stage.show();
    }

    public static Path getResource(String name){
        return Path.of("resources").resolve(name);
    }
}

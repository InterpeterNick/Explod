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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public final class Explod2 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Explod2.initItemsAndExplosions();
        FXMLLoader fxmlLoader = new FXMLLoader(TITLE.class.getResource("title.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    private static final Map<String, Integer> itemImageNameToIndexMap = new HashMap<>();
    private static final Map<Integer, String> itemImageNameIndex = new HashMap<>();
    private static final Map<Integer, Image> itemImageIndex = new HashMap<>();
    private static final Map<String, Integer> explosionIndex = new HashMap<>();

    private static int IMAG_AMOUNT = 0;

    public static void initItemsAndExplosions(){
        initItemImages();
    }

    public static void initItemImages(){
        try {
            URI itemPlaceholder = Objects.requireNonNull(TITLE.class.getResource("item/placeholder.txt")).toURI();
            Path itemPath = Path.of(itemPlaceholder).resolve("..");
            try(Stream<Path> pathStream = Files.list(itemPath)){
                AtomicInteger i = new AtomicInteger();
                pathStream.forEach(
                        (path -> {
                            if(Files.isDirectory(path)) return;
                            String fileName = String.valueOf(path.getFileName());
                            if(fileName.contains(".txt")) return;
                            itemImageNameToIndexMap.put(fileName, i.get());
                            itemImageNameIndex.put(i.get(), fileName);
                            try {
                                Image imag = new Image(path.toUri().toURL().toString());
                                itemImageIndex.put(i.get(), imag);
                                //System.out.println("\t"+fileName+" "+i.get()+" "+imag);
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

    public static int getRandomItemImageIndex(){
        Random random = new Random(-4_206_769_342_521L);
        return random.nextInt(IMAG_AMOUNT);
    }

    public static Image getItemImage(String name){
        int index = itemImageNameToIndexMap.get(name);
        return itemImageIndex.get(index);
    }

    public static Image getItemImage(int index){
        return itemImageIndex.get(index);
    }

    public static int getItemImageIndex(String name){
        return itemImageNameToIndexMap.get(name);
    }

    public static String getImageName(int index){
        return itemImageNameIndex.get(index);
    }

    public static Image defaultApple(){
        return new Image(Objects.requireNonNull(TITLE.class.getResource("item/the-classic.jpg")).toExternalForm());
    }

    public static Image defaultExplosionImage(){
        return new Image(Objects.requireNonNull(TITLE.class.getResource("explosion/the-classic.jpeg")).toExternalForm());
    }

    public static Media defaultExplosionSFX(){
        return new Media(Objects.requireNonNull(TITLE.class.getResource("explosion/explosion-289984.mp3")).toExternalForm());
    }

    public static void quitToTitle(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TITLE.class.getResource("title.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("about dang time.");
        stage.setScene(scene);
        stage.show();
    }

    public static void launch(String... args){
        System.out.println("Launching...");
        Application.launch(Explod2.class, args);
    }
}

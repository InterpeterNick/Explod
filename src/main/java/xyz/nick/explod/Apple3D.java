package xyz.nick.explod;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static xyz.nick.explod.Explod2.defaultExplosionSFX;

public class Apple3D implements Initializable {

    private static final double SPEED = 10;

    private static Group mainGroup;
    private static Camera camera;

    private static boolean wPressed;
    private static boolean aPressed;
    private static boolean sPressed;
    private static boolean dPressed;

    private static boolean lPressed;
    private static boolean rPressed;

    public static void play3D(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        Group itemGroup = buildItemGroup(stage);
        Scene scene = buildScene(itemGroup, stage);

        AnimationTimer timer = buildAnimationTimer();
        timer.start();

        stage.setTitle("nowin3D!");
        stage.setScene(scene);
        stage.show();
    }

    private static Group buildItemGroup(Stage stage){
        Sphere sphere = new Sphere(50);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
        sphere.setMaterial(material);

        Group itemGroup = new Group();
        itemGroup.getChildren().add(sphere);
        itemGroup.setTranslateX(stage.getWidth() / 2);
        itemGroup.setTranslateY(stage.getHeight() / 2);

        return itemGroup;
    }

    private static Scene buildScene(Group itemGroup, Stage stage) {
        AmbientLight ambientLight = new AmbientLight(Color.WHITE);
        ambientLight.setLightOn(true);

        mainGroup = new Group(itemGroup, ambientLight);
        camera = new PerspectiveCamera();
        camera.setRotationAxis(new Point3D(0, 1, 0));

        Button b = new Button("EXPLOD!");
        b.setOnAction((e) -> {
            Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
            s.setScene(new Scene(new AnchorPane()));
            explod();
        });
        mainGroup.getChildren().add(b);

        Scene scene = new Scene(
                mainGroup, stage.getWidth(), stage.getHeight(),
                true, SceneAntialiasing.BALANCED);
        scene.getRoot();
        scene.setCamera(camera);
        scene.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()){
                case W -> wPressed = true;
                case A -> aPressed = true;
                case S -> sPressed = true;
                case D -> dPressed = true;
                case LEFT -> lPressed = true;
                case RIGHT -> rPressed = true;
                default ->  {}
            }
        });
        scene.setOnKeyReleased((KeyEvent e) -> {
            switch (e.getCode()){
                case W -> wPressed = false;
                case A -> aPressed = false;
                case S -> sPressed = false;
                case D -> dPressed = false;
                case LEFT -> lPressed = false;
                case RIGHT -> rPressed = false;
                default ->  {}
            }
        });
        return scene;
    }

    private static AnimationTimer buildAnimationTimer(){
        return new AnimationTimer() {

            @Override
            public void handle(long now) {
                double prevX = mainGroup.getTranslateX();
                double prevY = mainGroup.getTranslateY();
                double prevZ = mainGroup.getTranslateZ();

                double prevR = mainGroup.getRotate();

                if(wPressed) mainGroup.setTranslateZ(prevZ-(SPEED));
                if(aPressed) mainGroup.setTranslateX(prevX+(SPEED));
                if(sPressed) mainGroup.setTranslateZ(prevZ+(SPEED));
                if(dPressed) mainGroup.setTranslateX(prevX-(SPEED));

                if(lPressed) camera.setRotate(prevR+(SPEED));
                if(rPressed) camera.setRotate(prevR-(SPEED));
            }
        };
    }

    private static void explod(){
        Media explodSfx = defaultExplosionSFX();
        MediaPlayer player = new MediaPlayer(explodSfx);
        player.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

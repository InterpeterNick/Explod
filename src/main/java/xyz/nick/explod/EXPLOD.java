package xyz.nick.explod;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EXPLOD extends Application implements Initializable {

    @FXML private VBox menuPane;
    @FXML private Button button0;
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;

    @FXML private AnchorPane titlePane;
    @FXML private Label title;
    @FXML private Label subtitle;
    @FXML private Label titleNumber;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EXPLOD.class.getResource("title.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("explod3!!!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button0.setText("PLAY");
        button0.onActionProperty().set((ActionEvent e) -> {
            try {
                this.play(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button0.setVisible(true);
    }

    @FXML
    public void play(ActionEvent e) throws IOException {
        resetMenuPane();
        button0.setText("REBORN MODE");
        button1.setText("Apple3D");
        button2.setText("CLASSIC MODE");
        button3.setText("BACK");

        button0.onActionProperty().set((ActionEvent ev) -> {
            try {
                this.playReborn(ev);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button2.onActionProperty().set((ActionEvent ev) -> {
            try {
                this.playClassic(ev);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button3.onActionProperty().set((ActionEvent e1) -> {
            resetMenuPane();
            button0.setText("PLAY");
            button0.onActionProperty().set((ActionEvent e2) -> {
                try {
                    this.play(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            button0.setVisible(true);
        });

        button0.setVisible(true);
        button1.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
    }

    @FXML
    public void playReborn(ActionEvent e) throws IOException {
        APPLE.initImages();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(EXPLOD.class.getResource("apple.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("reborn");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void play3D(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(EXPLOD.class.getResource("apple3D.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("apple3D");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void playClassic(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(EXPLOD.class.getResource("classic.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("classic");
        stage.setScene(scene);
        stage.show();
    }

    public void resetMenuPane(){
        ((Pane)  menuPane.getChildren().getFirst()).getChildren().forEach(
                (Node node) -> {
                    if(!(node instanceof Button b)) return;
                    b.setVisible(false);
                    b.setText(null);
                    b.onActionProperty().set(null);
                }
        );
    }
}

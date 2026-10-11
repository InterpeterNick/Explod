package xyz.nick.explod;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public final class TITLE implements Initializable {

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
    public void initialize(URL location, ResourceBundle resources) {
        initialUI();
    }

    @FXML
    public void initialUI(){
        resetMenuPane();
        button0.setText("PLAY");
        button0.setTextFill(Color.YELLOW);
        button1.setText("SETTINGS");

        button0.onActionProperty().set((ActionEvent e) -> {
            try {
                this.play(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button1.onActionProperty().set((ActionEvent e) -> {
            try {
                this.settings(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        button0.setVisible(true);
        button1.setVisible(true);
    }

    @FXML
    public void settings(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TITLE.class.getResource("settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("settings");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void play(ActionEvent e) throws IOException {
        resetMenuPane();
        button0.setText("NORMAL MODE");
        button1.setText("Apple3D (Preview)");
        button2.setText("CLASSIC MODE");
        button3.setText("BACK");

        button0.onActionProperty().set((ActionEvent ev) -> {
            try {
                this.playReborn(ev);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button1.onActionProperty().set((ActionEvent ev) -> {
            try {
                this.play3D(ev);
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
            initialUI();
        });

        button0.setVisible(true);
        button1.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
    }

    @FXML
    public void playReborn(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TITLE.class.getResource("apple.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("explod2!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void play3D(ActionEvent e) throws IOException {
        Apple3D.play3D(e);
    }

    @FXML
    public void playClassic(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TITLE.class.getResource("classic.fxml"));
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
                    b.setTextFill(Color.BLACK);
                    b.setText(null);
                    b.onActionProperty().set(null);
                }
        );
    }
}

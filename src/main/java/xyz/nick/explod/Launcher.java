package xyz.nick.explod;

import javafx.application.Application;

import javax.swing.*;

public class Launcher {
    public static void main(String... args) {
        JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to play this?",
                "You are booting up Explod 2: Farewell",
                JOptionPane.DEFAULT_OPTION
        );
        Application.launch(Explod2.class, args);
    }
}

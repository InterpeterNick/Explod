module xyz.nick.explod {
    requires javafx.media;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;


    opens xyz.nick.explod to javafx.fxml;
    exports xyz.nick.explod;
}
module xyz.nick.explod {
    requires javafx.media;
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires javafx.graphics;
    requires javafx.base;


    opens xyz.nick.explod to javafx.fxml;
    exports xyz.nick.explod;
}
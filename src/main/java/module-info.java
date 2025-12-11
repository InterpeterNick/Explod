module xyz.nick.explod {
    requires javafx.media;
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens xyz.nick.explod to javafx.fxml;
    exports xyz.nick.explod;
}
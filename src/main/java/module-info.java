module org.openjfx.emojibuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens org.openjfx.emojibuilder to javafx.fxml;
    exports org.openjfx.emojibuilder;
}

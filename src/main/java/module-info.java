module com.example.cryptoanalyzer {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.cryptoanalyzer to javafx.fxml;
    exports com.example.cryptoanalyzer;
}
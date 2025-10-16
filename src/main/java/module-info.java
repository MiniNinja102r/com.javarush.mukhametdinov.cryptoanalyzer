module com.example.cryptoanalyzer {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires org.yaml.snakeyaml;
    requires static lombok;

    opens com.example.cryptoanalyzer to javafx.fxml;
    exports com.example.cryptoanalyzer;
    exports com.example.cryptoanalyzer.application;
    opens com.example.cryptoanalyzer.application to javafx.fxml;
    exports com.example.cryptoanalyzer.controller;
    opens com.example.cryptoanalyzer.controller to javafx.fxml;
}
package com.example.cryptoanalyzer.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public record InformationController(@NotNull Pane informationPane, @NotNull Label informationLabel) {

    public void clearInfo() {
        Platform.runLater(() -> {
            informationPane.setVisible(false);
            informationLabel.setText("");
            informationLabel.setTextFill(Color.WHITE);
        });
    }

    public void showError(@NotNull String message) {
        Platform.runLater(() -> {
            informationLabel.setVisible(true);
            informationPane.setVisible(true);
            informationLabel.setTextFill(Color.RED);
            informationLabel.setText(message);
        });
    }
}

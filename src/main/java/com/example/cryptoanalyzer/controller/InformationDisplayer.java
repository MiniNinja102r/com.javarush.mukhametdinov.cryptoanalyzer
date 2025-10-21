package com.example.cryptoanalyzer.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public record InformationDisplayer(@NotNull Pane informationPane, @NotNull Label informationLabel) {

    public void showError(@NotNull String message) {
        showInfo(message, Color.RED);
    }

    public void showSuccess(@NotNull String message) {
        showInfo(message, Color.GREEN);
    }

    public void showInfo(@NotNull String message, @NotNull Color color) {
        Platform.runLater(() -> {
            informationLabel.setVisible(true);
            informationPane.setVisible(true);
            informationLabel.setTextFill(color);
            informationLabel.setText(message);
        });
    }

    public void clearInfo() {
        Platform.runLater(() -> {
            informationPane.setVisible(false);
            informationLabel.setText("");
            informationLabel.setTextFill(Color.WHITE);
        });
    }
}

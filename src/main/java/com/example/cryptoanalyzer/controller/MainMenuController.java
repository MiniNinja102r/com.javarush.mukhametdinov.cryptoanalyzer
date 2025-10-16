package com.example.cryptoanalyzer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public final class MainMenuController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}

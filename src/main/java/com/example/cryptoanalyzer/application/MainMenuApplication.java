package com.example.cryptoanalyzer.application;

import com.example.cryptoanalyzer.storage.Config;
import com.example.cryptoanalyzer.util.AppConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class MainMenuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Config.load();

        FXMLLoader fxmlLoader = new FXMLLoader(); //todo: scene-manager
        Scene scene = new Scene(fxmlLoader.load(), AppConstants.APP_WIDTH, AppConstants.APP_HEIGHT);
        stage.setTitle(AppConstants.APP_NAME);
        stage.setScene(scene);
        stage.show();
    }
}

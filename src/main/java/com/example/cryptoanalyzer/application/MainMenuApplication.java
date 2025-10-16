package com.example.cryptoanalyzer.application;

import com.example.cryptoanalyzer.util.AppConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class MainMenuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/cryptoanalyzer/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), AppConstants.APP_WIDTH, AppConstants.APP_HEIGHT);
        stage.setTitle(AppConstants.APP_NAME);
        stage.setScene(scene);
        stage.show();
    }
}

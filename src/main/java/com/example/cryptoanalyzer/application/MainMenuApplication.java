package com.example.cryptoanalyzer.application;

import com.example.cryptoanalyzer.service.scene.SceneManager;
import com.example.cryptoanalyzer.service.scene.SceneType;
import com.example.cryptoanalyzer.config.Config;
import javafx.application.Application;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public final class MainMenuApplication extends Application {
    @Override
    public void start(@NotNull Stage stage) {
        Config.load();

        initializeManagers(stage);

        SceneManager.getInstance().switchScene(SceneType.MAIN_MENU);
        stage.show();
    }

    private void initializeManagers(@NotNull Stage stage) {
        SceneManager.initialize(stage);
    }

    @Override
    public void stop() {
        // unload tasks.
    }
}

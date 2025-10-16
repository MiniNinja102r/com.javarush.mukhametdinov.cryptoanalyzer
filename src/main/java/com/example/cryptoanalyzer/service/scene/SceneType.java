package com.example.cryptoanalyzer.service.scene;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SceneType {
    MAIN_MENU("main-menu.fxml");

    private final String fxmlFileName;
}

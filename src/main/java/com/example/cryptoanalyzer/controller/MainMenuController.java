package com.example.cryptoanalyzer.controller;

import com.example.cryptoanalyzer.service.FileManager;
import com.example.cryptoanalyzer.service.Validator;
import com.example.cryptoanalyzer.service.cipher.BruteForce;
import com.example.cryptoanalyzer.service.cipher.CaesarCipher;
import com.example.cryptoanalyzer.storage.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Optional;

public final class MainMenuController {
    @NotNull
    private final Validator validator = Validator.getInstance();

    @NotNull
    private final FileManager fileManager = FileManager.getInstance();

    @NotNull
    private final CaesarCipher cipher = new CaesarCipher(validator);

    @NotNull
    private final BruteForce bruteForce = new BruteForce(validator);

    private InformationDisplayer infoDisplayer;

    @FXML
    private TextField keyField;

    @FXML
    private TextField pathField;

    @FXML
    private Pane informationPane;

    @FXML
    private Label informationLabel;

    public void initialize() {
        this.infoDisplayer = new InformationDisplayer(informationPane, informationLabel);
    }

    @FXML
    void onDecryptClick(ActionEvent event) {
        infoDisplayer.clearInfo();
        final String path = pathField.getText();
        if (isPathInvalid(path)) return;

        Integer key = parseKeyOrShowError(keyField.getText());
        if (key == null) return;

        readFileOrShowError(path).ifPresent(text -> {
            final String decrypted = cipher.decrypt(text, key);
            if (!fileManager.writeFile(decrypted, path)) {
                infoDisplayer.showError(Config.Message.CANNOT_WRITE);
            }
        });
    }

    @FXML
    void onEncryptClick(ActionEvent event) {
        infoDisplayer.clearInfo();
        final String path = pathField.getText();
        if (isPathInvalid(path)) return;

        Integer key = parseKeyOrShowError(keyField.getText());
        if (key == null) return;

        readFileOrShowError(path).ifPresent(text -> {
            final String encrypted = cipher.encrypt(text, key);
            if (!fileManager.writeFile(encrypted, path)) {
                infoDisplayer.showError(Config.Message.CANNOT_WRITE);
            }
        });
    }

    @FXML
    void onBruteForceClick(ActionEvent event) {
        infoDisplayer.clearInfo();
        final String path = pathField.getText();
        if (isPathInvalid(path)) return;

        readFileOrShowError(path).ifPresent(text -> {
            var optionalDirectory = fileManager.writeDirectory(path);
            if (optionalDirectory.isEmpty()) {
                infoDisplayer.showError(Config.Message.CANNOT_WRITE);
                return;
            }
            final Path directory = optionalDirectory.get();
            String[] ciphers = bruteForce.decrypt(text);
            for (int i = 0; i < ciphers.length; i++) {
                var line = ciphers[i];
                fileManager.writeFile(line, directory + "\\#" + (i + 1));
            }
        });
    }

    private boolean isPathInvalid(String path) {
        if (path == null || path.isEmpty() || validator.isFileNotExists(path)) {
            infoDisplayer.showError(Config.Message.FILE_NOT_FOUND);
            return true;
        }
        return false;
    }

    @Nullable
    private Integer parseKeyOrShowError(String keyText) {
        if (keyText == null || keyText.isEmpty()) {
            infoDisplayer.showError(Config.Message.KEY_IS_EMPTY);
            return null;
        }

        try {
            final int raw = Integer.parseInt(keyText.trim());
            return validator.getValidKey(raw);
        } catch (NumberFormatException e) {
            infoDisplayer.showError(Config.Message.KEY_IS_INVALID);
            return null;
        } catch (Exception e) {
            infoDisplayer.showError(Config.Message.INTERNAL_ERROR);
            return null;
        }
    }

    private Optional<String> readFileOrShowError(@NotNull String path) {
        try {
            var optionalText = fileManager.readFile(path);
            if (optionalText.isEmpty())
                infoDisplayer.showError(Config.Message.CANNOT_READ);
            return optionalText;
        } catch (Exception e) {
            infoDisplayer.showError(Config.Message.CANNOT_READ);
            return Optional.empty();
        }
    }
}
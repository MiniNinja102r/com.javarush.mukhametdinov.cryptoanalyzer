package com.example.cryptoanalyzer.controller;

import com.example.cryptoanalyzer.service.FileManager;
import com.example.cryptoanalyzer.service.Validator;
import com.example.cryptoanalyzer.service.cipher.BruteForce;
import com.example.cryptoanalyzer.service.cipher.CaesarCipher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Optional;

@Log
public final class MainMenuController {

    @NotNull
    private final Validator validator = Validator.getInstance();

    @NotNull
    private final FileManager fileManager = FileManager.getInstance();

    @NotNull
    private final CaesarCipher cipher = new CaesarCipher(validator);

    @NotNull
    private final BruteForce bruteForce = new BruteForce(validator);

    @FXML
    private Button bruteForceButton;

    @FXML
    private Button decryptButton;

    @FXML
    private Button encryptButton;

    @FXML
    private TextField keyField;

    @FXML
    private TextField pathField;

    @FXML
    private Pane informationPane;

    @FXML
    private Label informationLabel;

    @NotNull
    private final InformationController infoController = new InformationController(informationPane, informationLabel);

    @FXML
    void onDecryptClick(ActionEvent event) {
        infoController.clearInfo();
        final String path = pathField.getText();
        if (!validatePathOrShowError(path)) return;

        Integer key = parseKeyOrShowError(keyField.getText());
        if (key == null) return;

        String text = null;
        Optional<String> optionalText = fileManager.readFile(path);
        if (optionalText.isPresent())
            text = optionalText.get();

        if (text == null) {
            log.warning("Content is null");
            return;
        }

        final String decrypted = cipher.decrypt(text, key);
        if (!fileManager.writeFile(decrypted, path)) {
            //showError(cannot_write);
        }
    }

    @FXML
    void onEncryptClick(ActionEvent event) {
        infoController.clearInfo();
        final String path = pathField.getText();
        if (!validatePathOrShowError(path)) return;

        Integer key = parseKeyOrShowError(keyField.getText());
        if (key == null) return;

        String text = null;
        Optional<String> optionalText = fileManager.readFile(path);
        if (optionalText.isPresent())
            text = optionalText.get();

        if (text == null) {
            log.warning("Content is null");
            return;
        }

        final String encrypted = cipher.encrypt(text, key);
        if (!fileManager.writeFile(encrypted, path)) {
            log.warning("file not created");
        }
    }

    @FXML
    void onBruteForceClick(ActionEvent event) {
        infoController.clearInfo();
        final String path = pathField.getText();
        if (!validatePathOrShowError(path)) return;

        String text = null;
        Optional<String> optionalText = fileManager.readFile(path);
        if (optionalText.isPresent())
            text = optionalText.get();

        if (text == null) {
            log.warning("Content is null");
            return;
        }

        Path directory = null;
        final Optional<Path> optional = fileManager.writeDirectory(path);
        if (optional.isPresent())
            directory = optional.get();

        if (directory == null) {
            log.warning("Directory is null");
            return;
        }

        String[] ciphers = bruteForce.decrypt(text);
        for (int i = 0; i < ciphers.length; i++) {
            var line = ciphers[i];
            fileManager.writeFile(line, directory + "\\#" + (i + 1));
        }
    }

    private boolean validatePathOrShowError(String path) {
        if (path == null || path.isEmpty() || validator.isFileNotExists(path)) {
            //showError(file_not_found);
            return false;
        }
        return true;
    }

    @Nullable
    private Integer parseKeyOrShowError(String keyText) {
        if (keyText == null || keyText.isEmpty()) {
            //showError(key_is_null);
            return null;
        }

        try {
            final int raw = Integer.parseInt(keyText.trim());
            return validator.getValidKey(raw);
        } catch (NumberFormatException e) {
            //showError(key_is_empty);
            return null;
        } catch (Exception e) {
            //showError(internal_error);
            return null;
        }
    }
}
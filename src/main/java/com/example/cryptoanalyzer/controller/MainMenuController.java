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

import java.util.Arrays;
import java.util.Optional;

@Log
public final class MainMenuController {

    @NotNull
    private final Validator validator = Validator.getInstance();

    @NotNull
    private final FileManager fileManager = FileManager.getInstance();

    @NotNull
    private final CaesarCipher cipher = CaesarCipher.getInstance();

    @NotNull
    private final BruteForce bruteForce = BruteForce.getInstance();

    @FXML
    private Button bruteForceButton;

    @FXML
    private Button decryptButton;

    @FXML
    private Button encryptButton;

    @FXML
    private Pane informationPane;

    @FXML
    private TextField keyField;

    @FXML
    private TextField pathField;

    @FXML
    private Label pathNotFoundLabel;

    @FXML
    void onDecryptClick(ActionEvent event) {
        String[] res = bruteForce.decrypt("абвгдеёжзиклмнопрстуфхчщщко");
        System.out.println(Arrays.toString(res));

//        final String path = pathField.getCharacters().toString();
//        if (validator.isFileNotExists(path)) {
//            pathNotFoundLabel.setVisible(true);
//            return;
//        }
//        pathNotFoundLabel.setVisible(false); // todo: нормальное отображение ошибок.
//
//        String content = null;
//        Optional<String> optional = fileManager.readFile(path);
//        if (optional.isPresent())
//            content = optional.get();
//
//        if (content == null) {
//            log.warning("Content is null");
//            return;
//        }
//
//        final String decrypted = cipher.decrypt(content, 1);
//        if (!fileManager.writeFile(decrypted, path)) {
//            log.warning("file not created");
//        }
    }

    @FXML
    void onEncryptClick(ActionEvent event) {
        final String path = pathField.getCharacters().toString();
        if (validator.isFileNotExists(path)) {
            pathNotFoundLabel.setVisible(true);
            return;
        }
        pathNotFoundLabel.setVisible(false); // todo: нормальное отображение ошибок.

        String content = null;
        Optional<String> optional = fileManager.readFile(path);
        if (optional.isPresent())
            content = optional.get();

        if (content == null) {
            log.warning("Content is null");
            return;
        }

        final String encrypted = cipher.encrypt(content, 1);
        if (!fileManager.writeFile(encrypted, path)) {
            log.warning("file not created");
        }
    }

    @FXML
    void onBruteForceClick(ActionEvent event) {
        //
    }

    public void initialize() {
        pathNotFoundLabel.setVisible(false);
    }
}
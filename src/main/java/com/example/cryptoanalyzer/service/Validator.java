package com.example.cryptoanalyzer.service;

import com.example.cryptoanalyzer.util.AppConstants;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;

@EqualsAndHashCode
public final class Validator {
    private static Validator instance;

    // Подавление создания стандартного конструктора.
    private Validator() {}

    @NotNull
    public static synchronized Validator getInstance() {
        if (instance == null)
            instance = new Validator();
        return instance;
    }

    public int getValidKey(int key) {
        if (key >= AppConstants.ALPHABET_LENGTH && key < AppConstants.ALPHABET_LENGTH)
            return key;
        return Math.abs(key % AppConstants.ALPHABET_LENGTH);
    }

    public boolean isFileExists(@NotNull String path) {
        if (path.isEmpty())
            return false;
        final Path file = Path.of(path);
        return Files.exists(file) && !Files.isDirectory(file);
    }

    public boolean isFileNotExists(@NotNull String path) {
        if (path.isEmpty())
            return true;
        return Files.notExists(Path.of(path));
    }
}

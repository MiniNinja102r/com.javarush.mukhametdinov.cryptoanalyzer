package com.example.cryptoanalyzer.service;

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


    public boolean isValidKey(int key) {
        //todo: valid key
        return false;
    }

    public boolean isFileExists(@NotNull String path) {
        if (path.isEmpty())
            return false;
        final Path file = Path.of(path);
        return Files.exists(file) && !Files.isDirectory(file);
    }

    public boolean isFileNotExists(@NotNull String path) {
        if (path.isEmpty())
            return false;
        return Files.notExists(Path.of(path));
    }
}

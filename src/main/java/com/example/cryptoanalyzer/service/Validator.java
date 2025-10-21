package com.example.cryptoanalyzer.service;

import com.example.cryptoanalyzer.util.AppConstants;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;

@EqualsAndHashCode
public final class Validator {

    public int getValidKey(int key) {
        int valid = key % AppConstants.ALPHABET_LENGTH;
        if (valid < 0)
            valid += AppConstants.ALPHABET_LENGTH;
        return valid;
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

package com.example.cryptoanalyzer.service;

import com.example.cryptoanalyzer.storage.Config;
import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Log
@EqualsAndHashCode
public final class FileManager {
    private static FileManager instance;

    // Подавление создания стандартного конструктора.
    private FileManager() {}

    @NotNull
    public static synchronized FileManager getInstance() {
        if (instance == null)
            instance = new FileManager();
        return instance;
    }

    public String readFile(String path) {
        //todo: read
        return "";
    }

    public boolean writeFile(@NotNull String content, @NotNull String path) {
        if (path.isEmpty() || content.isEmpty()) {
            log.severe(ErrorType.DATA_IS_NULL.getDescription());
            return false;
        }

        try {
            final String fileName = Config.File.DEFAULT_WRITE_FILE_NAME + Config.File.WRITE_FILE_FORMAT;
            Path filePath = Path.of(path + fileName);
            if (Files.exists(filePath)) {
                log.warning(ErrorType.FILE_EXISTS.getDescription());
                return false;
            }
            final Path file = Files.createFile(filePath);
            return writeFileData(content, file);
        } catch (IOException e) {
            log.severe("Error, couldn't write file: " + e.getMessage());
            return false;
        }
    }

    private boolean writeFileData(@NotNull String content, @NotNull Path file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {
            writer.write(content);
            writer.flush();
            return true;
        } catch (IOException e) {
            log.severe("Error, can't write file data: " + e.getMessage());
            return false;
        }
    }
}

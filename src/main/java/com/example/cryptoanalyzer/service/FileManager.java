package com.example.cryptoanalyzer.service;

import com.example.cryptoanalyzer.storage.Config;
import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

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

    public Optional<String> readFile(@NotNull String path) {
        if (path.isEmpty()) {
            log.warning(ErrorType.DATA_IS_NULL.getDescription());
            return Optional.empty();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            var content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.isEmpty() ? Optional.empty() : Optional.of(content.toString());
        } catch (IOException e) {
            log.severe("Error, couldn't read file: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Path> writeDirectory(@NotNull String path) {
        return createDirectory(Path.of(path));
    }

    public Optional<Path> writeDirectory(@NotNull Path path) {
        return createDirectory(path);
    }

    private Optional<Path> createDirectory(@NotNull Path path) {
        try {
            final Path directoryPath = Path.of(path.getParent() + "\\" + Config.File.DEFAULT_WRITE_DIRECTORY_NAME);
            return Optional.of(Files.createDirectory(directoryPath));
        } catch (Exception e) {
            log.severe("Error, couldn't write directory: " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean writeFile(@NotNull String content, @NotNull String path) {
        if (path.isEmpty() || content.isEmpty()) {
            log.warning(ErrorType.DATA_IS_NULL.getDescription());
            return false;
        }

        try {
            final Path filePath = buildOutputFilePath(path);
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

    @NotNull
    private Path buildOutputFilePath(@NotNull String path) {
        Path filePath = Path.of(path);
        final String fileName = removeExtension(filePath.getFileName().toString()) + "-" +
                Config.File.DEFAULT_WRITE_FILE_NAME + Config.File.WRITE_FILE_FORMAT;
        return Path.of(filePath.getParent() + "\\" + fileName);
    }

    @NotNull
    private String removeExtension(@NotNull String fileName) {
        return fileName.lastIndexOf('.') > 0 ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;
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

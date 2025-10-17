package com.example.cryptoanalyzer.service;

import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

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
        if (path.isEmpty() || content.isEmpty())
            return false;

        final File file = new File(path);
        try {
            boolean success = file.createNewFile();
            if (!success)
                log.warning(ErrorType.FILE_ALREADY_EXISTS_ERROR.getDescription());
        } catch (IOException e) {
            log.severe("Error, can't write file: " + e.getMessage());
        }

        return false;
    }
}

package com.example.cryptoanalyzer.storage;

import lombok.extern.java.Log;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Log
public final class Config {
    private static Map<String, Object> data;

    // Подавление создания стандартного конструктора.
    private Config() {}

    public static void load() {
        try (InputStream input = Config.class.getResourceAsStream("/com/example/cryptoanalyzer/config.yml")) {
            if (input == null) {
                log.severe("Config file not found");
                return;
            }
            final Yaml yaml = new Yaml();
            data = yaml.load(input);
            loadResources();
            loadFiles();
            loadMessages();
        } catch (IOException e) {
            log.severe("Error reading resource configuration: " + e);
        }
    }

    private static void loadResources() {
        final Map<String, Object> resource = (Map<String, Object>) data.get("resource");
        if (resource == null)
            log.severe("Resource section not found in config");
        else {
            Resource.PATH = (String) resource.get("path");
            Resource.FXML_PATH = (String) resource.get("fxml");
            Resource.IMG_PATH = (String) resource.get("image");
        }
    }

    private static void loadFiles() {
        final Map<String, Object> file = (Map<String, Object>) data.get("file");
        if (file == null)
            log.severe("File section not found in config");
        else {
            File.WRITE_FILE_FORMAT = (String) file.get("write_file_format");
            File.DEFAULT_WRITE_FILE_NAME = (String) file.get("default_write_file_name");
            File.DEFAULT_WRITE_DIRECTORY_NAME = (String) file.get("default_write_directory_name");
        }
    }

    private static void loadMessages() {
        final Map<String, Object> message = (Map<String, Object>) data.get("message");
        if (message == null)
            log.severe("Message section not found in config");
        else {
            Message.FILE_NOT_FOUND = (String) message.get("file_not_found");
            Message.KEY_IS_EMPTY = (String) message.get("key_is_empty");
            Message.KEY_IS_INVALID = (String) message.get("key_is_invalid");
            Message.CANNOT_READ = (String) message.get("cannot_read");
            Message.CANNOT_WRITE = (String) message.get("cannot_write");
            Message.INTERNAL_ERROR = (String) message.get("internal_error");
            Message.SUCCESSFUL_ENCRYPTED = (String) message.get("successful_encrypted");
            Message.SUCCESSFUL_DECRYPTED = (String) message.get("successful_decrypted");
            Message.SUCCESS_BRUTE_FORCE= (String) message.get("successful_brute_force");
        }
    }

    public static class Resource {

        // Подавление создания стандартного конструктора.
        private Resource() {}

        public static String PATH;
        public static String FXML_PATH;
        public static String IMG_PATH;
    }

    public static class File {

        // Подавление создания стандартного конструктора.
        private File() {}

        public static String WRITE_FILE_FORMAT;
        public static String DEFAULT_WRITE_FILE_NAME;
        public static String DEFAULT_WRITE_DIRECTORY_NAME;
    }

    public static class Message {

        // Подавление создания стандартного конструктора.
        private Message() {}

        public static String FILE_NOT_FOUND;
        public static String KEY_IS_EMPTY;
        public static String KEY_IS_INVALID;
        public static String CANNOT_READ;
        public static String CANNOT_WRITE;
        public static String INTERNAL_ERROR;
        public static String SUCCESSFUL_ENCRYPTED;
        public static String SUCCESSFUL_DECRYPTED;
        public static String SUCCESS_BRUTE_FORCE;
    }
}

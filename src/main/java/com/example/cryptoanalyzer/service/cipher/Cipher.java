package com.example.cryptoanalyzer.service.cipher;

import com.example.cryptoanalyzer.service.Validator;
import com.example.cryptoanalyzer.util.AppConstants;
import org.jetbrains.annotations.NotNull;

public final class Cipher {
    private static Cipher instance;

    @NotNull
    private final Validator validator = Validator.getInstance();
    private final String alphabet = new String(AppConstants.ALPHABET);

    // Подавление создания стандартного конструктора.
    private Cipher() {}

    @NotNull
    public static synchronized Cipher getInstance() {
        if (instance == null)
            instance = new Cipher();
        return instance;
    }

    @NotNull
    public String encrypt(@NotNull String text, int key) {
        StringBuilder sb = new StringBuilder();
        for (char ch: text.toCharArray()) {
            final int charIndex = alphabet.indexOf(ch);
            if (charIndex == -1) {
                sb.append(ch);
                continue;
            }
            ch = alphabet.charAt(validator.getValidKey(charIndex + key));
            sb.append(ch);
        }
        return sb.toString();
    }

    public String decrypt(@NotNull String encryptedText, int key) {
        //TODO: Расшифровка.
        return "";
    }
}

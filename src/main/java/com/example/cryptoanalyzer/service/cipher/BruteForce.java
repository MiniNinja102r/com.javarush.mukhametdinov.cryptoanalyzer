package com.example.cryptoanalyzer.service.cipher;

import com.example.cryptoanalyzer.service.Validator;
import com.example.cryptoanalyzer.util.AppConstants;
import org.jetbrains.annotations.NotNull;

public final class BruteForce {
    private static BruteForce instance;

    @NotNull
    private final Validator validator = Validator.getInstance();
    private final String alphabet = new String(AppConstants.ALPHABET);

    // Подавление создания стандартного конструктора.
    private BruteForce() {}

    @NotNull
    public static synchronized BruteForce getInstance() {
        if (instance == null)
            instance = new BruteForce();
        return instance;
    }

    @NotNull
    public String[] decrypt(@NotNull String encryptedText) {
        String[] buffer = new String[alphabet.length()];
        for (int key = 0; key < alphabet.length(); key++) {
            StringBuilder sb = new StringBuilder();
            for (char ch: encryptedText.toCharArray()) {
                final int charIndex = alphabet.indexOf(ch);
                if (charIndex == -1) {
                    sb.append(ch);
                    continue;
                }
                ch = alphabet.charAt(validator.getValidKey(charIndex - key));
                sb.append(ch);
            }
            buffer[key] = sb.toString();
        }
        return buffer;
    }
}

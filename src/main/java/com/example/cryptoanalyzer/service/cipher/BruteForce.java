package com.example.cryptoanalyzer.service.cipher;

import com.example.cryptoanalyzer.service.Validator;
import com.example.cryptoanalyzer.util.AppConstants;
import org.jetbrains.annotations.NotNull;

public final class BruteForce extends Cipher {

    public BruteForce(@NotNull Validator validator, @NotNull String alphabet) {
        super(validator, alphabet);
    }

    public BruteForce(@NotNull Validator validator) {
        super(validator, new String(AppConstants.ALPHABET));
    }

    public BruteForce() {
        super(Validator.getInstance(), new String(AppConstants.ALPHABET));
    }

    @NotNull
    public String[] decrypt(@NotNull String encryptedText) {
        String[] buffer = new String[alphabet.length()];
        for (int key = 0; key < alphabet.length(); key++) {
            buffer[key] = crypt(encryptedText, -key);
        }
        return buffer;
    }
}

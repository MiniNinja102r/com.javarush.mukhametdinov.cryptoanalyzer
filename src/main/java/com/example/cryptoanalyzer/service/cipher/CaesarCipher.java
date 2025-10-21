package com.example.cryptoanalyzer.service.cipher;

import com.example.cryptoanalyzer.service.Validator;
import com.example.cryptoanalyzer.util.AppConstants;
import org.jetbrains.annotations.NotNull;

public final class CaesarCipher extends Cipher {

    public CaesarCipher(@NotNull Validator validator, @NotNull String alphabet) {
        super(validator, alphabet);
    }

    public CaesarCipher(@NotNull Validator validator) {
        super(validator, new String(AppConstants.ALPHABET));
    }

    public CaesarCipher() {
        super(new Validator(), new String(AppConstants.ALPHABET));
    }

    @NotNull
    public String encrypt(@NotNull String text, int key) {
        return crypt(text, key);
    }

    @NotNull
    public String decrypt(@NotNull String encryptedText, int key) {
        return crypt(encryptedText, -key);
    }
}

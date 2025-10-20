package com.example.cryptoanalyzer.service.cipher;

import com.example.cryptoanalyzer.service.Validator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Cipher {
    protected final Validator validator;
    protected final String alphabet;

    @NotNull
    protected String crypt(@NotNull String text, int key) {
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
}

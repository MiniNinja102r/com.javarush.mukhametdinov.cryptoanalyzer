package com.example.cryptoanalyzer.util;

public final class AppConstants {

    // Подавление создания стандартного конструктора.
    private AppConstants() {}

    public static final String APP_NAME = "Zeus";
    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 550;

    public static final char[] ALPHABET = {'а', 'б',
            'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '”', '’',
            ':', '-', '!', '?', ' '};
    public static final int ALPHABET_LENGTH = ALPHABET.length;
}

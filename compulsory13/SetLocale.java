package com;

import java.util.Locale;

public class SetLocale {
    public static Locale execute(String languageTag) {
        return Locale.forLanguageTag(languageTag);
    }
}
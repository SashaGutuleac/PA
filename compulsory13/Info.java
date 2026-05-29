package com;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import java.util.stream.Collectors;

public class Info {
    public static void execute(Locale loc) {
        System.out.println("Country: " + loc.getDisplayCountry() + " (" + loc.getDisplayCountry(loc) + ")");
        System.out.println("Language: " + loc.getDisplayLanguage() + " (" + loc.getDisplayLanguage(loc) + ")");

        try {
            Currency currency = Currency.getInstance(loc);
            System.out.println("Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName(loc) + ")");
        } catch (Exception e) {
            System.out.println("Currency: N/A");
        }

        DateFormatSymbols dfs = DateFormatSymbols.getInstance(loc);
        String days = Arrays.stream(dfs.getWeekdays()).filter(s -> !s.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Week Days: " + days);

        String months = Arrays.stream(dfs.getMonths()).filter(s -> !s.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Months: " + months);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(loc);
        System.out.println("Today: " + LocalDate.now().format(formatter));
    }
}
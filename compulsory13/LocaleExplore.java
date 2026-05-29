package app;

import com.DisplayLocales;
import com.Info;
import com.SetLocale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    private static Locale currentLocale = Locale.getDefault();
    private static ResourceBundle messages = ResourceBundle.getBundle("res.Messages", currentLocale);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(messages.getString("prompt") + " ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                break;
            } else if (command.equals("locales")) {
                System.out.println(messages.getString("locales"));
                DisplayLocales.execute();
            } else if (command.startsWith("set ")) {
                String tag = command.substring(4);
                currentLocale = SetLocale.execute(tag);
                Locale.setDefault(currentLocale);
                messages = ResourceBundle.getBundle("res.Messages", currentLocale);

                String msg = MessageFormat.format(messages.getString("locale.set"), currentLocale.toString());
                System.out.println(msg);
            } else if (command.startsWith("info")) {
                String tag = command.length() > 4 ? command.substring(5).trim() : currentLocale.toLanguageTag();
                Locale loc = Locale.forLanguageTag(tag);

                String msg = MessageFormat.format(messages.getString("info"), loc.toString());
                System.out.println(msg);
                Info.execute(loc);
            } else {
                System.out.println(messages.getString("invalid"));
            }
        }
    }
}
import java.time.LocalDate;

// programatorul extinde persoana
public class Programmer extends Person {
    private boolean prefersDarkTheme; // creativitate un bool despre cum scrie cod

    // constructorul
    public Programmer(String fullName, String userId, LocalDate birthDate, String moodOfTheDay, boolean prefersDarkTheme) {
        super(fullName, userId, birthDate, moodOfTheDay);
        this.prefersDarkTheme = prefersDarkTheme;
    }

    public boolean isPrefersDarkTheme() {
        return prefersDarkTheme;
    }

    @Override
    public String toString() {
        String tema = prefersDarkTheme ? "da" : "nu";
        return "programator: " + getDisplayName() + " (foloseste dark mode: " + tema + ")";
    }
}
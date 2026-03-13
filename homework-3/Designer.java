import java.time.LocalDate;

// designerul extinde persoana
public class Designer extends Person {
    private String favoriteHexColor; // creativitate designerii sunt inrteresati de codurile de culori

    // constructor
    public Designer(String fullName, String userId, LocalDate birthDate, String moodOfTheDay, String favoriteHexColor) {
        super(fullName, userId, birthDate, moodOfTheDay);
        this.favoriteHexColor = favoriteHexColor;
    }

    public String getFavoriteHexColor() {
        return favoriteHexColor;
    }

    @Override
    public String toString() {
        return "designer: " + getDisplayName() + " (culoare preferata: " + favoriteHexColor + ")";
    }
}
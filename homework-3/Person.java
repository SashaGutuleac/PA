import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// clasa de baza pentru o persoana
public class Person implements Profile {
    private String fullName;
    private String userId;
    private LocalDate birthDate;
    private String moodOfTheDay; // creativitate statusul zilnic din retelele sociale
    private Map<Profile, String> relationships;

    // constructor
    public Person(String fullName, String userId, LocalDate birthDate, String moodOfTheDay) {
        this.fullName = fullName;
        this.userId = userId;
        this.birthDate = birthDate;
        this.moodOfTheDay = moodOfTheDay;
        this.relationships = new HashMap<>();
    }

    @Override
    public String getUniqueId() {
        return userId;
    }

    @Override
    public String getDisplayName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getMoodOfTheDay() {
        return moodOfTheDay;
    }

    public Map<Profile, String> getRelationships() {
        return relationships;
    }

    // metoda ca sa adaugam usor o relatie
    public void addRelationship(Profile otherProfile, String relationshipType) {
        this.relationships.put(otherProfile, relationshipType);
    }

    @Override
    public String toString() {
        return "persoana: " + fullName + " (stare azi: " + moodOfTheDay + ")";
    }
}
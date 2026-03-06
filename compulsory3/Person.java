/**
 * reprezinta o persoana din reteaua de socializare.
 */
public class Person implements Profile, Comparable<Person> {
    private String fullName;
    private String userId;

    /**
     * creeaza o noua persoana cu numele si id-ul specificate.
     *
     * @param fullName numele persoanei
     * @param userId id-ul unic al persoanei
     */
    public Person(String fullName, String userId) {
        this.fullName = fullName;
        this.userId = userId;
    }

    /**
     * returneaza numele persoanei.
     *
     * @return numele persoanei
     */
    @Override
    public String getDisplayName() {
        return fullName;
    }

    /**
     * returneaza id-ul profilului persoanei.
     *
     * @return id-ul profilului
     */
    @Override
    public String getUniqueId() {
        return userId;
    }

    /**
     * compara aceasta persoana cu alta, in functie de nume.
     *
     * @param otherPerson cealalta persoana cu care se face comparatia
     * @return un numar negativ, zero, sau un numar pozitiv daca numele e mai mic, egal sau mai mare
     */
    @Override
    public int compareTo(Person otherPerson) {
        if (this.fullName == null) return (otherPerson.fullName == null) ? 0 : -1;
        if (otherPerson.fullName == null) return 1;
        return this.fullName.compareTo(otherPerson.fullName);
    }

    /**
     * returneaza reprezentarea sub forma de sir de caractere a persoanei.
     *
     * @return un text care descrie persoana
     */
    @Override
    public String toString() {
        return "Person{name='" + fullName + "', profileId='" + userId + "'}";
    }
}
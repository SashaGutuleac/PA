/**
 * reprezinta o companie din reteaua de socializare.
 */
public class Company implements Profile, Comparable<Company> {
    private String corpName;
    private String corpId;

    /**
     * creeaza o noua companie cu numele si id-ul specificate.
     *
     * @param corpName numele companiei
     * @param corpId id-ul unic al companiei
     */
    public Company(String corpName, String corpId) {
        this.corpName = corpName;
        this.corpId = corpId;
    }

    /**
     * returneaza numele companiei.
     *
     * @return numele companiei
     */
    @Override
    public String getDisplayName() {
        return corpName;
    }

    /**
     * returneaza id-ul profilului companiei.
     *
     * @return id-ul profilului
     */
    @Override
    public String getUniqueId() {
        return corpId;
    }

    /**
     * compara aceasta companie cu alta, in functie de nume.
     *
     * @param otherCorp cealalta companie cu care se face comparatia
     * @return un numar negativ, zero, sau un numar pozitiv daca numele e mai mic, egal sau mai mare
     */
    @Override
    public int compareTo(Company otherCorp) {
        if (this.corpName == null) return (otherCorp.corpName == null) ? 0 : -1;
        if (otherCorp.corpName == null) return 1;
        return this.corpName.compareTo(otherCorp.corpName);
    }

    /**
     * returneaza reprezentarea sub forma de sir de caractere a companiei.
     *
     * @return un text care descrie compania
     */
    @Override
    public String toString() {
        return "Company{name='" + corpName + "', profileId='" + corpId + "'}";
    }
}
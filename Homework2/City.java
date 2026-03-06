/**
 * clasa ce este o clasa de tip oras.
 * info specifice orasului: populatie
 */
public final class City extends Location {
    private int citizenCount;

    /**
     * constructor pt city.
     * @param locName Numele orasului
     * @param xCoord Coordonata X
     * @param yCoord Coordonata Y
     * @param citizenCount Populatia
     */
    public City(String locName, double xCoord, double yCoord, int citizenCount) {
        super(locName, xCoord, yCoord);
        this.citizenCount = citizenCount;
    }

    /**
     * returneaza populatia orasului.
     * @return populatia
     */
    public int getPopulation() {
        return citizenCount;
    }

    /**
     * seteaza populatia orasului.
     * @param citizenCount populatia
     */
    public void setPopulation(int citizenCount) {
        this.citizenCount = citizenCount;
    }
}
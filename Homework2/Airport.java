/**
 * clasa ce reprezinta o locatie de tip Aeroport (Airport).
 * informatii specifice clasei: numarul de terminale.
 */
public final class Airport extends Location {
    private int terminalCount;

    /**
     * constructor pentru Airport.
     * @param locName Numele aeroportului
     * @param xCoord Coordonata X
     * @param yCoord Coordonata Y
     * @param terminalCount Numarul de terminale
     */
    public Airport(String locName, double xCoord, double yCoord, int terminalCount) {
        super(locName, xCoord, yCoord);
        this.terminalCount = terminalCount;
    }

    /**
     * returneaza nr de terminale
     * @return Numar terminale
     */
    public int getTerminals() {
        return terminalCount;
    }

    /**
     * seteaza nr. de terminale.
     * @param terminalCount numar terminale
     */
    public void setTerminals(int terminalCount) {
        this.terminalCount = terminalCount;
    }
}
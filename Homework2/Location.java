import java.util.Objects;

/**
 * clasa sealed. restrictionam mostenirea
 */
public abstract sealed class Location permits City, Airport, GasStation {
    private String locName;
    private double xCoord;
    private double yCoord;

    /**
     * constructor pt o locatie
     * @param locName Numele locatiei
     * @param xCoord Coordonata X
     * @param yCoord Coordonata Y
     */
    public Location(String locName, double xCoord, double yCoord) {
        this.locName = locName;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * return num locatiei
     * @return nume
     */
    public String getName() {
        return locName;
    }

    /**
     * return coordonata x
     * @return coordonata X
     */
    public double getX() {
        return xCoord;
    }

    /**
     * return coordonata Y
     * @return coordonata Y
     */
    public double getY() {
        return yCoord;
    }

    /**
     * Setam numele locatiei
     * @param locName noul nume
     */
    public void setName(String locName) {
        this.locName = locName;
    }

    /**
     * Setam coordonata x
     * @param xCoord noua coordonata x
     */
    public void setX(double xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * setam coordonata y a locatiei
     * @param yCoord noua coordonata Y
     */
    public void setY(double yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * sw verifica egalitatea locatillor bazata pe coordonate
     * @param o Obiectul cu care se compara
     * @return true daca sunt acceasi locatie
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location otherLoc = (Location) o;
        return Double.compare(otherLoc.xCoord, xCoord) == 0 && Double.compare(otherLoc.yCoord, yCoord) == 0 && Objects.equals(locName, otherLoc.locName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locName, xCoord, yCoord);
    }

    @Override
    public String toString() {
        return "Location{name='" + locName + "', x=" + xCoord + ", y=" + yCoord + "}";
    }
}
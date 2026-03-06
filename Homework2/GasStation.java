/**
 * clasa benzinarie
 * informatii specifice clasei pretul combustibilului
 */
public final class GasStation extends Location {
    private double fuelPrice;

    /**
     * constructor pentru GasStation.
     * @param locName numele
     * @param xCoord coordonata X
     * @param yCoord coordonata Y
     * @param fuelPrice pretul combustibil
     */
    public GasStation(String locName, double xCoord, double yCoord, double fuelPrice) {
        super(locName, xCoord, yCoord);
        this.fuelPrice = fuelPrice;
    }

    /**
     * returneaza pretul combustibilului
     * @return pretul combustibil
     */
    public double getGasPrice() {
        return fuelPrice;
    }

    /**
     * seteaza pretul combustibilului.
     * @param fuelPrice pretul combustibil
     */
    public void setGasPrice(double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }
}
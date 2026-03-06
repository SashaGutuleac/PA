/**
 * enum ce listeaza tipurile posibile de drumuri.
 */
public enum RoadType {
    /** tipul autostrada, limita 130 km/h. */
    HIGHWAY(130, "Highway"),
    /** tipul drum expres, limita 100 km/h. */
    EXPRESS(100, "Express road"),
    /** tipul drum national, limita 90 km/h. */
    NATIONAL(90, "National road"),
    /** tipul drum judetean/comunal, limita 70 km/h. */
    COUNTRY(70, "Country road");

    private final int baseSpeedLimit;
    private final String roadLabel;

    RoadType(int baseSpeedLimit, String roadLabel) {
        this.baseSpeedLimit = baseSpeedLimit;
        this.roadLabel = roadLabel;
    }

    /**
     * returneaza limita de viteza prestabilita pentru acest tip de drum.
     * @return limita de viteza implicita
     */
    public int getDefaultSpeedLimit() {
        return baseSpeedLimit;
    }

    @Override
    public String toString() {
        return roadLabel;
    }
}
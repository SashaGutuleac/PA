import java.util.Objects;

/**
 * clasa ce reprezinta un drum (Road) intre doua locatii
 */
public class Road {
    private RoadType roadCategory;
    private double roadDistance;
    private double maxSpeedLimit;
    private Location startNode;
    private Location endNode;

    /**
     * constructor pentru a crea un drum nou.
     * @param roadCategory Tipul drumului din enum-ul RoadType
     * @param roadDistance Lungimea drumului
     * @param maxSpeedLimit Limita de viteza pe acest drum
     * @param startNode Prima locatie
     * @param endNode A doua locatie
     */
    public Road(RoadType roadCategory, double roadDistance, double maxSpeedLimit, Location startNode, Location endNode) {
        this.roadCategory = roadCategory;
        this.maxSpeedLimit = maxSpeedLimit;
        this.startNode = startNode;
        this.endNode = endNode;

        double calculatedMin = distance(startNode, endNode);
        if (roadDistance < calculatedMin) {
            throw new IllegalArgumentException("Length cannot be smaller than euclidean distance!");
        }
        this.roadDistance = roadDistance;
    }

    /**
     * returneaza tipul drumului.
     * @return tipul drumului
     */
    public RoadType getType() {
        return roadCategory;
    }

    /**
     * modifica tipul drumului.
     * Daca noua limita de viteza prestabilita a tipului de drum e mai mica,
     * viteza curenta a drumului se va ajusta corespunzator
     * @param roadCategory noul tip de drum
     */
    public void setType(RoadType roadCategory) {
        this.roadCategory = roadCategory;
        if (this.maxSpeedLimit > roadCategory.getDefaultSpeedLimit()) {
            this.maxSpeedLimit = roadCategory.getDefaultSpeedLimit();
        }
    }

    /**
     * returneaza lungimea curenta a drumului.
     * @return lungimea
     */
    public double getLength() {
        return roadDistance;
    }

    /**
     * modifica lungimea drumului, validand ca ea sa fie macar egala cu distanta ecuclidiana
     * @param roadDistance noua lungime
     */
    public void setLength(double roadDistance) {
        double calculatedMin = distance(startNode, endNode);
        if (roadDistance < calculatedMin) {
            throw new IllegalArgumentException("Length cannot be smaller than euclidean distance!");
        }
        this.roadDistance = roadDistance;
    }

    /**
     * returneaza limita de viteza a drumului
     * @return limita de viteza
     */
    public double getSpeedLimit() {
        return maxSpeedLimit;
    }

    /**
     * Modifica limita de viteza.
     * @param maxSpeedLimit noua limita
     */
    public void setSpeedLimit(double maxSpeedLimit) {
        if (maxSpeedLimit > roadCategory.getDefaultSpeedLimit()) {
            throw new IllegalArgumentException("Speed limit too high for this road type!");
        }
        this.maxSpeedLimit = maxSpeedLimit;
    }

    /**
     * Intoarce prima locatie a drumului.
     * @return prima locatie
     */
    public Location getA() {
        return startNode;
    }

    /**
     * Intoarce a doua locatie a drumului.
     * @return a doua locatie
     */
    public Location getB() {
        return endNode;
    }

    /**
     * calculeaza distanta cea mai scurt dintre doua locatii.
     * @param loc1 Prima locatie
     * @param loc2 A doua locatie
     * @return distanta
     */
    public static double distance(Location loc1, Location loc2) {
        double deltaX = loc1.getX() - loc2.getX();
        double deltaY = loc1.getY() - loc2.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * doua drumuri sunt egale daca au acelasi tip si aceleasi locatii de capat
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road otherRoad = (Road) o;
        return roadCategory == otherRoad.roadCategory &&
                ((Objects.equals(startNode, otherRoad.startNode) && Objects.equals(endNode, otherRoad.endNode)) ||
                        (Objects.equals(startNode, otherRoad.endNode) && Objects.equals(endNode, otherRoad.startNode)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(startNode, endNode) + Objects.hash(endNode, startNode);
    }

    @Override
    public String toString() {
        return "Road{" +
                "type='" + roadCategory + '\'' +
                ", length=" + roadDistance +
                ", speedLimit=" + maxSpeedLimit +
                ", from=" + startNode.getName() +
                ", to=" + endNode.getName() +
                '}';
    }
}
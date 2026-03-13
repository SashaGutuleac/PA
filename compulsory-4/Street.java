// clasa care reprezinta o strada ce uneste doua intersectii
public class Street implements Comparable<Street> {
    private String name;
    private double length;
    private Intersection nodeA;
    private Intersection nodeB;

    // constructor
    public Street(String name, double length, Intersection nodeA, Intersection nodeB) {
        this.name = name;
        this.length = length;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    // facem strazile comparabile, ordinea naturala o punem dupa nume
    @Override
    public int compareTo(Street other) {
        if (this.name == null) return (other.name == null) ? 0 : -1;
        if (other.name == null) return 1;
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "strada " + name + " (lungime: " + length + ", leaga " + nodeA + " de " + nodeB + ")";
    }
}
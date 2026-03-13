import java.util.Objects;

// clasa care reprezinta o intersectie
public class Intersection implements Comparable<Intersection> {
    private String name;

    // constructor
    public Intersection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // suprascriem equals si hashcode.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection that = (Intersection) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // ordinea naturala a intersectiilor o facem alfabetic dupa nume
    @Override
    public int compareTo(Intersection other) {
        if (this.name == null) return (other.name == null) ? 0 : -1;
        if (other.name == null) return 1;
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
import java.util.Objects;

// clasa simpla pentru intersectii
public class Intersectie {
    private String nume;

    public Intersectie(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    //  ca sa nu avem dubluri cand folosim set-uri sau jgrapht
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersectie that = (Intersectie) o;
        return Objects.equals(nume, that.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume);
    }

    @Override
    public String toString() {
        return nume;
    }
}
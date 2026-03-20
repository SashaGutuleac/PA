// clasa pentru strazi
public class Strada {
    private String nume;
    private double lungime;
    private Intersectie capatA;
    private Intersectie capatB;

    public Strada(String nume, double lungime, Intersectie capatA, Intersectie capatB) {
        this.nume = nume;
        this.lungime = lungime;
        this.capatA = capatA;
        this.capatB = capatB;
    }

    public String getNume() {
        return nume;
    }

    public double getLungime() {
        return lungime;
    }

    public Intersectie getCapatA() {
        return capatA;
    }

    public Intersectie getCapatB() {
        return capatB;
    }

    @Override
    public String toString() {
        return nume + " (" + lungime + ")";
    }
}
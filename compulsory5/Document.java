import java.io.File;

// clasa care reprezinta o resursa adica un fisier
public class Document {
    private String nume;
    private String caleFisier; // calea fisierului pe disc

    public Document(String nume, String caleFisier) {
        this.nume = nume;
        this.caleFisier = caleFisier;
    }

    public String getNume() {
        return nume;
    }

    public String getCaleFisier() {
        return caleFisier;
    }
}
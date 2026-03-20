import java.util.ArrayList;
import java.util.List;

// clasa care tine toate resursele la un loc ca un dosar
public class Repository {
    private List<Document> resurse = new ArrayList<>();

    // metoda sa adaugam un document nou in lista
    public void adaugaResursa(Document doc) {
        resurse.add(doc);
    }

    public List<Document> getResurse() {
        return resurse;
    }
}
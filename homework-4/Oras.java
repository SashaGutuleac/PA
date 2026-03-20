import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// clasa care aduna la un loc strazile si intersectiile
public class Oras {
    private List<Intersectie> intersectii = new ArrayList<>();
    private List<Strada> strazi = new ArrayList<>();

    public void adaugaIntersectie(Intersectie i) {
        intersectii.add(i);
    }

    public void adaugaStrada(Strada s) {
        strazi.add(s);
    }

    public List<Intersectie> getIntersectii() {
        return intersectii;
    }

    public List<Strada> getStrazi() {
        return strazi;
    }

    // o metoda ajutatoare ca sa vedem cate strazi sunt intr-o intersectie
    public int numaraStraziInIntersectie(Intersectie i) {
        int numar = 0;
        for (Strada s : strazi) {
            if (s.getCapatA().equals(i) || s.getCapatB().equals(i)) {
                numar++;
            }
        }
        return numar;
    }

    // rezolvarea cerintei cu stream-uri
    public void afiseazaStraziLungiSiConectate(double lungimeMinima) {
        System.out.println("--- strazi mai lungi de " + lungimeMinima + " care unesc cel putin 3 alte strazi ---");

        // folosim stream ca sa filtram rapid lista
        List<Strada> rezultat = strazi.stream()
                .filter(s -> s.getLungime() > lungimeMinima) // regula 1: e mai lunga decat am cerut
                .filter(s -> {
                    //  numaram cate strazi se unesc la ea
                    // scadem 1 la fiecare capat ca sa o ignoram pe ea insasi din numaratoare
                    int veciniA = numaraStraziInIntersectie(s.getCapatA()) - 1;
                    int veciniB = numaraStraziInIntersectie(s.getCapatB()) - 1;

                    // verificam daca are in total la capete minim 3 alte strazi conectate
                    return (veciniA + veciniB) >= 3;
                })
                .collect(Collectors.toList());

        // afisam ce am gasit
        if (rezultat.isEmpty()) {
            System.out.println("nu s-a gasit nicio strada de genul asta.");
        } else {
            rezultat.forEach(s -> System.out.println(s.getNume()));
        }
    }
}
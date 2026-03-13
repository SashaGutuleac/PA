import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// punctul de intrare in program
public class Main {
    public static void main(String[] args) {

        // 1. generam 10 intersectii folosind stream-uri (fix cum cere cerinta)
        // rangeClosed(1, 10) numara de la 1 la 10 si creeaza un "node1", "node2" etc.
        List<Intersection> nodes = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new Intersection("node" + i))
                .collect(Collectors.toList());

        // 2. cream o lista de strazi folosind o implementare de linkedlist
        List<Street> streets = new LinkedList<>();

        // bagam cateva strazi de test care leaga intersectiile de mai sus
        streets.add(new Street("s1", 2.5, nodes.get(0), nodes.get(1)));
        streets.add(new Street("s2", 1.2, nodes.get(0), nodes.get(2)));
        streets.add(new Street("s3", 3.8, nodes.get(1), nodes.get(3)));
        streets.add(new Street("s4", 0.9, nodes.get(2), nodes.get(4)));

        System.out.println("--- strazi inainte de sortare ---");
        for (Street s : streets) {
            System.out.println(s);
        }

        // 3. sortam strazile dupa lungime, folosind method reference
        streets.sort(Comparator.comparing(Street::getLength));

        System.out.println("\n--- strazi dupa sortarea dupa lungime ---");
        for (Street s : streets) {
            System.out.println(s);
        }

        // 4. lucram cu hashset pentru a demonstra ca nu exista duplicate
        Set<Intersection> intersectionSet = new HashSet<>();

        // adaugam toate cele 10 intersectii initiale
        intersectionSet.addAll(nodes);
        System.out.println("\nmarimea setului de intersectii: " + intersectionSet.size());

        // cream o intersectie clonata, care are acelasi nume ("node1") ca prima
        Intersection duplicateNode = new Intersection("node1");

        // incercam sa o adaugam in set
        boolean isAdded = intersectionSet.add(duplicateNode);

        // afisam rezultatul ca sa demonstrak ca set-ul a refuzat-o
        System.out.println("am reusit sa adaugam duplicatul? " + isAdded);
        System.out.println("marimea setului dupa incercare (ar trebui sa fie tot 10): " + intersectionSet.size());
    }
}
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * clasa principala care contine executia aplicatiei.
 */
public class Main {
    /**
     * constructor implicit pentru clasa principala.
     */
    public Main() {
    }

    /**
     * punctul de intrare in aplicatie.
     * creeaza elementele retelei si le sorteaza.
     *
     * @param args argumentele furnizate la linia de comanda
     */
    public static void main(String[] args) {
        Person user1 = new Person("Gigel", "Person1");
        Person user2 = new Person("Alex", "Person2");
        Person user3 = new Person("Stefan", "Person3");

        Company corp1 = new Company("Endawa", "Company1");
        Company corp2 = new Company("Microsoft", "Company2");
        Company corp3 = new Company("Nvidia", "Company3");

        List<Profile> socialNetwork = new ArrayList<>();
        socialNetwork.add(user1);
        socialNetwork.add(corp1);
        socialNetwork.add(user2);
        socialNetwork.add(corp2);
        socialNetwork.add(user3);
        socialNetwork.add(corp3);

        System.out.println(user1);
        System.out.println("Before sorting:");
        for (Profile account : socialNetwork) {
            System.out.println(account);
        }

        // sortam lista alfabetic folosind numele de afisare din interfata
        socialNetwork.sort(Comparator.comparing(Profile::getDisplayName));

        System.out.println("\nAfter sorting by name:");
        for (Profile account : socialNetwork) {
            System.out.println(account);
        }
    }
}
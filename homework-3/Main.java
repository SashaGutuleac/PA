import java.time.LocalDate;

// clasa principala de unde rulam totul
public class Main {
    public static void main(String[] args) {
        // 1. instantiem cu proprietatile noastre mai originale
        Company google = new Company("Google", "C1", "dinozaurul de pe net cand n-ai net");
        Company github = new Company("GitHub", "C2", "Pisica (cat)");

        // true pentru dark theme
        Programmer alex = new Programmer("Alex", "P1", LocalDate.of(2000, 5, 20), "axat pe cod", true);
        // #FF5733 este un portocaliu aprins
        Designer maria = new Designer("Maria", "P2", LocalDate.of(1998, 3, 15), "creativa", "#FF5733");
        Person ion = new Person("Ion", "P3", LocalDate.of(1990, 10, 10), "relaxat");

        // 2. setam relatiile din map-uri
        alex.addRelationship(google, "angajat");
        alex.addRelationship(maria, "coleg");
        maria.addRelationship(github, "isi tine portofoliul acolo");
        ion.addRelationship(alex, "frate");
        ion.addRelationship(google, "cauta retete");

        // 3. bagam totul in retea
        SocialNetwork network = new SocialNetwork();
        network.addProfile(google);
        network.addProfile(github);
        network.addProfile(alex);
        network.addProfile(maria);
        network.addProfile(ion);

        // 4. sortam lista in functie de importanta
        network.getProfiles().sort((prof1, prof2) -> {
            int importanta1 = network.computeImportance(prof1);
            int importanta2 = network.computeImportance(prof2);
            return Integer.compare(importanta2, importanta1);
        });

        // 5. afisam rezultatul pe ecran
        System.out.println("--- reteaua sortata dupa importanta ---");
        for (Profile p : network.getProfiles()) {
            int imp = network.computeImportance(p);
            System.out.println(p.getDisplayName() + " (importanta: " + imp + ") -> " + p.toString());
        }
    }
}
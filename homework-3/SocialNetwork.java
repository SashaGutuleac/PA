import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// clasa care gestioneaza toata reteaua
public class SocialNetwork {
    private List<Profile> profiles;

    public SocialNetwork() {
        this.profiles = new ArrayList<>();
    }

    // adauga un profil in lista retelei
    public void addProfile(Profile p) {
        if (!profiles.contains(p)) {
            profiles.add(p);
        }
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    // calculeaza importanta numarand conexiunile
    public int computeImportance(Profile target) {
        // folosim un set ca sa nu numaram de doua ori aceeasi conexiune
        Set<Profile> uniqueConnections = new HashSet<>();

        // ' daca e persoana, adaugam in set toti oamenii/companiile pe care ii cunoaste direct
        if (target instanceof Person) {
            uniqueConnections.addAll(((Person) target).getRelationships().keySet());
        }

        //''  ne uitam prin toata reteaua sa vedem cine l-a adaugat pe target in lista lui
        for (Profile p : profiles) {
            if (p instanceof Person) {
                Person person = (Person) p;
                if (person.getRelationships().containsKey(target)) {
                    uniqueConnections.add(p);
                }
            }
        }

        // importanta e efectiv marimea setului (numarul de noduri conectate la el)
        return uniqueConnections.size();
    }
}
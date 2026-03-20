import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // clasa faker si generam orasul gol
        Faker faker = new Faker();
        Random rand = new Random();
        Oras oras = new Oras();

        //  facem 6 intersectii cu nume complet false si random
        List<Intersectie> intersectii = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            // faker genereaza aici nume in engleza gen times square sau ceva de genu
            Intersectie nod = new Intersectie(faker.address().firstName() + " Square");
            intersectii.add(nod);
            oras.adaugaIntersectie(nod);
        }

        //  tragem strazi intre ele cu nume de drumuri false main Street
        for (int i = 0; i < 12; i++) {
            // luam 2 intersectii la nimereala
            Intersectie a = intersectii.get(rand.nextInt(intersectii.size()));
            Intersectie b = intersectii.get(rand.nextInt(intersectii.size()));

            // ne asiguram ca nu prindem de doua ori aceeasi intersectie ca pica algoritmul
            while (a.equals(b)) {
                b = intersectii.get(rand.nextInt(intersectii.size()));
            }

            // dam o lungime de la 1 la 10 si rotunjim
            double lungime = 1.0 + (9.0 * rand.nextDouble());
            lungime = Math.round(lungime * 10.0) / 10.0;

            // bagam strada cu numele ei fals in oras
            Strada stradaNoua = new Strada(faker.address().streetName(), lungime, a, b);
            oras.adaugaStrada(stradaNoua);
        }

        System.out.println("orasul random a fost construit cu succes!");

        //  testam daca ne afiseaza ce trebuie la cerinta cu stream-uri
        oras.afiseazaStraziLungiSiConectate(3.0);

        //  testam algoritmul de la jgrapht vrem o lista cu primele 3 solutii de drumuri posibile in functie de cost
        Algoritm alg = new Algoritm(oras);
        alg.afiseazaSolutii(3);
    }
}
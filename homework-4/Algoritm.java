import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// algoritmul care gaseste solutiile folosind libraria externa JGraphT
public class Algoritm {
    private Oras oras;

    public Algoritm(Oras oras) {
        this.oras = oras;
    }

    // construieste graful pe baza orasului, dar poate ignora o strada anume
    private SpanningTree<DefaultWeightedEdge> obtineArboreFaraOStrada(Strada stradaIgnorata) {
        // facem un graf gol de la jgrapht
        Graph<Intersectie, DefaultWeightedEdge> graf = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // punem toate nodurile intersectiil
        for (Intersectie i : oras.getIntersectii()) {
            graf.addVertex(i);
        }

        // punem toate muchiile strazile
        for (Strada s : oras.getStrazi()) {
            // daca am primit comanda sa ignoram o strada, sarim peste ea
            if (stradaIgnorata != null && s.equals(stradaIgnorata)) {
                continue;
            }

            // tragem linia intre intersectii si ii punem costul lungimea
            DefaultWeightedEdge muchie = graf.addEdge(s.getCapatA(), s.getCapatB());
            if (muchie != null) {
                graf.setEdgeWeight(muchie, s.getLungime());
            }
        }

        // aplicam kruskal ca sa gasim reteaua minima
        KruskalMinimumSpanningTree<Intersectie, DefaultWeightedEdge> kruskal = new KruskalMinimumSpanningTree<>(graf);
        return kruskal.getSpanningTree();
    }

    // metoda principala cerut in tema
    public void afiseazaSolutii(int numarSolutii) {
        System.out.println("\n //// cautam cele mai bune " + numarSolutii + " solutii posibile de cost minim //////");

        // gasim solutia suprema costul absolut cel mai mic fara sa ignoram nicio strada
        SpanningTree<DefaultWeightedEdge> solutiaIdeala = obtineArboreFaraOStrada(null);

        // facem o lista in care adunam solutiile gasite si bagam prima solutie
        List<SpanningTree<DefaultWeightedEdge>> toateSolutiile = new ArrayList<>();
        toateSolutiile.add(solutiaIdeala);

        //  pur si simplu scoatem pe rand cate o strada si vedem ce iese
        for (Strada s : oras.getStrazi()) {
            // calculam reteaua daca am inchide strada curenta
            SpanningTree<DefaultWeightedEdge> solutieAlternativa = obtineArboreFaraOStrada(s);

            // daca reteaua gasita inca leaga tot orasul si nu a ramas vreo intersectie izolata pe afara
            if (solutieAlternativa.getEdges().size() == oras.getIntersectii().size() - 1) {
                // verificam sa nu o fi gasit deja in alta trecere
                boolean existaDeja = toateSolutiile.stream()
                        .anyMatch(sol -> sol.getWeight() == solutieAlternativa.getWeight() && sol.getEdges().equals(solutieAlternativa.getEdges()));

                if (!existaDeja) {
                    toateSolutiile.add(solutieAlternativa);
                }
            }
        }

        // 3. sortam toate solutiile dupa cost
        toateSolutiile.sort(Comparator.comparingDouble(SpanningTree::getWeight));

        // 4. le afisam pe primele numarsolutii
        for (int i = 0; i < Math.min(numarSolutii, toateSolutiile.size()); i++) {
            System.out.println("solutia nr- " + (i + 1) + " are un cost total de " + toateSolutiile.get(i).getWeight());
        }
    }
}
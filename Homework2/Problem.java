import java.util.ArrayList;

/**
 * reprezinta o instanta a problemei, ce contine locatii si drumuri.
 * deonstreaza ca nu se pot adauga doua locatii in aceleasi cordonate cu acelasi nume
 */
public class Problem {
    private ArrayList<Location> allLocations = new ArrayList<>();
    private ArrayList<Road> allRoads = new ArrayList<>();

    /**
     * constructorul
     */
    public Problem() {
    }

    /**
     * se adauga o locatie in problema
     * @param newLocation lacatia care ar trebuie a fie adaugata deja
     */
    public void addLocation(Location newLocation) {
        if (!allLocations.contains(newLocation)) {
            allLocations.add(newLocation);
        }
    }

    /**
     * adaug drum daca nu exidta deva
     * @param newRoad calea care trebuie sa fie adaugata deja
     */
    public void addRoad(Road newRoad) {
        if (!allRoads.contains(newRoad)) {
            allRoads.add(newRoad);
        }
    }

    /**
     * verificam daca instnta este valida este valida
     * problema este valida daca nu exista copii la drumuri(duplicate)
     * @return returnam ori true ori false
     */
    public boolean isValid() {
        for(int idx = 0; idx < allLocations.size(); idx++) {
            for(int nextIdx = idx + 1; nextIdx < allLocations.size(); nextIdx++) {
                if(allLocations.get(idx).equals(allLocations.get(nextIdx))) return false;
            }
        }
        for(int rIdx = 0; rIdx < allRoads.size(); rIdx++) {
            for(int rNextIdx = rIdx + 1; rNextIdx < allRoads.size(); rNextIdx++) {
                if(allRoads.get(rIdx).equals(allRoads.get(rNextIdx))) return false;
            }
        }
        for (Road path : allRoads) {
            if (!allLocations.contains(path.getA()) || !allLocations.contains(path.getB())) {
                return false;
            }
        }
        return true;
    }

    /**
     * determina daca exista o cale intre doua locatii folosind DFS
     * @param source locatia de start
     * @param destination locatia destinatie
     * @return true daca exista cale intre start si end, false altfel
     */
    public boolean isReachable(Location source, Location destination) {
        if (!allLocations.contains(source) || !allLocations.contains(destination)) {
            return false;
        }

        ArrayList<Location> traversedNodes = new ArrayList<>();
        return dfs(source, destination, traversedNodes);
    }

    /**
     * algoritmul intern DFS pentru determinarea existentei unei cai.
     * @param currentNode locatia curenta
     * @param targetNode locatia destinatie
     * @param traversedNodes lista locatiilor deja vizitate
     * @return true daca se ajunge la tinta, false altfel
     */
    private boolean dfs(Location currentNode, Location targetNode, ArrayList<Location> traversedNodes) {
        if (currentNode.equals(targetNode)) return true;
        traversedNodes.add(currentNode); //* daca nu e destinatia adaug nodul curent in lista nodurilor traversate*/

        for (Road route : allRoads) {
            Location adjacentLoc = null;
            if (route.getA().equals(currentNode)) {
                adjacentLoc = route.getB();
            } else if (route.getB().equals(currentNode)) {
                adjacentLoc = route.getA();
            }

            if (adjacentLoc != null && !traversedNodes.contains(adjacentLoc)) {
                if (dfs(adjacentLoc, targetNode, traversedNodes)) {
                    return true;
                }
            }
        }
        return false;
    }
}
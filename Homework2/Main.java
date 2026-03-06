/**
 * clasa principala a aplicatiei.
 */
public class Main {
    /**
     * constructor pt clasa principala
     */
    public Main() {
    }

    /**
     * @param args argumentele pe care le dam
     */
    public static void main(String[] args) {
        City town1 = new City("Iasi", 0, 0, 300000);
        City town2 = new City("Bucuresti", 300, 200, 2000000);
        City town3 = new City("Cluj", 100, 400, 350000);

        Problem routingProblem = new Problem();
        routingProblem.addLocation(town1);
        routingProblem.addLocation(town2);
        routingProblem.addLocation(town3);

        double distCalc = Road.distance(town1, town2);
        Road routeA = new Road(RoadType.HIGHWAY, distCalc + 20, 130, town1, town2);
        Road routeB = new Road(RoadType.EXPRESS, Road.distance(town2, town3) + 10, 100, town2, town3);

        routingProblem.addRoad(routeA);
        routingProblem.addRoad(routeB);

        System.out.println("Problem is valid: " + routingProblem.isValid());
        System.out.println("Path from Iasi to Bucuresti exists: " + routingProblem.isReachable(town1, town2));

        try {
            routeA.setLength(10);
        } catch (IllegalArgumentException exc) {
            System.out.println("Eroare corectă: " + exc.getMessage());
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Location loc1 = new Location("Bucuresti", "oras", 44.43, 26.10);
        Location loc2 = new Location("Constanta", "Oras", 44.18, 28.63);

        Road drum1 = new Road("Autostrada soarelui", "autostrada", 202);
        Road drum2 = new Road("Transfagarasa", "Drum national", 151);

        System.out.println(loc1);
        System.out.println(loc2);
        System.out.println(drum1);
        System.out.println(drum2);
    }
}
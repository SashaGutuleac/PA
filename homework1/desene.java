public class desene {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("pune argumentele");
            return;
        }

        int n = Integer.parseInt(args[0]);
        String formaaleasa = args[1];

        long timpStart = System.nanoTime();

        int[][] matrice = new int[n][n];

        if (formaaleasa.equals("rectangle")) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrice[i][j] = 255;
                }
            }

            int inceput = n / 4;
            int sfarsit = (n * 3) / 4;

            for (int i = inceput; i < sfarsit; i++) {
                for (int j = inceput; j < sfarsit; j++) {
                    matrice[i][j] = 0;
                }
            }

        } else if (formaaleasa.equals("circle")) {
            int centru = n / 2;
            int raza = n / 3;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int distantaX = (i - centru) * (i - centru);
                    int distantaY = (j - centru) * (j - centru);

                    if (distantaX + distantaY <= (raza * raza)) {
                        matrice[i][j] = 255;
                    }
                }
            }
        } else {
            System.out.println("ai pus gresit argumentel");
            return;
        }

        long timpFinal = System.nanoTime();
        long durata = (timpFinal - timpStart) / 1000000;


        System.out.println("a durat: " + durata + " milisecunde.");


        if (n <= 100) {
            afiseaza(matrice, n);
        }
    }

    public static void afiseaza(int[][] matrice, int n) {
        StringBuilder textImagine = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrice[i][j] == 0) {
                    textImagine.append("  ");
                } else {
                    textImagine.append("##");
                }
            }
            textImagine.append("\n");
        }
        System.out.println(textImagine.toString());
    }
}
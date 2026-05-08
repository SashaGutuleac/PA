package org.example; // ajusteaza daca ai alt pachet

import java.util.Random;

// clasa principala care porneste toata nebunia
public class Lab9App {
    public static void main(String[] args) {
        System.out.println("incepe simularea...");

        // facem un labirint impartit de toate threadurile
        Maze maze = new Maze();

        // facem iepurasul
        Bunny bunny = new Bunny(1, 1, maze);

        // facem 3 roboti rai
        Robot r1 = new Robot("R1", 8, 8, maze);
        Robot r2 = new Robot("R2", 1, 8, maze);
        Robot r3 = new Robot("R3", 8, 1, maze);

        // pornim thread-urile (le dam start ca sa ruleze toate in paralel)
        new Thread(bunny).start();
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();

        // main-ul devine "spectator" si printeaza labirintul din cand in cand
        while (!maze.isGameOver()) {
            maze.printMaze();
            try {
                Thread.sleep(300); // dam refresh la fiecare 300ms sa vedem cum se misca
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // printam ultima stare ca sa vedem cine a castigat
        maze.printMaze();
        System.out.println("gata jocul!");
    }
}

// clasa care tine minte harta si se asigura ca nu se bat threadurile intre ele
class Maze {
    // 0 = gol, 1 = perete, 2 = iesire (jos in dreapta)
    private int[][] grid = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 2}, // iesirea e la 8, 8 (valoarea 2)
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    // aici tinem minte exact unde sunt baietii pe harta ca sa nu punem un robot peste altul
    private char[][] entities = new char[10][10];
    private boolean gameOver = false;

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean status) {
        this.gameOver = status;
    }

    // cel mai important cuvant din tema! SYNCHRONIZED
    // cand un thread (ex: robot1) intra aici, celelalte asteapta la coada. asa evitam bug-urile concurente
    public synchronized boolean tryMove(int oldX, int oldY, int newX, int newY, char symbol) {
        if (gameOver) return false;

        // daca iese din harta sau da de perete, miscare invalida
        if (newX < 0 || newX >= 10 || newY < 0 || newY >= 10) return false;
        if (grid[newX][newY] == 1) return false;

        char target = entities[newX][newY];

        // daca e iepurasul
        if (symbol == 'B') {
            if (target == 'R') {
                gameOver = true;
                System.out.println("\n*** iepurasul a sarit direct in bratele unui robot! au pierdut! ***");
            } else if (grid[newX][newY] == 2) { // 2 e iesirea
                gameOver = true;
                System.out.println("\n*** iepurasul a gasit iesirea! a castigat! ***");
            }
        }
        // daca e robot
        else if (symbol == 'R') {
            if (target == 'B') {
                gameOver = true;
                System.out.println("\n*** robtul a prin iepurele ***");
            } else if (target == 'R') {
                // nu au voie 2 roboti in aceeasi casuta (regula din pdf)
                return false;
            }
        }

        // daca totul e ok, facem mutarea propriu-zisa pe matricea noastra
        entities[oldX][oldY] = '\0'; // curatam vechea pozitie
        entities[newX][newY] = symbol; // punem pe pozitia noua

        return true; // a reusit mutarea
    }

    // alta metoda sincronizata ca sa printam harta fara sa o modifice cineva in timp ce printam
    public synchronized void printMaze() {
        System.out.println("\n--- starea labirintului ---");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (entities[i][j] != '\0') {
                    System.out.print(entities[i][j] + " "); // R sau B
                } else if (grid[i][j] == 1) {
                    System.out.print("# "); // perete
                } else if (grid[i][j] == 2) {
                    System.out.print("E "); // exit
                } else {
                    System.out.print(". "); // gol
                }
            }
            System.out.println();
        }
    }

    // cand initializam, bagam entitatea in matrice fara sa mutam de undeva
    public synchronized void spawn(int x, int y, char symbol) {
        entities[x][y] = symbol;
    }
}

// thread pt iepuras (implementeaza runnable obligatoriu)
class Bunny implements Runnable {
    private int x, y;
    private Maze maze;
    private Random rand = new Random();

    public Bunny(int startX, int startY, Maze maze) {
        this.x = startX;
        this.y = startY;
        this.maze = maze;
        maze.spawn(x, y, 'B'); // il punem pe harta cand se naste
    }

    @Override
    public void run() {
        // cat timp jocul e in desfasurare
        while (!maze.isGameOver()) {
            // miscare la intamplare (-1, 0, sau 1 pe axe)
            int dx = rand.nextInt(3) - 1;
            int dy = rand.nextInt(3) - 1;

            // nu stam pe loc dar nici nu ne miscam in diagonala
            if (Math.abs(dx) + Math.abs(dy) == 1) {
                // incercam sa ne mutam
                if (maze.tryMove(x, y, x + dx, y + dy, 'B')) {
                    x += dx;
                    y += dy;
                }
            }

            // trage pe dreapta un pic ca sa se simuleze miscarea (sa nu se termine instant)
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// thread pt robot
class Robot implements Runnable {
    private String name;
    private int x, y;
    private Maze maze;
    private Random rand = new Random();

    public Robot(String name, int startX, int startY, Maze maze) {
        this.name = name;
        this.x = startX;
        this.y = startY;
        this.maze = maze;
        maze.spawn(x, y, 'R');
    }

    @Override
    public void run() {
        while (!maze.isGameOver()) {
            // tot miscare la intamplare pt compulsory
            int dx = rand.nextInt(3) - 1;
            int dy = rand.nextInt(3) - 1;

            if (Math.abs(dx) + Math.abs(dy) == 1) {
                if (maze.tryMove(x, y, x + dx, y + dy, 'R')) {
                    x += dx;
                    y += dy;
                }
            }

            // robotii sunt putin mai lenti decat iepurasul ca sa aiba si el o sansa :)
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
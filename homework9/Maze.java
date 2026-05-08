package org.example;

public class Maze {
    private int[][] grid = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 2},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private char[][] entities = new char[10][10];
    private volatile boolean gameOver = false;

    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean status) { this.gameOver = status; }

    public synchronized boolean tryMove(int oldX, int oldY, int newX, int newY, char symbol) {
        if (gameOver) return false;
        if (newX < 0 || newX >= 10 || newY < 0 || newY >= 10) return false;
        if (grid[newX][newY] == 1) return false;

        char target = entities[newX][newY];

        if (symbol == 'B') {
            if (target == 'R') {
                gameOver = true;
                System.out.println("\n*** Iepurasul a fost prins! GAME OVER ***");
            } else if (grid[newX][newY] == 2) {
                gameOver = true;
                System.out.println("\n*** Iepurasul a castigat! ***");
            }
        } else if (symbol == 'R') {
            if (target == 'B') {
                gameOver = true;
                System.out.println("\n*** Robotii au castigat! GAME OVER ***");
            } else if (target == 'R') {
                return false;
            }
        }

        entities[oldX][oldY] = '\0';
        entities[newX][newY] = symbol;
        return true;
    }

    public synchronized void printMaze(long seconds) {
        System.out.println("\n--- Timp: " + seconds + "s | Limita: 30s ---");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (entities[i][j] != '\0') System.out.print(entities[i][j] + " ");
                else if (grid[i][j] == 1) System.out.print("# ");
                else if (grid[i][j] == 2) System.out.print("E ");
                else System.out.print(". ");
            }
            System.out.println();
        }
    }

    public synchronized void spawn(int x, int y, char symbol) {
        entities[x][y] = symbol;
    }
}
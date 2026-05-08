package org.example;

import java.util.Random;

public class Bunny implements Runnable {
    int x, y;
    Maze maze;
    GameController ctrl;
    Random rand = new Random();

    public Bunny(int x, int y, Maze maze, GameController ctrl) {
        this.x = x; this.y = y; this.maze = maze; this.ctrl = ctrl;
        maze.spawn(x, y, 'B');
    }

    @Override
    public void run() {
        while (!maze.isGameOver()) {
            ctrl.checkPause();
            int dx = rand.nextInt(3) - 1;
            int dy = rand.nextInt(3) - 1;

            if (Math.abs(dx) + Math.abs(dy) == 1) {
                if (maze.tryMove(x, y, x + dx, y + dy, 'B')) {
                    x += dx; y += dy;
                }
            }
            try { Thread.sleep(ctrl.bunnySpeed); }
            catch (InterruptedException e) { break; }
        }
    }
}
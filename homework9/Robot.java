package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Robot implements Runnable {
    String name;
    int x, y;
    Maze maze;
    GameController ctrl;
    Random rand = new Random();

    boolean[][] visited = new boolean[10][10];

    public Robot(String name, int x, int y, Maze maze, GameController ctrl) {
        this.name = name; this.x = x; this.y = y; this.maze = maze; this.ctrl = ctrl;
        maze.spawn(x, y, 'R');
    }

    @Override
    public void run() {
        visited[x][y] = true;

        while (!maze.isGameOver()) {
            ctrl.checkPause();

            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            List<int[]> validUnvisited = new ArrayList<>();
            List<int[]> validVisited = new ArrayList<>();

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && nx < 10 && ny >= 0 && ny < 10) {
                    if (!visited[nx][ny]) {
                        validUnvisited.add(new int[]{nx, ny});
                    } else {
                        validVisited.add(new int[]{nx, ny});
                    }
                }
            }

            if (!validUnvisited.isEmpty()) {
                int[] move = validUnvisited.get(rand.nextInt(validUnvisited.size()));
                if (maze.tryMove(x, y, move[0], move[1], 'R')) {
                    x = move[0]; y = move[1];
                    visited[x][y] = true;
                }
            }
            else if (!validVisited.isEmpty()) {
                int[] move = validVisited.get(rand.nextInt(validVisited.size()));
                if (maze.tryMove(x, y, move[0], move[1], 'R')) {
                    x = move[0]; y = move[1];
                    if (rand.nextDouble() < 0.2) visited = new boolean[10][10];
                }
            }

            try { Thread.sleep(ctrl.robotsSpeed); }
            catch (InterruptedException e) { break; }
        }
    }
}
package org.example;

import java.util.Scanner;

public class Lab9HomeworkApp {
    public static void main(String[] args) {
        System.out.println("se porneste , poti scrie comenzi in consola oricand");
        System.out.println("Comenzi 'stop', 'start', 'speed b <ms>', 'speed r <ms>'");

        Maze maze = new Maze();
        GameController controller = new GameController();

        Thread daemon = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            long timeLimit = 30000;

            while (!maze.isGameOver()) {
                long elapsed = System.currentTimeMillis() - startTime;
                if (elapsed > timeLimit) {
                    maze.setGameOver(true);
                    System.out.println("\n*** timpul a expirat, iepurele a scapat ***");
                    break;
                }

                maze.printMaze(elapsed / 1000);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        daemon.setDaemon(true);
        daemon.start();

        new Thread(new Bunny(1, 1, maze, controller)).start();
        new Thread(new Robot("R1", 8, 8, maze, controller)).start();
        new Thread(new Robot("R2", 1, 8, maze, controller)).start();
        new Thread(new Robot("R3", 8, 1, maze, controller)).start();

        Scanner scanner = new Scanner(System.in);
        while (!maze.isGameOver()) {
            if (scanner.hasNextLine()) {
                String cmd = scanner.nextLine().toLowerCase().trim();

                if (cmd.equals("stop")) {
                    controller.isPaused = true;
                    System.out.println(">> JOC PUS PE PAUZA <<");
                } else if (cmd.equals("start")) {
                    controller.isPaused = false;
                    synchronized (controller.pauseLock) {
                        controller.pauseLock.notifyAll();
                    }
                    System.out.println(">> JOC RELUAT <<");
                } else if (cmd.startsWith("speed b ")) {
                    try {
                        controller.bunnySpeed = Integer.parseInt(cmd.split(" ")[2]);
                        System.out.println(">> Viteza iepure setata la " + controller.bunnySpeed + "ms <<");
                    } catch (Exception e) { System.out.println("Eroare comanda. Ex: speed b 100"); }
                } else if (cmd.startsWith("speed r ")) {
                    try {
                        controller.robotsSpeed = Integer.parseInt(cmd.split(" ")[2]);
                        System.out.println(">> Viteza roboti setata la " + controller.robotsSpeed + "ms <<");
                    } catch (Exception e) { System.out.println("Eroare comanda. Ex: speed r 500"); }
                }
            }
        }
        scanner.close();
        System.out.println("Program oprit complet.");
    }
}
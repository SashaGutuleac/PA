package org.example.homework10.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameServer {
    private static final int PORT = 8100;
    private boolean running = true;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public GameServer() {
        threadPool = Executors.newFixedThreadPool(10);
        Game gameLogic = new Game();

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server Trivia pornit pe portul " + PORT);

            while (running) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Un jucator nou s-a conectat!");
                    threadPool.execute(new ClientHandler(socket, this, gameLogic));
                } catch (IOException e) {
                    if (!running) {
                        System.out.println("Conexiunile au fost oprite.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        System.out.println("Se initiaza oprirea gratioasa a serverului...");
        running = false;
        try {
            if (serverSocket != null) serverSocket.close();
            threadPool.shutdown();
            threadPool.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Server oprit complet.");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GameServer();
    }
}
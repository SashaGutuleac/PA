package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 8100;
    private boolean running = true;
    private ServerSocket serverSocket;

    public GameServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Serverul a pornit pe portul " + PORT + " si asteapta clienti...");

            while (running) {
                Socket socket = serverSocket.accept(); // Asteapta sa se conecteze cineva
                // Cand cineva se conecteaza, cream un fir de executie nou pentru el
                new ClientThread(socket, this).start();
            }
        } catch (IOException e) {
            if (running) {
                System.err.println("Eroare la pornirea serverului: " + e.getMessage());
            } else {
                System.out.println("Serverul a fost oprit cu succes!");
            }
        }
    }

    // Metoda apelata cand primim comanda "stop"
    public void stopServer() {
        this.running = false;
        try {
            serverSocket.close(); // Inchidem poarta ca sa nu mai intre nimeni
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GameServer();
    }
}
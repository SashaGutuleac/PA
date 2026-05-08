package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private GameServer server;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            // "in" citeste de la client, "out" trimite inapoi catre client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String request;
            // Citim comenzile trimise de client, linie cu linie
            while ((request = in.readLine()) != null) {
                if (request.equalsIgnoreCase("stop")) {
                    out.println("Server stopped");
                    server.stopServer(); // Oprim serverul cu totul
                    break;
                } else {
                    out.println("Server received the request: " + request);
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare de comunicare cu clientul: " + e.getMessage());
        } finally {
            try {
                socket.close(); // La final, inchidem mereu conexiunea
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
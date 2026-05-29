package org.example.homework10.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket socket;
    private GameServer server;
    private Game game;

    public ClientHandler(Socket socket, GameServer server, Game game) {
        this.socket = socket;
        this.server = server;
        this.game = game;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("--- BUN VENIT LA TRIVIA BLITZ ---");
            out.println("Scrie 'start' pentru a incepe jocul sau 'stop' pentru a opri serverul.");

            String request = in.readLine();
            if (request == null) return;

            if (request.equalsIgnoreCase("stop")) {
                out.println("Server stopped");
                server.stopServer();
                return;
            }

            if (request.equalsIgnoreCase("start")) {
                int score = 0;
                long totalTime = 0;
                List<String[]> questions = game.getQuestions();

                for (String[] q : questions) {
                    out.println("INTREBARE: " + q[0] + " (Ai 10 secunde!)");

                    long startTime = System.currentTimeMillis();
                    String answer = in.readLine();
                    long endTime = System.currentTimeMillis();
                    long responseTime = endTime - startTime;

                    if (answer == null) break;

                    if (responseTime > 10000) {
                        out.println("TIMP EXPIRAT! Ai stat " + (responseTime / 1000) + " secunde.");
                        totalTime += responseTime;
                    } else if (answer.equalsIgnoreCase(q[1].trim())) {
                        out.println("CORECT! (+1 punct)");
                        score++;
                        totalTime += responseTime;
                    } else {
                        out.println("GRESIT! Raspunsul corect era: " + q[1]);
                        totalTime += responseTime;
                    }
                }
                out.println("--- JOC TERMINAT ---");
                out.println("Scor final: " + score + "/" + questions.size() + " | Timp total: " + (totalTime / 1000.0) + " sec.");
                out.println("disconnect_me");
            }
        } catch (IOException e) {
            System.out.println("Client deconectat brusc.");
        }
    }
}
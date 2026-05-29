package org.example.homework10.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8100);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            Thread listenerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        if (serverMessage.equals("disconnect_me") || serverMessage.equals("Server stopped")) {
                            System.out.println("Conexiune incheiata. Apasa ENTER pentru a iesi din consola.");
                            System.exit(0);
                        }
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Conexiunea cu serverul s-a intrerupt.");
                    System.exit(0);
                }
            });
            listenerThread.start();

            while (true) {
                String userInput = scanner.nextLine();
                out.println(userInput);

                if (userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("stop")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare de conectare! Serverul este pornit?");
        }
    }
}
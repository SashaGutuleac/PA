package org.example.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // 127.0.0.1 inseamna "pe calculatorul meu" (localhost)
        int PORT = 8100;

        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectat la server! Poti scrie comenzi.");
            System.out.println("Tasteaza 'exit' ca sa inchizi doar clientul, sau 'stop' ca sa opresti serverul.");

            while (true) {
                System.out.print("> ");
                String command = scanner.nextLine().trim();

                // Daca scriem exit, inchidem bucla clientului fara sa omoram serverul
                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Iesire din client...");
                    break;
                }

                // Trimitem comanda la server
                out.println(command);

                // Asteptam si citim raspunsul de la server
                String response = in.readLine();
                if (response == null) {
                    System.out.println("Conexiunea cu serverul a fost pierduta.");
                    break;
                }
                System.out.println("Răspuns: " + response);

                // Daca i-am zis sa se opreasca, ne oprim si noi
                if (command.equalsIgnoreCase("stop")) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Eroare la client! Asigura-te ca serverul este pornit INAINTE sa pornesti clientul. Erorare: " + e.getMessage());
        }
    }
}
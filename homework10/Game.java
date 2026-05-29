package org.example.homework10.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<String[]> qaList = new ArrayList<>();

    public Game() {
        try (BufferedReader br = new BufferedReader(new FileReader("questions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    qaList.add(parts);
                }
            }
        } catch (IOException e) {
            System.out.println("Fisierul questions.txt nu a fost gasit. Folosim default.");
            qaList.add(new String[]{"Capitala Frantei?", "Paris"});
        }
    }

    public List<String[]> getQuestions() {
        return qaList;
    }
}
package org.example.compulsory7;

public class Movie {
    private int id;
    private String title;

    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // gettere springul meu are nevoie de ele ca sa stie ce sa afiseze in browser
    public int getId() { return id; }
    public String getTitle() { return title; }
}
package org.example;

public class Movie {
    private int id;
    private String title;

    // constructor ul nostru
    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // gettere pentru a putea citi datele mai tarziu in html
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
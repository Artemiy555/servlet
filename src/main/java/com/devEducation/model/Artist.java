package com.devEducation.model;

public class Artist {

    private String genre;
    private String name;

    public Artist(String name,String genre) {
        this.genre = genre;
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "genre='" + genre + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.devEducation.model;

public class Artist {

    private String genre;
    private String name;

    public Artist(String genre, String artists) {
        this.genre = genre;
        this.name = artists;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtists() {
        return name;
    }

    public void setArtists(String artists) {
        this.name = artists;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "genre='" + genre + '\'' +
                ", artists='" + name + '\'' +
                '}';
    }
}

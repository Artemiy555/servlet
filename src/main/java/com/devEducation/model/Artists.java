package com.devEducation.model;

public class Artists {

    private String genre;
    private String artists;

    public Artists(String genre, String artists) {
        this.genre = genre;
        this.artists = artists;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    @Override
    public String toString() {
        return "Artists{" +
                "genre='" + genre + '\'' +
                ", artists='" + artists + '\'' +
                '}';
    }
}

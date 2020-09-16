package com.devEducation;

import com.devEducation.dao.MySqlDao;
import com.devEducation.model.Album;
import com.devEducation.model.Artist;
import com.devEducation.model.Song;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Song> songs = new ArrayList<Song>();
        songs.add(new Song(
                "Karamel",
                "Rap",
                "Lsp",
                "Karamel",
                "////",
                "3.20",
                "2019"));
        songs.add(new Song(
                "Monet",
                "Rap",
                "Lsp",
                "City",
                "////",
                "3.50",
                "2018"));

        List<String> genres =  songs.stream().map(Song::getGenre).distinct().collect(Collectors.toList());
        System.out.println(genres);
        List<Artist> artists = songs.stream().map(e -> new Artist(e.getArtist(),e.getGenre())).distinct().collect(Collectors.toList());
        System.out.println(artists);
        if (artists.get(0).equals(artists.get(1))){
            System.out.println("Стримы сосутхуи");
        }
//        List<Album> albums = songs.stream().distinct().map(e -> new Album(e.getArtist(), e.getAlbum(), e.getYear())).collect(Collectors.toList());
//        System.out.println(albums);


//        MySqlDao mySqlDao = new MySqlDao();
//        mySqlDao.deleteAll();
//
//        mySqlDao.insertGenre(genres);
//        mySqlDao.insertArtist(artists);
//        mySqlDao.insertAlbum(albums);
//        mySqlDao.insertSong(songs);
//
//        System.out.println(mySqlDao.selectGenre());
//        System.out.println(mySqlDao.selectArtist("Rap"));
//        System.out.println(mySqlDao.selectAlbum());
//        System.out.println(mySqlDao.selectSong("Pharaoh"));
    }
}

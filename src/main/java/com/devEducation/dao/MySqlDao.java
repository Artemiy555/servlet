package com.devEducation.dao;

import com.devEducation.model.Album;
import com.devEducation.model.Artist;
import com.devEducation.model.Genre;
import com.devEducation.model.Song;
import com.devEducation.util.MySqlUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class MySqlDao {

    private static final Logger logger = Logger.getLogger(MySqlDao.class.getSimpleName());

    private Connection getDBConnection() {
        return MySqlUtil.getDBConnection();
    }

    private Connection c = getDBConnection();


    public void insertGenre(List<String> genres) {

        try {

            Connection c = getDBConnection();
            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO music.genre" +
                            "(name)" +
                            "VALUES (?)");

//            ResultSet set = statement.executeQuery(
//                    "INSERT INTO music.genre" +
//                            "(name)" +
//                            "VALUES ( ?)");

            for (String genre : genres) {
                statement.setString(1, genre);
                logger.info("Создана запись: " + genre);
                try {
                statement.execute();
                }catch (SQLException ignored){}

            }

            statement.clearParameters();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List selectGenre(){

        List<Genre> genres = new ArrayList<>();
        try {
            Statement statement = c.createStatement();
            ResultSet set = statement.executeQuery(
                    "SELECT * FROM music.genre"
            );

            while (set.next()) {
                genres.add(new Genre(
                        set.getString(2)
                ));
            }
            statement.close();

//            for (Genre p : genres) {
//                System.out.println("Получена запись: " + p);
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }

    public void insertArtist(List<Artist> artists) {

        try {
            Connection c = getDBConnection();
            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO music.artist" +
                            "(name,genre)" +
                            "VALUES ( ?,? )");

            for (Artist artist : artists) {
                statement.setString(1, artist.getName());
                statement.setString(2, artist.getGenre());
                logger.info("Создана запись: " + artist);
                try {
                statement.execute();
                }catch (Exception ignored){}
            }

            statement.clearParameters();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List selectArtist(String genre){

        List<Artist> artists = new ArrayList<>();
        try {
            Statement statement = c.createStatement();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM music.artist where genre=?");
            preparedStatement.setString(1, genre);
            ResultSet set = preparedStatement.executeQuery();

            while (set.next()) {
                artists.add(new Artist(
                        set.getString(2),
                        set.getString(3)
                ));
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artists;
    }

    public void insertSong(List<Song> songs) {

        try {
            Connection c = getDBConnection();
            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO music.song" +
                            "(name,genre,artist,album,link,time)" +
                            "VALUES ( ?,?,?,?,?,? )");

            for (Song song : songs) {
                statement.setString(1, song.getName());
                statement.setString(2, song.getGenre());
                statement.setString(3, song.getArtist());
                statement.setString(4, song.getAlbum());
                statement.setString(5, song.getLink());
                statement.setString(6, song.getTime());
                logger.info("Создана запись: " + song);
                try {
                    statement.execute();
                }catch (Exception ignored){}
            }

            statement.clearParameters();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List selectSong(String artist){

        List<Song> songs = new ArrayList<>();
        try {
            Statement statement = c.createStatement();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT song.name," +
                    "song.genre," +
                    "song.artist," +
                    "song.album," +
                    "song.link," +
                    "song.time," +
                    "album.year " +
                    "FROM music.album,music.song where song.album = album.album and song.artist = ?");

            preparedStatement.setString(1, artist);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                songs.add(new Song(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getString(6),
                        set.getString(7)
                        ));
            }
            statement.close();

            for (Song p : songs) {
                logger.info("Получена запись: " + p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs;
    }

    public void insertAlbum(List<Album> albums) {

        try {
            Connection c = getDBConnection();
            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO music.album" +
                            "(artist,album,year)" +
                            "VALUES ( ?,?,? )");

            for (Album album : albums) {
                statement.setString(1, album.getArtist());
                statement.setString(2, album.getAlbum());
                statement.setString(3, album.getYear());
                try {
                    statement.execute();
                }catch (Exception ignored){}
            }

            statement.clearParameters();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List selectAlbum(){

        List<Album> albums = new ArrayList<>();
        try {
            Statement statement = c.createStatement();
            ResultSet set = statement.executeQuery(
                    "SELECT * FROM music.album"
            );

            while (set.next()) {
                albums.add(new Album(
                        set.getString(2),
                        set.getString(3),
                        set.getString(4)
                ));
            }
            statement.close();

//            for (Album p : albums) {
//                System.out.println("Получена запись: " + p);
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return albums;
    }

    public void deleteAll() {
        try {
            PreparedStatement statement =
                    c.prepareStatement(
                            "DELETE FROM music.song "
                    );
            statement.execute();
            statement =
                    c.prepareStatement(
                            "DELETE FROM music.album "
                    );
            statement.execute();
            statement =
                    c.prepareStatement(
                            "DELETE FROM music.artist "
                    );
            statement.execute();
            statement =
                    c.prepareStatement(
                            "DELETE FROM music.genre "
                    );
            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

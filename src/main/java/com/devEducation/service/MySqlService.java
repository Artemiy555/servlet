package com.devEducation.service;

import com.devEducation.model.Album;
import com.devEducation.model.Artist;
import com.devEducation.model.Genre;
import com.devEducation.model.Song;
import com.devEducation.util.MySqlUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySqlService {

    private static final Logger logger = Logger.getLogger(MySqlService.class.getSimpleName());

    private Connection c = MySqlUtil.getDBConnection();

    public void insertGenre(List<String> genres) {

        try {

            PreparedStatement statement = c.prepareStatement(
                    "INSERT INTO music.genre" +
                            "(name)" +
                            "VALUES (?)");

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

            for (Genre p : genres) {
                logger.info("Получена запись: " + p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }

    public void insertArtist(List<Artist> artists) {

        try {
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
                }catch (Exception ignored){
                    System.out.println(song);
                }
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
            exrcuteQuery(songs, statement, preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs;
    }

    private void exrcuteQuery(List<Song> songs, Statement statement, PreparedStatement preparedStatement) throws SQLException {
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
    }

    public List selectAllSong(){

        List<Song> songs = new ArrayList<>();
        try {
            Statement statement = c.createStatement();
            PreparedStatement preparedStatement = c.prepareStatement(
                    "SELECT song.name," +
                    "song.genre," +
                    "song.artist," +
                    "song.album," +
                    "song.link," +
                    "song.time," +
                    "album.year " +
                    "FROM music.album,music.song where song.album = album.album ");

            exrcuteQuery(songs, statement, preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs;
    }

    public void insertAlbum(List<Album> albums) {

        try {
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

    public void deleteSong(int id){

        try  {
            PreparedStatement statement =
                    c.prepareStatement(
                            "DELETE FROM music.song WHERE ID = ?"
                    );
            statement.setInt(1, id);
            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Song selectSongById(int id){
            Song song = new Song();
        try  {

            PreparedStatement preparedStatement = c.prepareStatement("SELECT song.name," +
                    "song.genre," +
                    "song.artist," +
                    "song.album," +
                    "song.link," +
                    "song.time," +
                    "album.year " +
                    "FROM music.album,music.song where song.album = album.album and song.id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            song.setName(resultSet.getString(1));
            song.setGenre(resultSet.getString(2));
            song.setArtist(resultSet.getString(3));
            song.setAlbum(resultSet.getString(4));
            song.setLink(resultSet.getString(5));
            song.setTime(resultSet.getString(6));
            song.setYear(resultSet.getString(7));



            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return song;
    }

    public void updateSong(Song song,int id) {

        try  {
            PreparedStatement upd = c.
                    prepareStatement(
                            "UPDATE music.song SET name = ?, genre = ?, artist = ?, album = ? WHERE ID = ?");
            upd.setString(1, song.getName());
            upd.setString(2, song.getGenre());
            upd.setString(3, song.getArtist());
            upd.setString(4,song.getAlbum());
            upd.setInt(5,id);
            upd.execute();
            logger.info("Обновленная запись: " + song);
            upd.close();

            logger.info("Обновление прошло успешно!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

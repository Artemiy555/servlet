package com.devEducation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.devEducation.model.Song;
import com.mpatric.mp3agic.*;

import static java.lang.String.*;


public class SongTag {

    public List<Song> getListSongs(String path){
        List<Song> songs = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.parallel()
                    .filter(Files::isRegularFile)
                    .forEach(e -> {
                        if (String.valueOf(e).contains(".mp3")) {
                            songs.add(getTag(valueOf(e)));
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return songs;
    }

    private Song getTag(String fileLocation){
        Song song = new Song();
        try {
            Mp3File mp3file = new Mp3File(fileLocation);
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                //System.out.println("Artist: " + id3v2Tag.getArtist());
                song.setArtist(id3v2Tag.getArtist());
                //System.out.println("Title: " + id3v2Tag.getTitle());
                song.setName(id3v2Tag.getTitle());
                //System.out.println("Album: " + id3v2Tag.getAlbum());
                song.setAlbum(id3v2Tag.getAlbum());
                //System.out.println("Year: " + id3v2Tag.getYear());
                song.setYear(id3v2Tag.getYear());
                //System.out.println("Genre: " + id3v2Tag.getGenreDescription());
                song.setGenre(id3v2Tag.getGenreDescription());
                //System.out.println("Length: " + mp3file.getLengthInSeconds()+ " seconds");
                song.setTime(valueOf(mp3file.getLengthInSeconds()/60));
                song.setLink(fileLocation);

            }
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            //e.printStackTrace();
            return null;
        }
        return song;
    }

}

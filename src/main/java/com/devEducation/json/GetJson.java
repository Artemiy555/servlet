package com.devEducation.json;

import com.devEducation.model.Artist;
import com.devEducation.model.Song;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GetJson {

    public static void main(String[] args) {
//        System.out.println(getGenre("genre.json"));
        //System.out.println(getArtistsByGenre("artists.json",""));
        System.out.println(getArtistsByGenre("C:\\Users\\Artemiy\\Desktop\\sampleServlet\\sampleServlet\\song.json","Rap"));
//        System.out.println(getByParanetr("C:\\Users\\Artemiy\\Desktop\\sampleServlet\\sampleServlet\\artists.json","rep"));
    }


    public static StringBuilder getJson(String url){
        StringBuilder genre= new StringBuilder();
        try(FileReader reader = new FileReader(url)) {
            int c;
            while((c=reader.read())!=-1){
                genre.append((char) c);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return genre;
    }

    public static String getGenre(String path){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(path)){
            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray genre= (JSONArray) jsonObject.get("Genre");

            IntStream.range(0, genre.size())
                    .forEach(i -> stringBuilder.append(genre.get(i)).append(",")
                    );
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }

        return String.valueOf(stringBuilder);
    }

    public static List<Artist> getArtistsByGenre(String path, String genre){
        StringBuilder stringBuilder = new StringBuilder();
        List<Artist> artists= new ArrayList<>();
        try (FileReader reader = new FileReader(path)){

            int c;
            while((c=reader.read())!=-1){
                stringBuilder.append((char) c);
            }
            System.out.println(stringBuilder);
             artists = new Gson().fromJson(String.valueOf(stringBuilder),
                            new TypeToken<List<Artist>>(){}.getType());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return  artists.stream()
                .filter(e->e.getGenre().equals(genre))
                .collect(Collectors.toList());

    }

    public static List<Song> getSongByArtist(String path, String artist){
        StringBuilder stringBuilder = new StringBuilder();
        List<Song> artists= new ArrayList<>();
        try (FileReader reader = new FileReader(path)){

            int c;
            while((c=reader.read())!=-1){
                stringBuilder.append((char) c);
            }

            artists = new Gson().fromJson(String.valueOf(stringBuilder),
                    new TypeToken<List<Song>>(){}.getType());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return  artists.stream()
                .filter(e->e.getArtist().equals(artist))
                .collect(Collectors.toList());

    }


}

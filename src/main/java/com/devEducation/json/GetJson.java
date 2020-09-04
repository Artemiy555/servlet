package com.devEducation.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.stream.IntStream;

public class GetJson {

    public static void main(String[] args) {
        System.out.println(getGenre("genre.json"));
        System.out.println(getByParanetr("C:\\Users\\Artemiy\\Desktop\\sampleServlet\\sampleServlet\\artists.json","Rep"));
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

    public static String getByParanetr(String path, String gerge){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(path)){
            JSONParser jsonParser = new JSONParser();

            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray genre= (JSONArray) jsonObject.get(gerge);

            IntStream.range(0, genre.size())
                    .forEach(i -> stringBuilder.append(genre.get(i)).append(",")
                    );
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }

        return String.valueOf(stringBuilder);
    }


}

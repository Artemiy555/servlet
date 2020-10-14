package com.devEducation.servlet;

import com.devEducation.model.Album;
import com.devEducation.model.Artist;
import com.devEducation.model.Song;
import com.devEducation.service.MySqlService;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "UpdateSongServlet", urlPatterns = "/updateSong")
public class UpdateSongServlet extends HttpServlet {
    private static final long serialVersionUID = 1567877564;
    private static final Logger logger = Logger.getLogger(MySqlService.class.getSimpleName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        MySqlService mySqlService = new MySqlService();
        PrintWriter out = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String genre = request.getParameter("genre");
            String artist = request.getParameter("artist");
            String album = request.getParameter("album");

            Song song =mySqlService.selectSongById(id);
            logger.info(song.toString());

            if(name!=null){
                song.setName(name);
            }
            if(genre!=null){
                song.setGenre(genre);
                mySqlService.insertGenre(Collections.singletonList(song.getGenre()));
            }
            if(artist!=null){
                song.setArtist(artist);
                mySqlService.insertArtist(Collections.singletonList(new Artist(artist, genre)));
            }
            if(album!=null){
                song.setAlbum(album);
                mySqlService.insertAlbum(Collections.singletonList(new Album(artist, album, song.getYear())));
            }

//            if(genre!=null){
//                song.setGenre(genre);
//                mySqlService.insertGenre(Collections.singletonList(song.getGenre()));
//                if(artist!=null) {
//                    song.setArtist(artist);
//                    mySqlService.insertArtist(Collections.singletonList(new Artist(artist, genre)));
//                }else {
//                    mySqlService.insertArtist(Collections.singletonList(new Artist(song.getArtist(), genre)));
//                }
//            }
//            if(genre==null&&artist!=null){
//                mySqlService.insertArtist(Collections.singletonList(new Artist(artist, song.getGenre())));
//            }
//            if(album!=null){
//                song.setAlbum(album);
//                mySqlService.insertAlbum(Collections.singletonList(new Album(artist, album, song.getYear())));
//            }

            logger.info(song.toString());
            mySqlService.updateSong(song,id);
            out.print(HttpServletResponse.SC_OK);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            out.print(HttpServletResponse.SC_BAD_REQUEST);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}


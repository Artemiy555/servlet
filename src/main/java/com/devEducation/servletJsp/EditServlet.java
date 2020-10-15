package com.devEducation.servletJsp;

import com.devEducation.model.Album;
import com.devEducation.model.Artist;
import com.devEducation.model.Song;
import com.devEducation.service.MySqlService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "EditServlet", urlPatterns = "/editServlet")
public class EditServlet extends HttpServlet {
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
            String errorString = null;
            Song song = null;
            try {
                song = mySqlService.selectSongById(id);
            }catch (Exception e){
                e.printStackTrace();
                errorString = e.getMessage();
            }
            request.setAttribute("errorString", errorString);
            request.setAttribute("song", song);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editSong.jsp");
            dispatcher.forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            out.print(HttpServletResponse.SC_BAD_REQUEST);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        MySqlService mySqlService = new MySqlService();
        PrintWriter out = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            logger.info(name);
            String genre = request.getParameter("genre");
            logger.info(genre);
            String artist = request.getParameter("artist");
            logger.info(artist);
            String album = request.getParameter("album");
            logger.info(album);
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

            logger.info(song.toString());
            mySqlService.updateSong(song,id);
            out.print(HttpServletResponse.SC_OK);
            response.sendRedirect(request.getContextPath() + "/getAllSongServlet");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            out.print(HttpServletResponse.SC_BAD_REQUEST);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }

}
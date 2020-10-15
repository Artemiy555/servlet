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


@WebServlet(name = "CreateSongServlet", urlPatterns = "/createSongServlet")
public class CreateSongServlet extends HttpServlet {
    private static final long serialVersionUID = 1567877564;
    private static final Logger logger = Logger.getLogger(MySqlService.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String errorString = null;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/createSong.jsp");
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
            String name = request.getParameter("name");
            String genre = request.getParameter("genre");
            String artist = request.getParameter("artist");
            String album = request.getParameter("album");
            String link = request.getParameter("link");
            String time = request.getParameter("time");
            String year = request.getParameter("year");
            Song song = new Song(0,name,genre,artist,album,link,time,year);
            logger.info(song.toString());
            mySqlService.insertGenre(Collections.singletonList(song.getGenre()));
            mySqlService.insertArtist(Collections.singletonList(new Artist(artist, genre)));
            mySqlService.insertAlbum(Collections.singletonList(new Album(artist, album, song.getYear())));
            mySqlService.insertSong(Collections.singletonList(song));


            out.print(HttpServletResponse.SC_OK);
            logger.info(HttpServletResponse.SC_OK);
            response.sendRedirect(request.getContextPath() + "/getAllSongServlet");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            out.print(HttpServletResponse.SC_BAD_REQUEST);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }
}
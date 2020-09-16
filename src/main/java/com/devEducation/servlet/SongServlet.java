package com.devEducation.servlet;


import com.devEducation.dao.MySqlDao;
import com.devEducation.model.Song;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "ArtistsSong", urlPatterns = "/getSong")
public class SongServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-16");
        String artist = request.getParameter("artist");
        if (artist != null) {
            List<Song> songs = new MySqlDao().selectSong(artist);
            out.print(new Gson().toJson(songs));
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}



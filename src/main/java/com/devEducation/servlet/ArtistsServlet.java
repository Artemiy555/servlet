package com.devEducation.servlet;


import com.devEducation.dao.MySqlDao;
import com.devEducation.model.Artist;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ArtistsServlet", urlPatterns = "/getArtists")
public class ArtistsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-16");

        String genre = request.getParameter("genre");
        if (genre != null) {
            List<Artist> artists = new MySqlDao().selectArtist(genre);
            out.print(new Gson().toJson(artists));
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}



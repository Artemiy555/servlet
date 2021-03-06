package com.devEducation.servlet;


import com.devEducation.model.Artist;
import com.devEducation.service.MySqlService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ArtistsServlet", urlPatterns = "/getArtists")
public class ArtistsServlet extends HttpServlet {
    private static final long serialVersionUID = 1567877564;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String genre = request.getParameter("genre");
        if (genre != null) {
            List<Artist> artists = new MySqlService().selectArtist(genre);
            out.print(new Gson().toJson(artists));
        }
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}



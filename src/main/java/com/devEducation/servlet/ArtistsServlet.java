package com.devEducation.servlet;

import com.devEducation.json.GetJson;
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
        response.setCharacterEncoding("UTF-8");
        String genre = request.getParameter("genre");

//        String path = "\\WEB-INF\\classes\\com\\devEducation\\servlet\\artists.json";
//        ServletContext sc = request.getServletContext();
//        String rp = sc.getRealPath(path);



        List<Artist> artists = GetJson
                .getArtistsByGenre("C:\\Users\\Artemiy\\Desktop\\sampleServlet\\sampleServlet\\artists.json",genre);

        out.print(new Gson().toJson(artists));

        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}



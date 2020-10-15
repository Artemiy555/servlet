package com.devEducation.servlet;


import com.devEducation.model.Song;
import com.devEducation.service.MySqlService;
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
    private static final long serialVersionUID = 1567877564;

//    MySqlService mySqlDao = new MySqlService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String artist = request.getParameter("artist");
        MySqlService mySqlService =new MySqlService();
        if (artist != null) {
            if(artist.equals("*")){
                List<Song> songs = mySqlService.selectAllSong();
                String str = new Gson().toJson(songs);
                out.print(str);
            }else {
                List<Song> songs = mySqlService.selectSong(artist);
                String str = new Gson().toJson(songs);
                out.print(str);
            }
        }
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}



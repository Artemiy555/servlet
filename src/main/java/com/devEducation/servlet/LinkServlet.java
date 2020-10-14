package com.devEducation.servlet;

import com.devEducation.SongTag;
import com.devEducation.service.MySqlService;
import com.devEducation.model.Album;
import com.devEducation.model.Artist;
import com.devEducation.model.Song;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "LinkServlet", urlPatterns = "/getLink")
public class LinkServlet extends HttpServlet {
    private static final long serialVersionUID = 1567877564;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-16");
        String link = request.getParameter("link");

        out.print(link);
        System.out.println(link);
        //по этой ссылке запихиваем в таблицы
        List<Song> songs = new SongTag().getListSongs(link);

        List<String> genres = songs.stream().map(Song::getGenre).distinct().collect(Collectors.toList());
        List<Artist> artists = songs.stream().distinct().map(e -> new Artist(e.getArtist(), e.getGenre())).collect(Collectors.toList());
        List<Album> albums = songs.stream().distinct().map(e -> new Album(e.getArtist(), e.getAlbum(), e.getYear())).collect(Collectors.toList());

        MySqlService mySqlService = new MySqlService();
//        mySqlService.deleteAll();

        mySqlService.insertGenre(genres);
        mySqlService.insertArtist(artists);
        mySqlService.insertAlbum(albums);
        mySqlService.insertSong(songs);
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
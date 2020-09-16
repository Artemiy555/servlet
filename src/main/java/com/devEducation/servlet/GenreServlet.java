package com.devEducation.servlet;

import com.devEducation.dao.MySqlDao;
import com.devEducation.model.Genre;
import com.google.gson.Gson;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GenreServlet", urlPatterns = "/getGenre")
public class GenreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-16");
        //получаем все жанры с бд и запихиваем в json
        List<Genre> genres = new MySqlDao().selectGenre();
        out.print(new Gson().toJson(genres));
        //out.print(GetJson.getJson("C:\\Users\\Artemiy\\Desktop\\sampleServlet\\sampleServlet\\genre.json")+"\n");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}

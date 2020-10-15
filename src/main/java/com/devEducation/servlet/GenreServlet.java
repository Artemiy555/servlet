package com.devEducation.servlet;

import com.devEducation.model.Genre;
import com.devEducation.service.MySqlService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GenreServlet", urlPatterns = "/getGenre")
public class GenreServlet extends HttpServlet {
    private static final long serialVersionUID = 1567877564;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        //получаем все жанры с бд и запихиваем в json
        List<Genre> genres = new MySqlService().selectGenre();
        out.print(new Gson().toJson(genres));
        //out.print(GetJson.getJson("C:\\Users\\Artemiy\\Desktop\\sampleServlet\\sampleServlet\\genre.json")+"\n");
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}

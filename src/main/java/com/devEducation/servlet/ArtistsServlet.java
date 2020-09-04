package com.devEducation.servlet;

import com.devEducation.json.GetJson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        out.print(GetJson.getByParanetr("C:\\Users\\Artemiy\\Desktop\\sampleServlet\\sampleServlet\\artists.json",genre));

        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}


